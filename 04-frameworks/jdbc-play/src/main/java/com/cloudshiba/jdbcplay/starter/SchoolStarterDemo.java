package com.cloudshiba.jdbcplay.starter;

import com.cloudshiba.core.School;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SchoolStarterDemo {
    @Autowired
    private School school;

    public static void main(String[] args) {
        SchoolStarterDemo demo = new SchoolStarterDemo();
        log.info("school {}", demo.school);
    }

}
