package org.example.hotelbookingsystem.config;
public class DatabaseConfig {
    private final String url;
    private final String username;
    private final String password;

    public DatabaseConfig(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Object getUrl() {
        return url;
    }

    public Object getUsername() {
        return username;
    }
}