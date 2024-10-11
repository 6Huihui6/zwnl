package com.zwnl.ports;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zwnl.ports.mapper")
public class PortsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortsApplication.class, args);
    }
}
