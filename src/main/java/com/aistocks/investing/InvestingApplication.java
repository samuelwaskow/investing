package com.aistocks.investing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InvestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestingApplication.class, args);
	}

}
