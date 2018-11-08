package com.manager.task;

import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@SpringBootApplication
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

    @Primary
    @Bean
    public SchedulerFactoryBean schedulerFactory() {
        Properties properties = new Properties();
        properties.put(SchedulerFactoryBean.PROP_THREAD_COUNT, "20");

        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setQuartzProperties(properties);

        return scheduler;
    }
}
