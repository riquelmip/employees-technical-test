package com.employeestechnicaltest;

import com.employeestechnicaltest.util.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeesTechnicalTestApplication {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        appConfig.loadEnv();
        SpringApplication.run(EmployeesTechnicalTestApplication.class, args);
    }

}
