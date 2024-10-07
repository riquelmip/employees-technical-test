package com.employeestechnicaltest;

import com.employeestechnicaltest.util.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeesTechnicalTestApplication {

    public static void main(String[] args) {
        // Cargar el perfil basado en una variable de entorno
        String profile = System.getenv("SPRING_PROFILES_ACTIVE");
        if (profile != null && !profile.isEmpty()) {
            System.setProperty("spring.profiles.active", profile);
        }
        SpringApplication.run(EmployeesTechnicalTestApplication.class, args);
    }

}
