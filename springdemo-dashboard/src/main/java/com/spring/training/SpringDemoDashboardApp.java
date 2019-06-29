package com.spring.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages="com.spring.training.model")
public class SpringDemoDashboardApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoDashboardApp.class, args);
	}

}
