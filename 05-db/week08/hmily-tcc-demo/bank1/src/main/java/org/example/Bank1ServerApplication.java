package org.example;

import org.example.service.Bank2AccountService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ImportResource({"classpath:spring-dubbo.xml"})
@SpringBootApplication
@RestController
@MapperScan("org.example.mapper")
public class Bank1ServerApplication {

    @Autowired
    private Bank2AccountService bank2AccountService;

    public static void main(String[] args) {
        SpringApplication.run(Bank1ServerApplication.class, args);
    }

    @GetMapping("/hi")
    public String hi() {
        System.out.println("我是bank1！");
        return bank2AccountService.hi("bank1-server");
    }
}
