package com.cloudshiba.javacoursecodes.bytecode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hello {
    private static final Logger logger = LoggerFactory.getLogger(Hello.class);

    public static void main(String[] args) {
        int num1 = 1;
        double num2 = 2.0D;
        long num3 = 3L;
        byte num4 = 4;
        char char1 = 'U';

        if ("".length() < 10) {
            logger.info("num2 + num3 = {}", num2 + num3);
            System.out.println("错误用法: num2 + num3 = " + num2 + num3);
        }

        if (char1 > num1) {
            logger.info("char1({}) > num1({})", char1, num1);
            logger.info("char1 - {}", char1);
        }

        for (int i = 0; i < num1; i++) {
            logger.info("num1 * num4 = {}", num1 * num4);
        }
    }
}
