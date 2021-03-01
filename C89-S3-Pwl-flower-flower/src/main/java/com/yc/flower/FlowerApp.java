package com.yc.flower;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yc.flower.dao")
public class FlowerApp {

	public static void main(String[] args) {
		SpringApplication.run(FlowerApp.class, args);
	}
	
}
