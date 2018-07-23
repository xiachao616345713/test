package com.manager.healthindicator;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HealthIndicatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthIndicatorApplication.class, args);
    }

}
