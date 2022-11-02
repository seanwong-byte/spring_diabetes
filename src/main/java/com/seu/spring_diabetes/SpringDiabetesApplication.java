package com.seu.spring_diabetes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.seu.spring_diabetes.dao")
public class SpringDiabetesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDiabetesApplication.class, args);
    }

}
