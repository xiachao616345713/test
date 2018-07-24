package com.manager.common;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages={"com.manager.common.mapper"})
public class CommonProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonProviderApplication.class, args);
    }

}
