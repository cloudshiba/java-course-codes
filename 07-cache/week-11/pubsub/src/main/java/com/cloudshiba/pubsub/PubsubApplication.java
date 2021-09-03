package com.cloudshiba.pubsub;

import com.cloudshiba.pubsub.entity.SpeedKill;
import com.cloudshiba.pubsub.repository.SpeedKillRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan(basePackages = "com.cloudshiba.pubsub.repository")
@EnableScheduling
@SpringBootApplication
public class PubsubApplication implements ApplicationRunner {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private SpeedKillRepository speedKillRepository;

	public static void main(String[] args) {
		SpringApplication.run(PubsubApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		SpeedKill speedKill = speedKillRepository.get(1000);
		speedKillRepository.updateStock(1000L, 10);
		redisTemplate.opsForValue().set(speedKill.getName(), String.valueOf(speedKill.getNumber()));
	}
}
