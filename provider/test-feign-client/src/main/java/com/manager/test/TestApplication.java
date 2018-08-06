package com.manager.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author chao
 * @since 2018-07-25
 */
@EnableCircuitBreaker
@EnableFeignClients(basePackages = {"com.manager.client"})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.manager.test","com.manager.plugins","com.manager.client"})
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
