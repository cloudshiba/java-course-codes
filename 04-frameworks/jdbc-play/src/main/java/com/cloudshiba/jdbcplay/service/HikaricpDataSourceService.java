package com.cloudshiba.jdbcplay.service;

import com.cloudshiba.jdbcplay.dao.HikaricpDataSourceDAO;
import com.cloudshiba.jdbcplay.model.User;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HikaricpDataSourceService {
    private final HikaricpDataSourceDAO dao;

    public HikaricpDataSourceService(final HikaricpDataSourceDAO dao) {
        this.dao = dao;
    }

    public List<User> findAll() throws Exception {
        return dao.findAll();
    }

    public Optional<User> findById(long id) throws Exception {
        return dao.findById(id);
    }

    public int create(String name) throws Exception {
        return dao.create(name);
    }

    public int update(long id, String name) throws Exception {
        return dao.update(id, name);
    }

    public int delete(long id) throws Exception {
        return dao.delete(id);
    }

    public void doCrud() throws Exception {
        Faker faker = new Faker();
        String newUserName = faker.name().firstName();
        int createCount = create(newUserName);
        log.info("Create User Count: {} - name: {}", createCount, newUserName);

        List<User> users = findAll();
        log.info("find all users {}", users);

        long queryId = users.get(0).getId();
        Optional<User> userOptional = findById(queryId);
        log.info("find by id {}: {}", queryId, userOptional.get());

        String updateName = faker.name().firstName();
        int updateCount = update(queryId, updateName);
        log.info("Update User Count: {} - name: {}", updateCount, updateName);

        long lastRecordId = users.get(users.size() - 1).getId();
        int deleteCount = delete(lastRecordId);
        log.info("Delete User Count: {} - id: {}", deleteCount, lastRecordId);
    }
}
