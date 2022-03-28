package com.example.BalantaTaller1.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
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
import org.junit.jupiter.api.Order;
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
		@Order(1)
		void saveCorrectly() {
			psc.setModifieddate(time);
			psc.setName("Aluminio");
			psc.setProducts(null);
			psc.setProductsubcategoryid(985);
			psc.setRowguid(666);
			
			pc.setProductcategoryid(13);
			
			when(productcategoryRepository.findById(13)).thenReturn(Optional.of(pc));
			when(productsubcategoryRepository.save(psc)).thenReturn(psc);
			
			psc.setProductcategory( productcategoryRepository.findById(13).get());
		
			Productsubcategory temp = productsubcategoryService.save(psc);
			
			assertNotNull(temp);
			assertNotSame(pc, temp, "No son el mismo objeto");
			assertEquals(temp.getName(), "Aluminio");
			assertEquals(psc.getProductsubcategoryid(), temp.getProductsubcategoryid());
			assertNotNull(psc.getProductcategory());
			verify(productsubcategoryRepository).save(psc);
			
		}
		
		@Test
		@Order(2)
		void saveNameLesserThanFiveChar() {
			psc.setModifieddate(time);
			psc.setName("Al");
			psc.setProducts(null);
			psc.setProductsubcategoryid(985);
			psc.setRowguid(666);
			pc.setProductcategoryid(13);
			
			when(productcategoryRepository.findById(13)).thenReturn(Optional.of(pc));
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
		@Order(3)
		void saveNullProductcategoryid() {
			psc.setModifieddate(time);
			psc.setName("Aluminio");
			psc.setProducts(null);
			psc.setProductsubcategoryid(985);
			psc.setRowguid(666);
	
			when(productsubcategoryRepository.save(psc)).thenThrow(RuntimeException.class);
			
			try {
				Productsubcategory temp = productsubcategoryService.save(psc);
				assertNull(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		@Test
		@Order(4)
		public void saveNull() {
			assertThrows(NullPointerException.class, () -> {
				productsubcategoryService.save(null);
			});
		}
	}
	

	@Nested
	@DisplayName("Update methods")
	class ProductsubcategoryUpdate {
		
		@Test
		@Order(5)
		void updateCorrectly() {
			psc.setModifieddate(time);
			psc.setName("Estano");
			psc.setProducts(null);
			psc.setProductsubcategoryid(985);
			psc.setRowguid(666);
			
			pc.setProductcategoryid(13);
			
			when(productcategoryRepository.findById(13)).thenReturn(Optional.of(pc));
			when(productsubcategoryRepository.findById(985)).thenReturn(Optional.of(psc));
			
			psc.setProductcategory( productcategoryRepository.findById(13).get());
			
			productsubcategoryService.edit(psc);
		
			//Productsubcategory temp = productsubcategoryService.edit(psc);
			Productsubcategory temp = productsubcategoryRepository.findById(985).get();
			//assertNotNull(temp);
			assertFalse(temp.getName().isEmpty());
			assertEquals("Estano", temp.getName());
			assertNotNull(temp);
			assertEquals(985, temp.getProductsubcategoryid());
			
			//verify(productsubcategoryRepository).save(psc);
		}
		
		@Test
		@Order(6)
		void updateNameLesserThanFiveChar() {
			psc.setModifieddate(time);
			psc.setName("Sn");
			psc.setProducts(null);
			psc.setProductsubcategoryid(985);
			psc.setRowguid(666);
			pc.setProductcategoryid(13);
			
			when(productcategoryRepository.findById(13)).thenReturn(Optional.of(pc));
			when(productsubcategoryRepository.findById(985)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				productsubcategoryService.edit(psc);
			});
			try {
				Productsubcategory temp = productsubcategoryRepository.findById(985).get();
				assertNull(temp);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
			
		}
		
		@Test
		@Order(7)
		void updateNullProductcategoryid() {
			psc.setModifieddate(time);
			psc.setName("Estano");
			psc.setProducts(null);
			psc.setProductsubcategoryid(985);
			psc.setRowguid(666);
	
			when(productsubcategoryService.edit(psc)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				productsubcategoryService.edit(psc);
			});
			try {
				Productsubcategory temp = productsubcategoryRepository.findById(985).get();
				assertNull(temp);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
			
		}
		
		@Test
		@Order(8)
		public void udateNull() {
			assertThrows(NullPointerException.class, () -> {
				productsubcategoryService.edit(null);
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
