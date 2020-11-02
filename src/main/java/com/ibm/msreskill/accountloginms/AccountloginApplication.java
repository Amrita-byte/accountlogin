package com.ibm.msreskill.accountloginms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableEurekaClient
@ComponentScan("com.ibm.msreskill.accountloginms.*")
public class AccountloginApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountloginApplication.class, args);
	}

}
