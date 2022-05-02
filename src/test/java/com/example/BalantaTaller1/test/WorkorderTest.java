package com.example.BalantaTaller1.test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Scrapreason;
import com.example.BalantaTaller1.model.prod.Workorder;
import com.example.BalantaTaller1.repository.prod.ProductRepository;
import com.example.BalantaTaller1.repository.prod.ScrapreasonRepository;
import com.example.BalantaTaller1.repository.prod.WorkorderRepository;
import com.example.BalantaTaller1.service.prod.WorkorderServiceImpl;

@ContextConfiguration(classes = BalantaTaller1Application.class)
@SpringBootTest()
class WorkorderTest {
	
	private Workorder wo;
	private Scrapreason sr;
	private Product p;
	private SimpleDateFormat df; 
	private Date date; 
	private long time1;
	private Timestamp modifieddate;
	
	private SimpleDateFormat df1; 
	private Date date1; 
	private long time2;
	private Timestamp duedate;
	
	private SimpleDateFormat df2; 
	private Date date2; 
	private long time3;
	private Timestamp startdate;
	
	private SimpleDateFormat df3; 
	private Date date3; 
	private long time4;
	private Timestamp enddate;
	
	@Mock
	private WorkorderRepository workorderRepository;
	@Mock
	private ScrapreasonRepository scrapreasonRepository;
	@Mock
	private ProductRepository productRepository;
	@InjectMocks
	private WorkorderServiceImpl workorderService;

	@BeforeAll
	static void start() {
		System.out.println("... TEST STARTED ...");
	}

	@BeforeEach
	void setUp() throws ParseException {
		wo = new Workorder();
		sr = new Scrapreason();
		p = new Product();
		
		df = new SimpleDateFormat("dd-MM-yyyy");
		date  = df.parse("27-03-2022");
		time1 = date.getTime();
		modifieddate = new Timestamp(time1);
		
		df1 = new SimpleDateFormat("dd-MM-yyyy");
		date1  = df1.parse("06-08-2023");
		time2 = date1.getTime();
		duedate = new Timestamp(time2);
		
		df2 = new SimpleDateFormat("dd-MM-yyyy");
		date2  = df2.parse("30-03-2022");
		time3 = date2.getTime();
		startdate = new Timestamp(time3);
		
		df3 = new SimpleDateFormat("dd-MM-yyyy");
		date3  = df3.parse("13-04-2022");
		time4 = date3.getTime();
		enddate = new Timestamp(time4);
		
	}

	@Nested
	@DisplayName("Save methods")
	class WorkorderSave {
		@Test
		@Order(1)
		void saveCorrectly() {
			wo.setDuedate(duedate);
			wo.setEnddate(enddate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(3);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			sr.setScrapreasonid(69);
			
			p.setProductid(777);
			
			when(scrapreasonRepository.findById(69)).thenReturn(Optional.of(sr));
			when(productRepository.findById(777)).thenReturn(Optional.of(p));
			
			wo.setProduct(productRepository.findById(777).get());
			wo.setScrapreason(scrapreasonRepository.findById(69).get());
			
			when(workorderRepository.save(wo)).thenReturn(wo);
			
			Workorder temp = workorderService.save(wo);
			assertNotNull(temp);
			assertEquals(123, temp.getWorkorderid());
			assertFalse(temp.getStartdate().after(temp.getEnddate()));
			assertTrue(temp.getOrderqty()>0 & temp.getScrappedqty()>0);
			verify(workorderRepository).save(wo);
		}
		
		@Test
		@Order(2)
		void saveNegativeOrderQty(){
			wo.setDuedate(duedate);
			wo.setEnddate(enddate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(-4);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			sr.setScrapreasonid(69);
			
			p.setProductid(777);
			
			when(scrapreasonRepository.findById(69)).thenReturn(Optional.of(sr));
			when(productRepository.findById(777)).thenReturn(Optional.of(p));
			
			wo.setProduct(productRepository.findById(777).get());
			wo.setScrapreason(scrapreasonRepository.findById(69).get());
			
			when(workorderRepository.save(wo)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				workorderService.save(wo);
			});
			try {
				Workorder temp = workorderService.save(wo);
				assertNull(temp);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			
		}
		
		@Test
		@Order(3)
		void saveNegativeScrappedQty() {
			wo.setDuedate(duedate);
			wo.setEnddate(enddate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(4);
			wo.setScrappedqty(-8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			sr.setScrapreasonid(69);
			
			p.setProductid(777);
			
			when(scrapreasonRepository.findById(69)).thenReturn(Optional.of(sr));
			when(productRepository.findById(777)).thenReturn(Optional.of(p));
			
			wo.setProduct(productRepository.findById(777).get());
			wo.setScrapreason(scrapreasonRepository.findById(69).get());
			
			when(workorderRepository.save(wo)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				workorderService.save(wo);
			});
			try {
				Workorder temp = workorderService.save(wo);
				assertNull(temp);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		
		@Test
		@Order(4)
		void saveStartdateGreaterThanEnddate() {
			wo.setDuedate(duedate);
			wo.setEnddate(startdate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(enddate);
			wo.setOrderqty(4);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			sr.setScrapreasonid(69);
			
			p.setProductid(777);
			
			when(scrapreasonRepository.findById(69)).thenReturn(Optional.of(sr));
			when(productRepository.findById(777)).thenReturn(Optional.of(p));
			
			wo.setProduct(productRepository.findById(777).get());
			wo.setScrapreason(scrapreasonRepository.findById(69).get());
			
			when(workorderRepository.save(wo)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				workorderService.save(wo);
			});
			try {
				Workorder temp = workorderService.save(wo);
				assertNull(temp);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		
		@Test
		@Order(5)
		void saveNullProductid() {
			wo.setDuedate(duedate);
			wo.setEnddate(startdate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(enddate);
			wo.setOrderqty(4);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			sr.setScrapreasonid(69);
			
			when(scrapreasonRepository.findById(69)).thenReturn(Optional.of(sr));
			
			wo.setScrapreason(scrapreasonRepository.findById(69).get());
			
			when(workorderRepository.save(wo)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				workorderService.save(wo);
			});
			try {
				Workorder temp = workorderService.save(wo);
				assertNull(temp);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		
		@Test
		@Order(6)
		void saveNullScrapreasonid() {
			wo.setDuedate(duedate);
			wo.setEnddate(startdate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(enddate);
			wo.setOrderqty(4);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			p.setProductid(777);
			
			when(productRepository.findById(777)).thenReturn(Optional.of(p));
			
			wo.setProduct(productRepository.findById(777).get());
			
			when(workorderRepository.save(wo)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				workorderService.save(wo);
			});
			try {
				Workorder temp = workorderService.save(wo);
				assertNull(temp);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		
		@Test
		@Order(7)
		public void saveNull() {
			assertThrows(NullPointerException.class, () -> {
				workorderService.save(null);
			});
		}
	}
	
	@Nested
	@DisplayName("Update methods")
	class WorkorderUpdate {
		@Test
		@Order(8)
		void updateCorrectly() {
			wo.setDuedate(duedate);
			wo.setEnddate(enddate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(5);
			wo.setScrappedqty(10);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			sr.setScrapreasonid(69);
			
			p.setProductid(777);
			
			when(scrapreasonRepository.findById(69)).thenReturn(Optional.of(sr));
			when(productRepository.findById(777)).thenReturn(Optional.of(p));
			
			wo.setProduct(productRepository.findById(777).get());
			wo.setScrapreason(scrapreasonRepository.findById(69).get());
			
			when(workorderRepository.findById(123)).thenReturn(Optional.of(wo));
			
			workorderService.edit(wo);
			
			Workorder temp = workorderRepository.findById(123).get();
			assertNotNull(temp);
			assertEquals(123, temp.getWorkorderid());
			assertFalse(temp.getStartdate().after(temp.getEnddate()));
			assertTrue(temp.getOrderqty()>0 & temp.getScrappedqty()>0);
			assertEquals(69, temp.getScrapreason().getScrapreasonid());
			verify(workorderRepository, VerificationModeFactory.times(2)).findById(123);
		}
		
		@Test
		@Order(9)
		void updateNegativeOrderQty(){
			wo.setDuedate(duedate);
			wo.setEnddate(enddate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(-4);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			sr.setScrapreasonid(69);
			
			p.setProductid(777);
			
			when(scrapreasonRepository.findById(69)).thenReturn(Optional.of(sr));
			when(productRepository.findById(777)).thenReturn(Optional.of(p));
			
			wo.setProduct(productRepository.findById(777).get());
			wo.setScrapreason(scrapreasonRepository.findById(69).get());
			
			when(workorderRepository.findById(123)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				workorderService.edit(wo);
			});
			try {
				Workorder temp = workorderRepository.findById(123).get();
				assertNull(temp);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			
		}
		
		@Test
		@Order(10)
		void updateNegativeScrappedQty() {
			wo.setDuedate(duedate);
			wo.setEnddate(enddate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(4);
			wo.setScrappedqty(-8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			sr.setScrapreasonid(69);
			
			p.setProductid(777);
			
			when(scrapreasonRepository.findById(69)).thenReturn(Optional.of(sr));
			when(productRepository.findById(777)).thenReturn(Optional.of(p));
			
			wo.setProduct(productRepository.findById(777).get());
			wo.setScrapreason(scrapreasonRepository.findById(69).get());
			
			when(workorderRepository.findById(123)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				workorderService.edit(wo);
			});
			try {
				Workorder temp = workorderRepository.findById(123).get();
				assertNull(temp);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		
		@Test
		@Order(11)
		void updateStartdateGreaterThanEnddate() {
			wo.setDuedate(duedate);
			wo.setEnddate(startdate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(enddate);
			wo.setOrderqty(4);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			sr.setScrapreasonid(69);
			
			p.setProductid(777);
			
			when(scrapreasonRepository.findById(69)).thenReturn(Optional.of(sr));
			when(productRepository.findById(777)).thenReturn(Optional.of(p));
			
			wo.setProduct(productRepository.findById(777).get());
			wo.setScrapreason(scrapreasonRepository.findById(69).get());
			
			
			when(workorderRepository.findById(123)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				workorderService.edit(wo);
			});
			try {
				Workorder temp = workorderRepository.findById(123).get();
				assertNull(temp);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			
		}
		
		@Test
		@Order(12)
		void updateNullScrapreasonid() {
			wo.setDuedate(duedate);
			wo.setEnddate(startdate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(enddate);
			wo.setOrderqty(4);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			p.setProductid(777);
			
			when(productRepository.findById(777)).thenReturn(Optional.of(p));
			
			wo.setProduct(productRepository.findById(777).get());
			
			when(workorderRepository.findById(123)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				workorderService.edit(wo);
			});
			try {
				Workorder temp = workorderRepository.findById(123).get();
				assertNull(temp);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		
		@Test
		@Order(13)
		void updateNullProductid() {
			wo.setDuedate(duedate);
			wo.setEnddate(startdate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(enddate);
			wo.setOrderqty(4);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			
			sr.setScrapreasonid(69);
			
			when(scrapreasonRepository.findById(69)).thenReturn(Optional.of(sr));
			
			wo.setScrapreason(scrapreasonRepository.findById(69).get());
			
			when(workorderRepository.findById(123)).thenThrow(RuntimeException.class);
			
			assertThrows(RuntimeException.class, () -> {
				workorderService.edit(wo);
			});
			try {
				Workorder temp = workorderRepository.findById(123).get();
				assertNull(temp);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		
		@Test
		@Order(14)
		public void updateNull() {
			assertThrows(NullPointerException.class, () -> {
				workorderService.edit(null);
			});
		}
	}
	
	
	@AfterEach
	void tearDown() {
		wo = null;
		p = null;
		sr = null;
		System.gc();
	}
	
	@AfterAll
	static void end() {
		System.out.println("... TEST FINISHED ...");
	}


}
