package com.cognizant.greengov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreenGovApplication {

	public static void main(String[] args) {
		System.out.println("green gov");
		SpringApplication.run(GreenGovApplication.class, args);
	}

}
