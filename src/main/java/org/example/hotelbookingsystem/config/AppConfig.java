package org.example.hotelbookingsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
 public class AppConfig {

    @Value("${APP_NAME}")
    private String appName;

    @Value("${DB_URL}")
    private String dbUrl;

    @Value("${DB_USERNAME}")
    private String dbUsername;

    @Value("${DB_PASSWORD}")
    private String dbPassword;

    @Bean
    public String appInfo() {
        return String.format("App Name: %s, DB URL: %s", appName, dbUrl);
    }

    @Bean
    public DatabaseConfig databaseConfig() {
        return new DatabaseConfig(dbUrl, dbUsername, dbPassword);
    }
}