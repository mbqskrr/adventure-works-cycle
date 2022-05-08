package com.example.BalantaTaller1.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.BalantaTaller1.model.user.User;
import com.example.BalantaTaller1.model.user.UserType;
import com.example.BalantaTaller1.service.user.UserServiceImpl;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.BalantaTaller1"})
@EnableJpaRepositories(basePackages = "com.example.BalantaTaller1")
@EntityScan(basePackages = "com.example.BalantaTaller1")
public class BalantaTaller1Application {

	/*
	 * public static void main(String[] args) {
	 * 
	 * SpringApplication.run(BalantaTaller1Application.class, args); }
	 */
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BalantaTaller1Application.class, args);
		UserServiceImpl user= context.getBean(UserServiceImpl.class);
		
		User u= new User();
		u.setUsername("El bicho");
		u.setPassword("{noop}SIU7");
		u.setType(UserType.administrator);
		
		user.save(u);
	}
}
