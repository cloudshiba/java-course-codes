package com.cloudshiba.jdbcplay.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data @NoArgsConstructor @AllArgsConstructor
@Configuration @ConfigurationProperties(prefix = "db")
public class JdbcProperty {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public String getFullUrl() {
        StringBuffer reultUrl = new StringBuffer();
        reultUrl.append(url).append("?")
                .append("user=").append(username)
                .append("&password=").append(password);
        return reultUrl.toString();
    }
}
