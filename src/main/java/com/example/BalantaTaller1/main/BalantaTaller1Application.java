package com.example.BalantaTaller1.main;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Scrapreason;
import com.example.BalantaTaller1.model.prod.Unitmeasure;
import com.example.BalantaTaller1.model.prod.Workorder;
import com.example.BalantaTaller1.model.user.UserAWC;
import com.example.BalantaTaller1.model.user.UserType;
import com.example.BalantaTaller1.service.prod.ProductServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductsubcategoryServiceImpl;
import com.example.BalantaTaller1.service.prod.WorkorderServiceImpl;
import com.example.BalantaTaller1.service.user.UserServiceImpl;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.example.BalantaTaller1.repository.prod",
		"com.example.BalantaTaller1.repository.user" })
@EntityScan(basePackages = { "com.example.BalantaTaller1.model.prod", "com.example.BalantaTaller1.model.validation",
		"com.example.BalantaTaller1.model.user", "com.example.BalantaTaller1.auth" })
@ComponentScan(basePackages = { "com.example.BalantaTaller1.controller", "com.example.BalantaTaller1.auth",
		"com.example.BalantaTaller1.repository.prod", "com.example.BalantaTaller1.repository.user",
		"com.example.BalantaTaller1.service.prod", "com.example.BalantaTaller1.service.user",
		"com.example.BalantaTaller1.model.prod", "com.example.BalantaTaller1.model.validation",
		"com.example.BalantaTaller1.dao" })
public class BalantaTaller1Application {

	public static void main(String[] args) {

		/* ConfigurableApplicationContext context = */ SpringApplication.run(BalantaTaller1Application.class, args);

	}

	@Bean
	public CommandLineRunner add(ProductcategoryServiceImpl productcategoryService, ProductServiceImpl productService,
			UserServiceImpl user, ProductsubcategoryServiceImpl productsubcategoryService,
			WorkorderServiceImpl workorderService) {
		return (args) -> {

			UserAWC u = new UserAWC();
			u.setUsername("admin");
			u.setPassword("{noop}admin");
			u.setType(UserType.administrator);
			user.save(u);

			UserAWC u1 = new UserAWC();
			u1.setUsername("op");
			u1.setPassword("{noop}op");
			u1.setType(UserType.operator);
			user.save(u1);

			Productcategory pc = new Productcategory();
			LocalDate time = LocalDate.parse("2022-03-24");
			pc.setName("Hogar");
			pc.setRowguid(14);
			pc.setModifieddate(time);
			pc.setProductsubcategories(null);
			productcategoryService.save(pc);

			Productsubcategory psc = new Productsubcategory();
			LocalDate time1 = LocalDate.parse("2022-04-28");
			psc.setModifieddate(time1);
			psc.setName("Aluminio");
			psc.setRowguid(666);
			psc.setProductcategory(pc);
			productsubcategoryService.save(psc);

			Productmodel pm = new Productmodel();
			pm.setName("Modelo");
			productService.saveProductmodel(pm);

			Unitmeasure um1 = new Unitmeasure();
			um1.setName("cm");
			productService.saveUnitmeasure(um1);

			Unitmeasure um2 = new Unitmeasure();
			um2.setName("kg");
			productService.saveUnitmeasure(um2);

			Scrapreason scrapreason = new Scrapreason();
			scrapreason.setName("None");
			workorderService.saveScrapreason(scrapreason);

			Product p = new Product();
			LocalDate sellstartdate = LocalDate.parse("2022-03-30");
			LocalDate sellenddate = LocalDate.parse("2022-09-13");
			p.setSellstartdate(sellstartdate);
			p.setSellenddate(sellenddate);
			p.setProductnumber("4");
			p.setWeight(new BigDecimal(20));
			p.setSize("120");
			p.setDaystomanufacture(8);
			p.setName("Makinon");
			p.setProductsubcategory(psc);
			p.setProductmodel(pm);
			p.setUnitmeasure1(um1);
			p.setUnitmeasure2(um2);
			productService.save(p);

			Workorder wo = new Workorder();
			LocalDate startdate = LocalDate.parse("2022-03-30");
			LocalDate enddate = LocalDate.parse("2023-04-13");
			wo.setEnddate(enddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(3);
			wo.setScrappedqty(0);
			wo.setProduct(p);
			wo.setScrapreason(scrapreason);
			workorderService.save(wo);

		};

	}
}
