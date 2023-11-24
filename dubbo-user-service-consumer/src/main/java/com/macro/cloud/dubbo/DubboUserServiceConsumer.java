package com.macro.cloud.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.macro.cloud.dubbo")
public class DubboUserServiceConsumer {
    public static void main(String[] args) {
        SpringApplication.run(DubboUserServiceConsumer.class, args);
    }
}
