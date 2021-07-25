package com.cloudshiba.jdbcplay.service;

import com.cloudshiba.jdbcplay.dao.TransactionDAO;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionService {
    private final TransactionDAO dao;

    public TransactionService(final TransactionDAO dao) {
        this.dao = dao;
    }

    public int transactionSuccessfully() throws Exception {
        int count = 0;
        Faker faker = new Faker();
        String name1 = faker.name().firstName();
        String name2 = faker.name().firstName();
        count = dao.transactionSuccessfully(name1, name2);
        return count;
    }

    public int transactionUnSuccessfully() throws Exception {
        int count = 0;
        Faker faker = new Faker();
        String name1 = faker.name().firstName();
        String name2 = faker.name().firstName();
        count = dao.transactionUnSuccessfully(name1, name2);
        return count;
    }

    public void doTransaction() throws Exception {
        transactionSuccessfully();
        transactionUnSuccessfully();
    }
}
