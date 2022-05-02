package com.example.BalantaTaller1.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
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
import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Scrapreason;
import com.example.BalantaTaller1.model.prod.Unitmeasure;
import com.example.BalantaTaller1.model.prod.Workorder;
import com.example.BalantaTaller1.repository.prod.ProductRepository;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
import com.example.BalantaTaller1.repository.prod.ProductmodelRepository;
import com.example.BalantaTaller1.repository.prod.ProductsubcategoryRepository;
import com.example.BalantaTaller1.repository.prod.ScrapreasonRepository;
import com.example.BalantaTaller1.repository.prod.UnitmeasureRepository;
import com.example.BalantaTaller1.repository.prod.WorkorderRepository;
import com.example.BalantaTaller1.service.prod.WorkorderServiceImpl;


@ContextConfiguration(classes = BalantaTaller1Application.class)
@SpringBootTest
class WorkoderIntegartionTest {
	
	private Workorder wo;
	private Scrapreason sr;
	private Product p;
	private Unitmeasure um1;
	private Unitmeasure um2;
	private Workorder workorder;
	private Productsubcategory psc;
	private Productmodel pm;
	private Productcategory pc;
	
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
	
	private WorkorderRepository workorderRepository;
	private ScrapreasonRepository scrapreasonRepository;
	private ProductRepository productRepository;
	private ProductmodelRepository productmodelRepository;
	private UnitmeasureRepository unitmeasureRepository;
	private ProductcategoryRepository productcategoryRepository;
	private ProductsubcategoryRepository productsubcategoryRepository;
	
	private WorkorderServiceImpl workorderService;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("... TEST STARTED ...");
	}
	
	@Autowired
	public WorkoderIntegartionTest(WorkorderRepository workorderRepository, ScrapreasonRepository scrapreasonRepository,
			ProductRepository productRepository, UnitmeasureRepository unitmeasureRepository,
			ProductmodelRepository productmodelRepository, ProductcategoryRepository productcategoryRepository,
			WorkorderServiceImpl workorderService, ProductsubcategoryRepository productsubcategoryRepository) {
		this.workorderRepository = workorderRepository;
		this.scrapreasonRepository = scrapreasonRepository;
		this.productRepository = productRepository;
		this.productmodelRepository = productmodelRepository;
		this.unitmeasureRepository = unitmeasureRepository;
		this.productcategoryRepository = productcategoryRepository;
		this.workorderService = workorderService;
		this.productsubcategoryRepository = productsubcategoryRepository;
		
	}

	@BeforeEach
	void setUp() throws ParseException {
		wo = new Workorder();
		sr = new Scrapreason();
		p = new Product();
		um1 = new Unitmeasure();
		um2 = new Unitmeasure();
		pc = new Productcategory();
		psc = new Productsubcategory();
		pm = new Productmodel();
		
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
	
	@BeforeEach
	void setUp1() {
		
		pc = new Productcategory();
		pc.setName("Hogar");
		pc.setRowguid(14);
		pc.setModifieddate(modifieddate);
		pc.setProductsubcategories(null);
		productcategoryRepository.save(pc);
		
		psc = new Productsubcategory();
		psc.setModifieddate(modifieddate);
		psc.setName("Aluminio");
		
		List<Product> products = new ArrayList<Product>();
		products.add(p);
		psc.setProducts(products);
		psc.setRowguid(666);
		psc.setProductcategory(pc);
		productsubcategoryRepository.save(psc);
		
		pm = new Productmodel();
		pm.setCatalogdescription("Productos nuevos");
		pm.setInstructions("Usar con cuidado");
		pm.setModifieddate(modifieddate);
		pm.setName("Todo terreno");
		pm.setRowguid(69);
		productmodelRepository.save(pm);
		
		um1 = new Unitmeasure();
		//um1.setUnitmeasurecode("cm");
		um1.setModifieddate(modifieddate);
		um1.setName("centimetros");
		unitmeasureRepository.save(um1);
		
		um2 = new Unitmeasure();
		//um2.setUnitmeasurecode("in");
		um2.setModifieddate(modifieddate);
		um2.setName("Pulgadas");
		unitmeasureRepository.save(um2);
		
		p = new Product();
		p.setModifieddate(modifieddate);
		p.setSellstartdate(startdate);
		p.setSellenddate(enddate);
		p.setDiscontinueddate(duedate);
		p.setProductnumber("Cantidad");
		p.setWeight(new BigDecimal(20));
		p.setSize("12");
		p.setColor("Verde");
		p.setDaystomanufacture(8);
		p.setName("Makinon");
		p.setProductsubcategory(psc);
		p.setProductmodel(pm);
		p.setUnitmeasure1(um1);
		p.setUnitmeasure2(um2);
		p.setProductid(1);
		p.setClass_("Producto");
		productRepository.save(p);
		
		sr = new Scrapreason();
		sr.setModifieddate(modifieddate);
		sr.setName("My name is Lala");
		scrapreasonRepository.save(sr);
	}
	
	@Nested
	@DisplayName("Save method test")
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
			wo.setProduct(p);
			wo.setScrapreason(sr);
			
			workorder = workorderService.save(wo);
			
			Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
			assertNotNull(found);
			assertEquals(workorder.getWorkorderid(), found.getWorkorderid());
			Assertions.assertThat(found).isInstanceOfAny(Productsubcategory.class);
			Assertions.assertThat(found.getWorkorderid()).isGreaterThan(0);
			Assertions.assertThat(found).hasNoNullFieldsOrProperties();
			assertEquals(1, found.getWorkorderid());
		}
		
		@Test
		@Order(2)
		void saveNegativeOrderQty(){
			wo.setDuedate(duedate);
			wo.setEnddate(enddate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(-3);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			wo.setProduct(p);
			wo.setScrapreason(sr);
			
			try {
				workorder = workorderService.save(wo);
				
				Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
			
		}
		
		@Test
		@Order(3)
		void saveNegativeScrappedQty() {
			wo.setDuedate(duedate);
			wo.setEnddate(enddate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(3);
			wo.setScrappedqty(-8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			wo.setProduct(p);
			wo.setScrapreason(sr);
			
			try {
				workorder = workorderService.save(wo);
				
				Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}
		
		@Test
		@Order(4)
		void saveStartdateGreaterThanEnddate() {
			wo.setDuedate(duedate);
			wo.setEnddate(startdate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(enddate);
			wo.setOrderqty(3);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			wo.setProduct(p);
			wo.setScrapreason(sr);
			
			try {
				workorder = workorderService.save(wo);
				
				Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}
		
		@Test
		@Order(5)
		void saveNullProductid() {
			wo.setDuedate(duedate);
			wo.setEnddate(enddate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(3);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			wo.setProduct(null);
			wo.setScrapreason(sr);
			
			try {
				workorder = workorderService.save(wo);
				
				Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}
		
		@Test
		@Order(6)
		void saveNullScrapreasonid() {
			wo.setDuedate(duedate);
			wo.setEnddate(enddate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(3);
			wo.setScrappedqty(-8);
			wo.setWorkorderroutings(null);
			wo.setWorkorderid(123);
			wo.setProduct(p);
			wo.setScrapreason(sr);
			
			try {
				workorder = workorderService.save(wo);
				
				Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
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
		@BeforeEach
		void setUpUpdate() {
			wo.setDuedate(duedate);
			wo.setEnddate(enddate);
			wo.setModifieddate(modifieddate);
			wo.setStartdate(startdate);
			wo.setOrderqty(3);
			wo.setScrappedqty(8);
			wo.setWorkorderroutings(null);
			wo.setProduct(p);
			wo.setScrapreason(sr);
			
			workorder = workorderService.save(wo);
		}
		
		@Test
		@Order(8)
		void updateCorrectly() {
			
			Workorder wo1 = new Workorder();
			
			wo1.setDuedate(duedate);
			wo1.setEnddate(enddate);
			wo1.setModifieddate(modifieddate);
			wo1.setStartdate(startdate);
			wo1.setOrderqty(5);
			wo1.setScrappedqty(10);
			wo1.setWorkorderroutings(null);
			wo1.setWorkorderid(1);
			wo.setProduct(p);
			wo.setScrapreason(sr);
			
			workorder = workorderService.edit(wo);
			
			Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
			assertNotNull(found);
			assertEquals(workorder.getWorkorderid(), found.getWorkorderid());
			Assertions.assertThat(found).isInstanceOfAny(Productsubcategory.class);
			Assertions.assertThat(found.getWorkorderid()).isGreaterThan(0);
			Assertions.assertThat(found).hasNoNullFieldsOrProperties();
			assertEquals(1, found.getWorkorderid());
		}
		
		@Test
		@Order(9)
		void updateNegativeOrderQty(){
			Workorder wo1 = new Workorder();
			
			wo1.setDuedate(duedate);
			wo1.setEnddate(enddate);
			wo1.setModifieddate(modifieddate);
			wo1.setStartdate(startdate);
			wo1.setOrderqty(-5);
			wo1.setScrappedqty(10);
			wo1.setWorkorderroutings(null);
			wo1.setWorkorderid(1);
			wo.setProduct(p);
			wo.setScrapreason(sr);
			
			try {
				workorder = workorderService.edit(wo);
				
				Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
			
		}
		
		@Test
		@Order(10)
		void updateNegativeScrappedQty() {
			Workorder wo1 = new Workorder();
			
			wo1.setDuedate(duedate);
			wo1.setEnddate(enddate);
			wo1.setModifieddate(modifieddate);
			wo1.setStartdate(startdate);
			wo1.setOrderqty(5);
			wo1.setScrappedqty(-10);
			wo1.setWorkorderroutings(null);
			wo1.setWorkorderid(1);
			wo.setProduct(p);
			wo.setScrapreason(sr);
			
			try {
				workorder = workorderService.edit(wo);
				
				Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
			
		}
		
		@Test
		@Order(11)
		void updateStartdateGreaterThanEnddate() {
			Workorder wo1 = new Workorder();
			
			wo1.setDuedate(duedate);
			wo1.setEnddate(startdate);
			wo1.setModifieddate(modifieddate);
			wo1.setStartdate(enddate);
			wo1.setOrderqty(5);
			wo1.setScrappedqty(10);
			wo1.setWorkorderroutings(null);
			wo1.setWorkorderid(1);
			wo.setProduct(p);
			wo.setScrapreason(sr);
			
			try {
				workorder = workorderService.edit(wo);
				
				Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
			
		}
		
		@Test
		@Order(12)
		void updateNullScrapreasonid() {
			Workorder wo1 = new Workorder();
			
			wo1.setDuedate(duedate);
			wo1.setEnddate(enddate);
			wo1.setModifieddate(modifieddate);
			wo1.setStartdate(startdate);
			wo1.setOrderqty(5);
			wo1.setScrappedqty(10);
			wo1.setWorkorderroutings(null);
			wo1.setWorkorderid(1);
			wo.setProduct(p);
			wo.setScrapreason(null);
			
			try {
				workorder = workorderService.edit(wo);
				
				Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
			
		}
		
		@Test
		@Order(13)
		void updateNullProductid() {
			Workorder wo1 = new Workorder();
			
			wo1.setDuedate(duedate);
			wo1.setEnddate(enddate);
			wo1.setModifieddate(modifieddate);
			wo1.setStartdate(startdate);
			wo1.setOrderqty(5);
			wo1.setScrappedqty(10);
			wo1.setWorkorderroutings(null);
			wo1.setWorkorderid(1);
			wo.setProduct(null);
			wo.setScrapreason(sr);
			
			try {
				workorder = workorderService.edit(wo);
				
				Workorder found = workorderRepository.findById(workorder.getWorkorderid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
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
	void tearDown()  {
		//System.gc();
	}
	
	@AfterAll
	static void tearDownAfterClass() {
		System.out.println("... TEST FINISHED ...");
	}

}
