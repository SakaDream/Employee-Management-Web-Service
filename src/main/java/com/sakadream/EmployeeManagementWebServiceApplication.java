package com.sakadream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sakadream"})
public class EmployeeManagementWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementWebServiceApplication.class, args);
	}
}
