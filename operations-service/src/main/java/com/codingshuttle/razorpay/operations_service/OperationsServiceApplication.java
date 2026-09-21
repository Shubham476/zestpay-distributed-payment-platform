package com.codingshuttle.razorpay.operations_service;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
public class OperationsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationsServiceApplication.class, args);
    }

}
