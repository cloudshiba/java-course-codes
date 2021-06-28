package com.cloudshiba.javacoursecodes.bytecode;

public class Hello {
    public static void main(String[] args) {
        int num1 = 1;       // 宣告 int num1
        double num2 = 2.0D; // 宣告 double num2
        long num3 = 3L;     // 宣吿 long num3
        byte num4 = 4;      // 宣吿 byte num4
        char char1 = 'U';   // 宣告 char char1

        if ("".length() < 10) {
            System.out.println("错误用法: num2 + num3 = " + num2 + num3);
        }

        if (char1 > num1) {
            System.out.println("比較結果： char1 > num1");
        }

        for (int i = 0; i < num1; i++) {
            System.out.println("num1 * num4 = " + num1 * num4);
        }
    }
}
