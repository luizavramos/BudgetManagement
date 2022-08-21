package com.budget.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class
BudgetManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetManagementApplication.class, args);
	}

}
