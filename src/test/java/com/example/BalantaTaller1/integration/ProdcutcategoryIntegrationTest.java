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
import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;

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
	private ProductcategoryServiceImpl productcategoryService;
	
	@Autowired
	public ProdcutcategoryIntegrationTest(ProductcategoryRepository productcategoryRepository, ProductcategoryServiceImpl productcategoryService) {
		this.productcategoryRepository = productcategoryRepository;
		this.productcategoryService = productcategoryService;
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
		
		/*
		 * pc.setName("Hogar"); pc.setRowguid(14); pc.setModifieddate(time);
		 * 
		 * pc.setProductsubcategories(null);
		 * 
		 * productcategory = productcategoryService.save(pc);
		 */
	}
	
	@Nested
	@DisplayName("Save method test")
	class IntegrationTestSave {
		
		/*
		 * @Test
		 * 
		 * @Order(1)
		 * 
		 * @DisplayName("findById") public void testFindById() { Productcategory result
		 * = productcategoryRepository.findById(productcategory.getProductcategoryid()).
		 * get(); assertNotNull(result);
		 * assertEquals(productcategory.getProductcategoryid(),
		 * result.getProductcategoryid()); }
		 * 
		 * @Test
		 * 
		 * @Order(2)
		 * 
		 * @DisplayName("findAll") public void testFindAll() { List<Productcategory>
		 * result = new ArrayList<>(); productcategoryRepository.findAll().forEach(e ->
		 * result.add(e)); assertEquals(result.size(), 1);
		 * Assertions.assertThat(result).first().hasFieldOrPropertyWithValue("name",
		 * "Hogar"); }
		 */
		
		@Test
		@Order(1)
		public void saveCorrectly() {
			pc.setName("Hogar");
			pc.setRowguid(14);
			pc.setModifieddate(time);
			
			pc.setProductsubcategories(null);
			
			productcategory = productcategoryService.save(pc);
			
			Productcategory found = productcategoryRepository.findById(productcategory.getProductcategoryid()).get();
			assertNotNull(found);
			assertEquals(productcategory.getProductcategoryid(), found.getProductcategoryid());
			Assertions.assertThat(found).isInstanceOfAny(Productcategory.class);
			Assertions.assertThat(found.getProductcategoryid()).isGreaterThan(0);
			Assertions.assertThat(found).hasNoNullFieldsOrProperties();
			assertEquals(found.getName(), "Hogar");
		}
		
		@Test
		@Order(2)
		public void saveWithNameLesserThanThreeChar() {
			pc.setProductcategoryid(6);
			pc.setName("I");
			pc.setRowguid(13);
			pc.setModifieddate(time);
			pc.setProductsubcategories(null);
			
			try {
				productcategory = productcategoryService.save(pc);
				Productcategory found = productcategoryRepository.findById(productcategory.getProductcategoryid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		
		}
		
		@Test
		@Order(3)
		public void saveNull() {
			assertThrows(NullPointerException.class, () -> {
				productcategoryService.save(null);
			});
		}

	}
	
	@Nested
	@DisplayName("Update methods")
	class IntegrationTestEdit{
		
		@BeforeEach
		public void setUpUpdate() {
			pc.setName("Hogar");
			pc.setRowguid(14);
			pc.setModifieddate(time);
			
			pc.setProductsubcategories(null);
			
			productcategoryService.save(pc);
		}
		
		@Test
		@Order(4)
		public void updateCorrectly() {
			
			Productcategory pc1 = new Productcategory();
			
			pc1.setProductcategoryid(1);
			pc1.setName("Otra C");
			pc1.setRowguid(14);
			pc1.setModifieddate(time);
			
			pc1.setProductsubcategories(null);
			
			productcategory = productcategoryService.edit(pc1);
			
			Productcategory found = productcategoryRepository.findById(productcategory.getProductcategoryid()).get();
			assertNotNull(found);
			assertEquals(productcategory.getProductcategoryid(), found.getProductcategoryid());
			Assertions.assertThat(found).isInstanceOfAny(Productcategory.class);
			Assertions.assertThat(found.getProductcategoryid()).isGreaterThan(0);
			Assertions.assertThat(found).hasNoNullFieldsOrProperties();
			assertEquals(found.getName(), "Otra C");
		}
		
		@Test
		@Order(5)
		public void updateNameLesserThanThreeChar() {
			
			Productcategory pc1 = new Productcategory();
			
			pc1.setProductcategoryid(1);
			pc1.setName("NB");
			pc1.setRowguid(13);
			pc1.setModifieddate(time);
			pc1.setProductsubcategories(null);
			
			
			try {
				productcategory = productcategoryService.edit(pc1);
				Productcategory found = productcategoryRepository.findById(productcategory.getProductcategoryid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
			
		}
		
		@Test
		@Order(6)
		public void updateNull() {
			assertThrows(NullPointerException.class, () -> {
				productcategoryService.edit(null);
			});
		}
		
		@Test
		@Order(7)
		public void updateNonExistingId() {
			Productcategory pc1 = new Productcategory();
			
			pc1.setProductcategoryid(85);
			pc1.setName("Mountain");
			pc1.setRowguid(13);
			pc1.setModifieddate(time);
			pc1.setProductsubcategories(null);
			productcategory = productcategoryService.edit(pc1);
			assertThrows(NullPointerException.class, () -> {
				productcategoryRepository.findById(productcategory.getProductcategoryid()).get();
			});
			//Productcategory found = productcategoryRepository.findById(productcategory.getProductcategoryid()).get();
			//assertNotNull(found);
		}
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
