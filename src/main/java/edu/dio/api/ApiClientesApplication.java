package edu.dio.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiClientesApplication.class, args);
	}

}
