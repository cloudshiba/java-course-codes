package io.kimmking.beandemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.kimmking.beandemo.componentscan")
public class ComponentScanConfig {
}
