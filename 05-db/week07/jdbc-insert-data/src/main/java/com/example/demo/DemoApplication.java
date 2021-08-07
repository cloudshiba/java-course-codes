package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

@Slf4j
@SpringBootApplication
public class DemoApplication implements ApplicationRunner {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
	    final int times = 1000000;
	    Instant startedAt = Instant.now();
	    long userId = 1L;
	    BigDecimal price = BigDecimal.valueOf(100);
	    String sql = "INSERT INTO ec_orders(user_id, total_price) VALUES (?, ?)";

		log.info("START INSERT {} RECORDS...at {}", times, startedAt);

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(
					PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, userId);
				ps.setBigDecimal(2, price);
			}
			public int getBatchSize() {
				return times;
			}
		});

		Instant endedAt = Instant.now();
		log.info("FINISH INSERT {} RECORDS...at {}", times, endedAt);
		Duration duration = Duration.between(startedAt, endedAt);
		long seconds = duration.getSeconds();
		log.info("TOTAL COST TIME: {} seconds", seconds);
	}
}
