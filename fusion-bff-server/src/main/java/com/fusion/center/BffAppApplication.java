package com.fusion.center;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableDubbo
@ImportResource({"classpath:dubbo/*.xml"})
public class BffAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(BffAppApplication.class, args);
    }
}