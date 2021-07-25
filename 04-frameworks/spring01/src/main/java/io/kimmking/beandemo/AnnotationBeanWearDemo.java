package io.kimmking.beandemo;

import io.kimmking.beandemo.config.AnnotationCustomerConfig;
import io.kimmking.beandemo.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class AnnotationBeanWearDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationCustomerConfig.class);
        Customer customer = context.getBean(Customer.class);

        log.info("Customer: {}", customer.getInfo());
        log.info("Today {}", customer.getCoffee().getInfo());
    }
}
