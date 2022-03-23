package com.example.BalantaTaller1.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class BalantaTaller1Application {

	public static void main(String[] args) {
		SpringApplication.run(BalantaTaller1Application.class, args);
	}

}
