package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StudentManagementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementServerApplication.class, args);
	}

}
