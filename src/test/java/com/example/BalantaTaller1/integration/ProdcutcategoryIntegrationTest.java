package com.example.BalantaTaller1.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;

@ContextConfiguration(classes = BalantaTaller1Application.class)
@SpringBootTest
class ProdcutcategoryIntegrationTest {
	
	private Productcategory pc;
	private SimpleDateFormat df; 
	private Date date; 
	private long time1;
	private Timestamp time;

	private ProductcategoryRepository productcategoryRepository;
	
	@Autowired
	public ProdcutcategoryIntegrationTest(ProductcategoryRepository productcategoryRepository) {
	
		this.productcategoryRepository = productcategoryRepository;
	}

	@BeforeAll
	static void init() {
		System.out.println("... TEST STARTED ...");
	}
	
	@BeforeEach
	void setUp() throws ParseException {
		
		pc = new Productcategory();
		df = new SimpleDateFormat("dd-MM-yyyy");
		date  = df.parse("24-03-2022");
		time1 = date.getTime();
		time = new Timestamp(time1);
	}
	
	@Nested
	@DisplayName("Save methods")
	class ProductcategoryServiceSave {

		@Test
		@Order(1)
		public void saveCorrectly() {
			
			pc.setProductcategoryid(7);
			pc.setName("Hogar");
			pc.setRowguid(14);
			pc.setModifieddate(time);
			
			pc.setProductsubcategories(null);
			
			
		    
			
			Productcategory temp = productcategoryRepository.save(pc);
			
			Iterable<Productcategory> productcategory = productcategoryRepository.findAll();
			Assertions.assertThat(productcategory).first().hasFieldOrPropertyWithValue("name", "Hogar");

			
			assertNotNull(temp);
			
		}
	}


	@AfterAll
	static void end() {
	}

	
	@AfterEach
	void tearDown() {
	}


}
