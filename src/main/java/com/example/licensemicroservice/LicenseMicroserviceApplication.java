package com.example.licensemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LicenseMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenseMicroserviceApplication.class, args);
		System.out.println("test");
	}

}
