package com.clooudshiba.dynamicdatasource.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "datasource.secondary")
public class SecondaryDataSourceConfig {
    private String url;
    private String username;
    private String password;
}
