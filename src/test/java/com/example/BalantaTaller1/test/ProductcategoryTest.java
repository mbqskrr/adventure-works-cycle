package com.example.BalantaTaller1.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Date;
//import java.util.List;
import java.util.Optional;

//import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Productcategory;
//import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;

@ContextConfiguration(classes = BalantaTaller1Application.class)
@SpringBootTest()
class ProductcategoryTest {

	
	private Productcategory pc;
	private SimpleDateFormat df; 
	private Date date; 
	private long time1;
	private Timestamp time;
	//private List<Productsubcategory> productsubcategories;
	
	@Mock
	private ProductcategoryRepository productcategoryRepository;
	@InjectMocks
	private ProductcategoryServiceImpl productcategoryService;

	@BeforeAll
	static void start() {
		System.out.println("... TEST STARTED ...");
	}
	
	@BeforeEach
	void setUp() throws ParseException {
		//productsubcategories = new ArrayList<Productsubcategory>();
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
			
			when(productcategoryRepository.save(pc)).thenReturn(pc);
			
			try {
				productcategoryService.save(pc);
				
			}catch(RuntimeException rte) {
				rte.printStackTrace();
			}
			Productcategory testPc =productcategoryService.save(pc);
			assertNotNull(testPc);
			assertFalse(testPc.getName().isEmpty());
			assertEquals(testPc.getProductcategoryid(), 7);
			assertNull(testPc.getProductsubcategories());
			assertEquals(testPc.getName(), "Hogar");
			assertEquals(testPc.getModifieddate(), time);
			verify(productcategoryRepository, VerificationModeFactory.times(2)).save(pc);
			
			
		}
		
		@Test
		@Order(2)
		public void saveWithNameLesserThanThreeChar() {
			
			
			pc.setProductcategoryid(6);
			pc.setName("I");
			pc.setRowguid(13);
			pc.setModifieddate(time);
			pc.setProductsubcategories(null);
			when(productcategoryRepository.findById(6)).thenThrow(RuntimeException.class);
			
			try {
				assertThrows(RuntimeException.class, ()->{
					productcategoryService.save(pc);
				});
				
				Productcategory tempPc =productcategoryService.save(pc);
				assertNull(tempPc);
			}catch(RuntimeException rte) {
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
	class ProductcategoryServiceEdit{
		@Test
		@Order(4)
		public void updateCorrectly() {
			pc.setProductcategoryid(77);
			pc.setName("Otra C");
			pc.setRowguid(14);
			pc.setModifieddate(time);
			
			pc.setProductsubcategories(null);
			
			when(productcategoryRepository.findById(77)).thenReturn(Optional.of(pc));
			//when(productcategoryRepository.save(pc)).thenReturn(pc);
			
			productcategoryService.edit(pc);
			//Productcategory test = productcategoryService.edit(pc);
			
			Productcategory test = productcategoryRepository.findById(77).get();
			
			assertNotNull(test);
			assertFalse(test.getName().isEmpty());
			assertEquals(test.getProductcategoryid(), 77);
			assertNull(test.getProductsubcategories());
			assertEquals(test.getName(), "Otra C");
			assertEquals(test.getModifieddate(), time);
			verify(productcategoryRepository, VerificationModeFactory.times(2)).findById(77);
		}
		
		@Test
		@Order(5)
		public void updateNameLesserThanThreeChar() {
			pc.setProductcategoryid(42);
			pc.setName("NB");
			pc.setRowguid(13);
			pc.setModifieddate(time);
			pc.setProductsubcategories(null);
			
			when(productcategoryRepository.findById(42)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, ()->{
				productcategoryService.edit(pc);
			});
			try {
				Productcategory tempPc = productcategoryRepository.findById(42).get();
				assertNull(tempPc);
			}catch(RuntimeException rte) {
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
	}

	@AfterAll
	static void finish() {
		System.out.println("... TEST FINISHED ...");
	}
	
	@AfterEach
	void tearDown() {
		pc = null;
		System.gc();
	}

}
