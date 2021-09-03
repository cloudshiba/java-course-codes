package com.cloudshiba.pubsub.controller;

import com.cloudshiba.pubsub.entity.SpeedKill;
import com.cloudshiba.pubsub.service.OrderService;
import com.cloudshiba.pubsub.service.SpeedKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class SpeedKillController {
    @Autowired
    private SpeedKillService speedKillService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/{id}")
    public SpeedKill get(@PathVariable Long id) {
        return speedKillService.get(id);
    }

    @GetMapping("/kill")
    public String kill(@RequestParam Long id) {
        String orderNo = UUID.randomUUID().toString();

        //speedKillService.updateStockWithRedisLock(orderNo, id, 1); //方案2，基于redis的分布式锁
        //speedKillService.updateStockInRedis(orderNo, id, 1); //方案3，基于redis的lua
        speedKillService.updateStockByDecr(orderNo, id);
        return "success";
    }


}
