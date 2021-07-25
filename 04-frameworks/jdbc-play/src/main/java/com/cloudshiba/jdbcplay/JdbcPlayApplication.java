package com.cloudshiba.jdbcplay;

import com.cloudshiba.jdbcplay.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@Slf4j
@SpringBootApplication
public class JdbcPlayApplication implements ApplicationRunner {
	@Autowired
    private StatementCrudService statementCrudService;
	@Autowired
	private PreparedStatementCrudService preparedStatementCrudService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private BatchProcessingService batchProcessingService;
	@Autowired
	private HikaricpDataSourceService hikaricpDataSourceService;

	public static void main(String[] args) {
		SpringApplication.run(JdbcPlayApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("ApplicationRunner Running...");

		// Statement CRUD 操作
		statementCrudService.doCrud();

		// PrepareStatement CRUD 操作
		preparedStatementCrudService.doCrud();

		// Transaction 操作
		transactionService.doTransaction();

		// 批次處理操作
		batchProcessingService.doBatchProcessing();

        // HikariCP 操作
		hikaricpDataSourceService.doCrud();

		log.info("ApplicationRunner Finished...");
	}
}
