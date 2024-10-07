package com.employeestechnicaltest.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    public void loadEnv() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("SPRING_DATASOURCE_HOST", dotenv.get("SPRING_DATASOURCE_HOST"));
        System.setProperty("SPRING_DATASOURCE_DBNAME", dotenv.get("SPRING_DATASOURCE_DBNAME"));
        System.setProperty("SPRING_DATASOURCE_PORT", dotenv.get("SPRING_DATASOURCE_PORT"));
        System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
        System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
    }
}