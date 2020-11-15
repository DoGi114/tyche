package com.damiannguyen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TycheApplication {

	public static void main(String[] args) {
		SpringApplication.run(TycheApplication.class, args);
	}

}
