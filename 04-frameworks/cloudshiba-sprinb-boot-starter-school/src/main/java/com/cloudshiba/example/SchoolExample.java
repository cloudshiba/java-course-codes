package com.cloudshiba.example;

import com.cloudshiba.core.School;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchoolExample {
    private static School school;

    public static void main(String[] args) {
        log.info("school: {}", school);
    }
}
