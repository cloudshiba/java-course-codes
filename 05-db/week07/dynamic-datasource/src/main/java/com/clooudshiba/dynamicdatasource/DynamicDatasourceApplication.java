package com.clooudshiba.dynamicdatasource;

import com.clooudshiba.dynamicdatasource.datasource.DataSourceContextHolder;
import com.clooudshiba.dynamicdatasource.model.EcOrder;
import com.clooudshiba.dynamicdatasource.service.EcOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Slf4j
@SpringBootApplication
public class DynamicDatasourceApplication implements ApplicationRunner {
    @Autowired
	private DataSourceContextHolder dataSourceContextHolder;
    @Autowired
    private EcOrderService ecOrderService;

	public static void main(String[] args) {
		SpringApplication.run(DynamicDatasourceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("================= Primary DataSource for writing =================");
		Instant instant = Instant.now();
		EcOrder order = EcOrder.builder()
				.userId(9999L)
				.totalPrice(BigDecimal.valueOf(9999.99))
				.createTime(instant.getEpochSecond())
				.updateTime(instant.getEpochSecond())
				.build();
		ecOrderService.create(order);

		System.out.println("================= Secondary DataSource for reading =================");
		List<EcOrder> readOrders = ecOrderService.findAll();
		log.info("readOrders: {}", readOrders);
	}
}
