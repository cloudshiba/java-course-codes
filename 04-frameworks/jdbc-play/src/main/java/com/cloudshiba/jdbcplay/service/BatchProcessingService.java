package com.cloudshiba.jdbcplay.service;

import com.cloudshiba.jdbcplay.dao.BatchProcessingDAO;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BatchProcessingService {
    private final BatchProcessingDAO dao;

    public BatchProcessingService(final BatchProcessingDAO dao) {
        this.dao = dao;
    }

    public int[] statementBatchProcessing() throws Exception {
        int[] count = null;
        Faker faker = new Faker();
        String name1 = faker.name().firstName();
        String name2 = faker.name().firstName();
        count = dao.statementBatchProcessing(name1, name2);
        log.info("statementBatchProcessing result: {}", count);
        return count;
    }

    public int[] prepareStatementBatchProcessing() throws Exception {
        int[] count = null;
        Faker faker = new Faker();
        String name1 = faker.name().firstName();
        String name2 = faker.name().firstName();
        count = dao.prepareStatementBatchProcessing(name1, name2);
        log.info("prepareStatementBatchProcessing result: {}", count);
        return count;
    }

    public void doBatchProcessing() throws Exception {
        statementBatchProcessing();
        prepareStatementBatchProcessing();
    }
}
