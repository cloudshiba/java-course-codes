package com.cloudshiba.pubsub.service;

import com.cloudshiba.pubsub.config.RedisConfig;
import com.cloudshiba.pubsub.entity.Order;
import com.cloudshiba.pubsub.entity.SpeedKill;
import com.cloudshiba.pubsub.repository.OrderRepository;
import com.cloudshiba.pubsub.repository.SpeedKillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SpeedKillService {

    private final long userId = 100000L;

    @Autowired
    private SpeedKillRepository speedKillRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RedisPubSub redisPubSub;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public SpeedKill get(long id) {
        log.error("{}", id);
        return speedKillRepository.get(id);
    }


    @Transactional
    public void updateStockForUpdate(String orderNo, long id, int count) {
        SpeedKill speedKill = speedKillRepository.getWithLock(id);

        if(speedKill.getNumber() > 0) {
            try {
                //插入订单
                orderRepository.insert(new Order(orderNo, id, 1));
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            speedKillRepository.updateStock(id, speedKill.getNumber() - count);
        }
    }

    @Transactional
    public void updateStockWithRedisLock(String orderNo, long id, int count) {
        String redisLockKey = "product_" + id;
        String redisLockValue = UUID.randomUUID().toString();

        // set nx ex
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(redisLockKey, redisLockValue , 10, TimeUnit.SECONDS);
        if (flag) {
            SpeedKill speedKill = speedKillRepository.get(id);
            speedKillRepository.updateStock(id, speedKill.getNumber() - count);

            try {
                //插入订单
                orderRepository.insert(new Order(orderNo, id, 1));
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //使用lua脚本释放锁
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

                RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
                Object result = redisTemplate.execute(redisScript, Collections.singletonList(redisLockKey), redisLockValue);
                if(!Long.valueOf(1).equals(result)) {
                    throw new RuntimeException("Redis解锁异常!"); //事务回滚
                }
            }

        }
    }

    /***
     * 使用 Redis lua script 保证库存扣减的原子性
     */
    public void updateStockInRedis(String orderNo, long id, int count) {
        SpeedKill speedKill = speedKillRepository.get(id);
        String key = speedKill.getName();
        StringBuilder luaScript = new StringBuilder();
        luaScript.append("if (redis.call('exists', KEYS[1]) == 1) then"); // 查詢商品
        luaScript.append("  local stock  = tonumber(redis.call('get', KEYS[1]));"); // 如果存在，獲取庫存
        luaScript.append("  if (stock < tonumber(KEYS[2])) then return 0 \n");
        luaScript.append("  else\n");
        luaScript.append("      redis.call('set', KEYS[1], stock - tonumber(KEYS[2])); return 1;\n"); // 如果庫存大於等於扣減數量則回傳 1，不足回傳 0
        luaScript.append("  end\n");
        luaScript.append("else return -1\n");
        luaScript.append("end\n");


        RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript.toString(), Long.class);
        Object result = redisTemplate.execute(redisScript, Arrays.asList(key, String.valueOf(count)));

        if (Long.valueOf("1").equals(result)) {
            try {
                // 新增訂單
                orderRepository.insert(new Order(orderNo, id, 1));
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("下單失敗");
        }
    }


    /***
     * 扣減庫存，基於 Redis 的一個分部式計數器
     */
    @Transactional
    public void updateStockByDecr(String orderNo, long id) {
        SpeedKill speedKill = speedKillRepository.get(id);
        String key = speedKill.getName();
        orderRepository.insert(new Order(orderNo, id, 1));
        Long decrement = redisTemplate.opsForValue().decrement(key);
        if(decrement < 0L) {
            throw new RuntimeException("庫存不足..;");
        }
    }

    public void updateBySubPub(String orderNo, long id) {
        redisPubSub.publish(RedisConfig.topic.getTopic(), new Order(orderNo, id,1));
    }

}
