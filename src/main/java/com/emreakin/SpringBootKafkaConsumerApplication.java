package com.emreakin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
public class SpringBootKafkaConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaConsumerApplication.class, args);
    }
}
