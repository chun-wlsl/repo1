package com.yc.flower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//开启申明式远程方法调用
@EnableFeignClients
public class IndexApp {

	public static void main(String[] args) {
		SpringApplication.run(IndexApp.class, args);
	}
}
