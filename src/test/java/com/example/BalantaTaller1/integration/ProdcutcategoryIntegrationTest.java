package com.example.BalantaTaller1.integration;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private Productcategory productcategory;
	
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
		
		pc.setName("Hogar");
		pc.setRowguid(14);
		pc.setModifieddate(time);
		
		pc.setProductsubcategories(null);
		
		productcategory = productcategoryRepository.save(pc);
	}
	
	/*@Nested
	@DisplayName("Save methods")
	class ProductcategoryServiceSave {

		@Test
		@Order(1)
		public void save() {
			
			
			pc.setName("Hogar");
			pc.setRowguid(14);
			pc.setModifieddate(time);
			
			pc.setProductsubcategories(null);
			
			Productcategory temp = productcategoryRepository.save(pc);
			assertNotNull(temp);
			assertTrue(temp.getProductcategoryid() > 0);

			Iterable<Productcategory> productcategory = productcategoryRepository.findAll();
			Assertions.assertThat(productcategory).first().hasFieldOrPropertyWithValue("name", "Hogar");
			
			Productcategory pc1 = new Productcategory();
			
			pc1.setName("Todo terreno");
			pc1.setRowguid(24);
			pc1.setModifieddate(time);
			
			pc1.setProductsubcategories(null);
			
			Productcategory temp1 = productcategoryRepository.save(pc1);
			assertNotNull(temp1);
			assertEquals(2, temp1.getProductcategoryid());
		}
		
	}
	
	@Nested
	@DisplayName("Update methods")
	class ProductcategoryServiceUpdate {
		@Test
		@Order(1)
		public void update() {
			//pc.setProductcategoryid(77);
			pc.setName("Otra C");
			pc.setRowguid(14);
			pc.setModifieddate(time);
			
			pc.setProductsubcategories(null);
			
			Productcategory temp = productcategoryRepository.save(pc);
			
			pc.setName("Otra C1");
			Productcategory temp1 = productcategoryRepository.e(pc);
		}
	}*/

	@Test
	public void testFindById() {
		Productcategory result = productcategoryRepository.findById(productcategory.getProductcategoryid()).get();
		assertEquals(productcategory.getProductcategoryid(), result.getProductcategoryid());
	}
	
	@Test
	public void testFindAll() {
		List<Productcategory> result = new ArrayList<>();
		productcategoryRepository.findAll().forEach(e -> result.add(e));
		assertEquals(result.size(), 1);
	}
	
	@Test
	public void testSave() {
		Productcategory found = productcategoryRepository.findById(productcategory.getProductcategoryid()).get();
		assertEquals(productcategory.getProductcategoryid(), found.getProductcategoryid());
	}

	@AfterAll
	static void end() {
		System.out.println("... TEST STARTED ...");
	}

	@AfterEach
	void tearDown() {
		pc = null;
		productcategoryRepository = null;
		System.gc();
	}

}
