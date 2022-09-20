package com.Bootcamp.BankClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories("com.BootCamp.BankClient.repository")
public class BankClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankClientApplication.class, args);
	}

}
