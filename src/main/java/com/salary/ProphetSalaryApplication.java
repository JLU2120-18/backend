package com.salary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.salary.dao")
@SpringBootApplication
public class ProphetSalaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProphetSalaryApplication.class, args);
    }

}
