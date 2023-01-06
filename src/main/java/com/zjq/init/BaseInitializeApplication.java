package com.zjq.init;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.zjq.init.mapper")
@SpringBootApplication
public class BaseInitializeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseInitializeApplication.class, args);
    }

}
