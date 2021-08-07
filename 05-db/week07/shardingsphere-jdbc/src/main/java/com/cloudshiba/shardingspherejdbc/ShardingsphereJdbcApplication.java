package com.cloudshiba.shardingspherejdbc;

import com.cloudshiba.shardingspherejdbc.model.EcOrder;
import com.cloudshiba.shardingspherejdbc.service.EcOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Slf4j
@SpringBootApplication
public class ShardingsphereJdbcApplication implements ApplicationRunner {
	@Autowired
	private EcOrderService ecOrderService;

	public static void main(String[] args) {
		SpringApplication.run(ShardingsphereJdbcApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("================= Primary DataSource for writing =================");
		Instant instant = Instant.now();
		EcOrder order = EcOrder.builder()
				.userId(8888L)
				.totalPrice(BigDecimal.valueOf(8888.88))
				.createTime(instant.getEpochSecond())
				.updateTime(instant.getEpochSecond())
				.build();
		ecOrderService.create(order);

		System.out.println("================= Secondary DataSource for reading =================");
		List<EcOrder> readOrders = ecOrderService.findAll();
		log.info("readOrders: {}", readOrders);
	}
}
