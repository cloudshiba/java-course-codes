package org.example;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ImportResource({"classpath:spring-dubbo.xml"})
@SpringBootApplication
@RestController
@MapperScan("org.example.mapper")
public class Bank2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Bank2ServerApplication.class,args);
    }
    @GetMapping("/hi")
    public String hi(){
        return "hello world!";
    }
}
