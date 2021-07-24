package io.kimmking.beandemo;

import io.kimmking.beandemo.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class XmlBeanWearDemo {
    public static void main(String[] args) {
        Object object;
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Customer customerXml = (Customer) context.getBean("customerXml");

        log.info("customerXml: {}", customerXml.getInfo());
        log.info("Today {}", customerXml.getCoffee().getInfo());
    }
}
