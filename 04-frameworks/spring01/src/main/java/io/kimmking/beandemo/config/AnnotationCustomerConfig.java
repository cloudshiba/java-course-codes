package io.kimmking.beandemo.config;

import io.kimmking.beandemo.model.Coffee;
import io.kimmking.beandemo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationCustomerConfig {
    @Bean
    public Customer customerAnnotation() {
        Customer customer = new Customer();
        customer.setId(222222L);
        customer.setName("CustomerBean");
        customer.setBirthday("2000-04-09");
        return customer;
    }

    @Bean
    public Coffee coffeeAnnotation() {
        Coffee coffee = new Coffee();
        coffee.setName("Latte Coffee");
        return coffee;
    }
}
