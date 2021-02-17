package com.chatano.ticketmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TicketManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketManagerApplication.class, args);
	}
}
