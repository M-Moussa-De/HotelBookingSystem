package org.example.hotelbookingsystem.api.config;

import org.example.hotelbookingsystem.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private DatabaseConfig databaseConfig;

    @GetMapping
    public String getConfig() {
        return String.format("DB URL: %s, Username: %s",
                databaseConfig.getUrl(),
                databaseConfig.getUsername());
    }
}