package io.kimmking.beandemo;

import io.kimmking.beandemo.componentscan.Pet;
import io.kimmking.beandemo.config.ComponentScanConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class ComponentScanBeanWearDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);

        Pet pet = context.getBean(Pet.class);

        log.info("A {}", pet.getInfo());
    }
}
