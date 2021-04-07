package com.orange.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OrangeBootcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrangeBootcampApplication.class, args);
	}

}
