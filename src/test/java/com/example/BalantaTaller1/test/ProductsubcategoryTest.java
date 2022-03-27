package com.example.BalantaTaller1.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.BalantaTaller1.main.BalantaTaller1Application;
//import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
//import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
import com.example.BalantaTaller1.repository.prod.ProductsubcategoryRepository;
import com.example.BalantaTaller1.service.prod.ProductsubcategoryServiceImpl;

@ContextConfiguration(classes = BalantaTaller1Application.class)
@SpringBootTest()
class ProductsubcategoryTest {
	
	private Productsubcategory psc;
	private Productcategory pc;
	private SimpleDateFormat df; 
	private Date date; 
	private long time1;
	private Timestamp time;
	//private List<Product> products;
	
	@Mock
	private ProductsubcategoryRepository productsubcategoryRepository;
	@Mock
	private ProductcategoryRepository productcategoryRepository;
	
	@InjectMocks
	private ProductsubcategoryServiceImpl productsubcategoryService;
	
	@BeforeAll
	static void start() {
		System.out.println("... TEST STARTED ...");
	}

	@BeforeEach
	void setUp() throws ParseException {
		//products = new ArrayList<Product>();
		psc = new Productsubcategory();
		pc = new Productcategory();
		df = new SimpleDateFormat("dd-MM-yyyy");
		date  = df.parse("27-03-2022");
		time1 = date.getTime();
		time = new Timestamp(time1);
	}

	
	@Nested
	@DisplayName("Save methods")
	class ProductsubcategorySave {
		
		@Test
		void saveCorrectly() {
			psc.setModifieddate(time);
			psc.setName("Aluminio");
			psc.setProducts(null);
			psc.setProductsubcategoryid(985);
			psc.setRowguid(666);
			pc.setProductcategoryid(13);
			when(productcategoryRepository.findById(13)).thenReturn(Optional.of(pc));
			when(productsubcategoryRepository.save(psc)).thenReturn(psc);
			//when(productcategoryRepository.save(pc)).thenReturn(pc);
			//productcategoryRepository.save(pc);
			//Productcategory pc2 = productcategoryRepository.findById(13).get();
			
			
			psc.setProductcategory( productcategoryRepository.findById(13).get());
		
			//assertEquals(13, pc2.getProductcategoryid());
			Productsubcategory temp = productsubcategoryService.save(psc);
			assertNotNull(temp);
			assertNotSame(pc, temp, "No son el mismo objeto");
			assertEquals(temp.getName(), "Aluminio");
			assertEquals(psc.getProductsubcategoryid(), temp.getProductsubcategoryid());
			assertNotNull(psc.getProductcategory());
			
		}
		
		@Test
		void saveNameLesserThanFiveChar() {
			psc.setModifieddate(time);
			psc.setName("Al");
			psc.setProducts(null);
			psc.setProductsubcategoryid(985);
			psc.setRowguid(666);
			pc.setProductcategoryid(13);
			when(productcategoryRepository.findById(13)).thenReturn(Optional.of(pc));
			//when(productsubcategoryRepository.save(psc)).thenReturn(psc);
			when(productsubcategoryRepository.save(psc)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				productsubcategoryService.save(psc);
			});
			
			try {
				Productsubcategory temp = productsubcategoryService.save(psc);
				assertNull(temp);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
			
		}
		
		@Test
		void saveNullProductcategoryid() {
			psc.setModifieddate(time);
			psc.setName("Aluminio");
			psc.setProducts(null);
			psc.setProductsubcategoryid(985);
			psc.setRowguid(666);
			//pc.setProductcategoryid(13);
			//when(productcategoryRepository.findById(13)).thenReturn(Optional.of(pc));
			when(productsubcategoryRepository.save(psc)).thenThrow(RuntimeException.class);
			
			try {
				Productsubcategory temp = productsubcategoryService.save(psc);
				assertNull(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		@Test
		public void saveNull() {
			assertThrows(NullPointerException.class, () -> {
				productsubcategoryService.save(null);
			});
		}
	}
	
	@AfterEach
	void tearDown() {
		psc = null;
		pc = null;
		System.gc();
	}
	
	@AfterAll
	static void end() {
		System.out.println("... TEST FINISHED ...");
	}

	

}
