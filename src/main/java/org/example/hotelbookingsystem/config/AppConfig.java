package org.example.hotelbookingsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
 public class AppConfig {
   @Value("${spring.application.name}")
   private String appName;

   @Value("${spring.datasource.url}")
   private String dbUrl;

   @Value("${spring.datasource.username}")
   private String dbUsername;

   @Value("${spring.datasource.password}")
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