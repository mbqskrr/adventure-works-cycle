package com.example.BalantaTaller1.testDAO;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import com.example.BalantaTaller1.dao.ProductcategoryDAO;
import com.example.BalantaTaller1.dao.ProductcategoryDAOImpl;
import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Productcategory;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = BalantaTaller1Application.class)
class ProductcategoryDAOTest {
	
	@Autowired
	private ProductcategoryDAOImpl productcategoryDAO;
	
	private Productcategory productcategory;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("... TEST STARTED ...");
	}
	
	@BeforeEach
	void setUp() throws Exception {
		productcategory = new Productcategory();
		productcategory.setName("Categoria");
		LocalDate modifieddate = LocalDate.parse("2020-01-08"/*, DateTimeFormatter.ofPattern("yyyy-MM-dd")*/);
		productcategory.setModifieddate(modifieddate);
		productcategory.setRowguid(666);
		
		
	}

	@Nested
	class DAOTest{
		
		@Test
		@Order(1)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void save() {
			assertNotNull(productcategoryDAO);
			productcategoryDAO.save(productcategory);
			assertTrue(productcategoryDAO.findById(productcategory.getProductcategoryid()).equals(productcategory));
		}
		
		@Test
		@Order(2)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void update() {
			assertNotNull(productcategoryDAO);
			productcategoryDAO.save(productcategory);
			
			productcategory.setName("Categoria Nueva :v");
			LocalDate modifieddate = LocalDate.parse("2022-01-08"/*, DateTimeFormatter.ofPattern("yyyy-MM-dd")*/);
			productcategory.setModifieddate(modifieddate);
			productcategory.setRowguid(7);
			
			Productcategory changed = productcategoryDAO.findById(productcategory.getProductcategoryid());
			
			assertAll(                
					() -> assertEquals(changed.getName(), "Categoria Nueva :v"),
					() -> assertEquals(changed.getModifieddate(), modifieddate),                 
					() -> assertEquals(changed.getRowguid(),7)
					);
			
		}
		
		@Test
		@Order(3)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findAll() {
			assertNotNull(productcategoryDAO);
			productcategoryDAO.save(productcategory);
			
			assertEquals(productcategoryDAO.findAll().size(), 1);
		}
		
	}

	@AfterEach
	void tearDown() throws Exception {
		productcategoryDAO = null;
		productcategory = null;
		System.gc();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("... TEST FINISHED ...");
	}
	
}
