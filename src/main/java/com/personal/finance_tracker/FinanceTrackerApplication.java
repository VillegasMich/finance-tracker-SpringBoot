package com.personal.finance_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceTrackerApplication.class, args);
		System.out.println("Check documentation at http://localhost:8080/swagger-ui.html");
		System.out.println("Server running on localhost:8080");
	}

}
