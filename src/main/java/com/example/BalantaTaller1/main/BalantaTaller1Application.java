package com.example.BalantaTaller1.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.BalantaTaller1"})
@EnableJpaRepositories(basePackages = "com.example.BalantaTaller1")
@EntityScan(basePackages = "com.example.BalantaTaller1")
public class BalantaTaller1Application {

	public static void main(String[] args) {
		
		SpringApplication.run(BalantaTaller1Application.class, args);
	}

}
