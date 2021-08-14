package com.cloudshiba.shardingsphereproxy;

import com.cloudshiba.shardingsphereproxy.entity.EcOrder;
import com.cloudshiba.shardingsphereproxy.service.EcOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootApplication @Slf4j
public class ShardingsphereproxyApplication implements ApplicationRunner {
	@Autowired
	private EcOrderService ecOrderService;

	public static void main(String[] args) {
		SpringApplication.run(ShardingsphereproxyApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		long userId = 1;
		BigDecimal totalPrice = new BigDecimal(30.03);
		EcOrder order = EcOrder.builder()
				.userId(userId)
				.totalPrice(totalPrice)
				.build();

		EcOrder orderRecord = ecOrderService.create(order);
		log.info("Create new order: {}", orderRecord);

		List<EcOrder> orders = ecOrderService.findAll();
		log.info("Find all orders: {}", orders);

		Optional<EcOrder> orderOptional = ecOrderService.findOne(orderRecord.getOrderId());
		log.info("Find one order: {}", orderOptional.get());

		long newUserId = 2;
		BigDecimal newTotalPrice = new BigDecimal(66.66);
		orderRecord.setUserId(newUserId);
		orderRecord.setTotalPrice(newTotalPrice);
		EcOrder newOrderRecord = ecOrderService.update(orderRecord);
		log.info("Update order: {}", newOrderRecord);

		boolean isDelete = ecOrderService.delete(newOrderRecord.getOrderId());
		log.info("Is delete order?: {}", isDelete);
	}
}
