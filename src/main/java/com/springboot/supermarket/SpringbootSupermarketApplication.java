package com.springboot.supermarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描这个包下的所有mapper 并且给所有mapper加上 @Mapper 注解
@MapperScan(value = "com.springboot.supermarket.mapper")
@SpringBootApplication
public class SpringbootSupermarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSupermarketApplication.class, args);
    }

}
