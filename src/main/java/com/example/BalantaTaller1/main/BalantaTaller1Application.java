package com.example.BalantaTaller1.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.user.User;
import com.example.BalantaTaller1.model.user.UserType;
import com.example.BalantaTaller1.service.prod.ProductServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;
import com.example.BalantaTaller1.service.user.UserServiceImpl;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.BalantaTaller1.repository.prod" , "com.example.BalantaTaller1.repository.user"})
@EntityScan(basePackages = {"com.example.BalantaTaller1.model.prod",  "com.example.BalantaTaller1.model.validation",
		"com.example.BalantaTaller1.model.user", "com.example.BalantaTaller1.auth"})
@ComponentScan(basePackages = {"com.example.BalantaTaller1.controller", "com.example.BalantaTaller1.auth", 
		"com.example.BalantaTaller1.repository.prod", "com.example.BalantaTaller1.repository.user", 
		"com.example.BalantaTaller1.service.prod", "com.example.BalantaTaller1.service.user", 
		"com.example.BalantaTaller1.model.prod", "com.example.BalantaTaller1.model.validation", "com.example.BalantaTaller1.dao"})
public class BalantaTaller1Application {

	/*
	 * public static void main(String[] args) {
	 * 
	 * SpringApplication.run(BalantaTaller1Application.class, args); }
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(BalantaTaller1Application.class, args);
		/*
		 * UserServiceImpl user= context.getBean(UserServiceImpl.class);
		 * 
		 * User u= new User(); u.setUsername("admin"); u.setPassword("{noop}admin");
		 * u.setType(UserType.administrator);
		 * 
		 * user.save(u);
		 * 
		 * User u1= new User(); u1.setUsername("op"); u1.setPassword("{noop}op");
		 * u1.setType(UserType.operator);
		 * 
		 * user.save(u1);
		 */
		
	}
	
	@Bean
	public CommandLineRunner add(UserServiceImpl user, ProductcategoryServiceImpl productcategoryService, ProductServiceImpl productService){
		return (args) -> {
			
			
			User u= new User();
			u.setUsername("admin1");
			u.setPassword("{noop}admin1");
			u.setType(UserType.administrator);
			
			user.save(u);
			
			User u1= new User();
			u1.setUsername("op");
			u1.setPassword("{noop}op");
			u1.setType(UserType.operator);
			
			user.save(u1);
			
			Productmodel pm = new Productmodel();
			pm.setName("Modelo de producto");
			productService.saveProductmodel(pm);
			
			
			
		};
		
	}
}
