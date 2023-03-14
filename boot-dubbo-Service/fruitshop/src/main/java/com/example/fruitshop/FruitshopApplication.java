package com.example.fruitshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.fruitshop.mapper")
public class FruitshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(FruitshopApplication.class, args);
    }

}
