package com.example.BalantaTaller1.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
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
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Unitmeasure;
import com.example.BalantaTaller1.repository.prod.ProductRepository;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
import com.example.BalantaTaller1.repository.prod.ProductmodelRepository;
import com.example.BalantaTaller1.repository.prod.ProductsubcategoryRepository;
import com.example.BalantaTaller1.repository.prod.UnitmeasureRepository;
import com.example.BalantaTaller1.service.prod.ProductServiceImpl;

@ContextConfiguration(classes = BalantaTaller1Application.class)
@SpringBootTest
class ProductIntegrationTest {
	
	private Unitmeasure um1;
	private Unitmeasure um2;
	private Product p;
	private Product product;
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
	private Timestamp discontinueddate;
	
	private SimpleDateFormat df2; 
	private Date date2; 
	private long time3;
	private Timestamp sellstartdate;
	
	private SimpleDateFormat df3; 
	private Date date3; 
	private long time4;
	private Timestamp sellenddate;
	

	private UnitmeasureRepository unitmeasureRepository;
	private ProductRepository productRepository;
	private ProductmodelRepository productmodelRepository;
	private ProductsubcategoryRepository productsubcategoryRepository;
	private ProductcategoryRepository productcategoryRepository;

	private ProductServiceImpl productService;
	
	@Autowired
	public ProductIntegrationTest(UnitmeasureRepository unitmeasureRepository, ProductRepository productRepository,
			ProductmodelRepository productmodelRepository, ProductsubcategoryRepository productsubcategoryRepository,
			ProductServiceImpl productService, ProductcategoryRepository productcategoryRepository) {
		this.unitmeasureRepository = unitmeasureRepository;
		this.productRepository = productRepository;
		this.productmodelRepository = productmodelRepository;
		this.productsubcategoryRepository = productsubcategoryRepository;
		this.productService = productService;
		this.productcategoryRepository = productcategoryRepository;
	}

	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("... TEST STARTED ...");
	}
	
	@BeforeEach
	void setUp() throws ParseException {
		um1 = new Unitmeasure();
		um2 = new Unitmeasure();
		p = new Product();
		pm = new Productmodel();
		psc = new Productsubcategory();
		
		df = new SimpleDateFormat("dd-MM-yyyy");
		date  = df.parse("27-03-2022");
		time1 = date.getTime();
		modifieddate = new Timestamp(time1);
		
		df1 = new SimpleDateFormat("dd-MM-yyyy");
		date1  = df1.parse("06-08-2023");
		time2 = date1.getTime();
		discontinueddate = new Timestamp(time2);
		
		df2 = new SimpleDateFormat("dd-MM-yyyy");
		date2  = df2.parse("30-03-2022");
		time3 = date2.getTime();
		sellstartdate = new Timestamp(time3);
		
		df3 = new SimpleDateFormat("dd-MM-yyyy");
		date3  = df3.parse("13-04-2022");
		time4 = date3.getTime();
		sellenddate = new Timestamp(time4);
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
		psc.setProducts(null);
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
		
		/*
		 * um1 = new Unitmeasure(); um1.setUnitmeasurecode("cm");
		 * um1.setModifieddate(modifieddate); um1.setName("centimetros");
		 * unitmeasureRepository.save(um1);
		 * 
		 * um2 = new Unitmeasure(); um2.setUnitmeasurecode("in");
		 * um2.setModifieddate(modifieddate); um2.setName("Pulgadas");
		 * unitmeasureRepository.save(um2);
		 */
		
	}
	
	@Nested
	@DisplayName("Save methods")
	class ProductSave {
		
		@Test
		@Order(1)
		void saveCorrectly() {
			
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
			
			p.setModifieddate(modifieddate);
			p.setSellstartdate(sellstartdate);
			p.setSellenddate(sellenddate);
			p.setDiscontinueddate(discontinueddate);
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
			product = productService.save(p);
			
			Product found = productRepository.findById(product.getProductid()).get();
			assertNotNull(found);
			assertEquals(product.getProductid(), found.getProductid());
			Assertions.assertThat(found).isInstanceOfAny(Productsubcategory.class);
			Assertions.assertThat(found.getProductid()).isGreaterThan(0);
			Assertions.assertThat(found).hasNoNullFieldsOrProperties();
			assertEquals(found.getName(), "Makinon");
			assertEquals(1, found.getProductid());
			
		}
		
	}
	
	@Nested
	@DisplayName("Edit method test")
	class ProductUpdate {
		
		@BeforeEach
		void setUpUpdate() {
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
			
			p.setModifieddate(modifieddate);
			p.setSellstartdate(sellstartdate);
			p.setSellenddate(sellenddate);
			p.setDiscontinueddate(discontinueddate);
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
			
			product = productService.save(p);
			
		}
		
		@Test
		@Order(2)
		void updateCorrectly() {
			
			Product p1 = new Product();
			
			p1.setProductid(1);
			p1.setModifieddate(modifieddate);
			p1.setSellstartdate(sellstartdate);
			p1.setSellenddate(sellenddate);
			p1.setDiscontinueddate(discontinueddate);
			p1.setProductnumber("Cantidad");
			p1.setWeight(new BigDecimal(20));
			p1.setSize("120");
			p1.setBillofmaterials1(null);
			p1.setBillofmaterials2(null);
			p1.setColor("Azul");
			p1.setDaystomanufacture(8);
			p1.setName("Bichota");
			p1.setProductsubcategory(psc);
			p1.setProductmodel(pm);
			p1.setUnitmeasure1(um1);
			p1.setUnitmeasure2(um2);
			
			product = productService.edit(p1);
			
			Product found = productRepository.findById(product.getProductid()).get();
			assertNotNull(found);
			assertEquals(product.getProductid(), found.getProductid());
			Assertions.assertThat(found).isInstanceOfAny(Product.class);
			Assertions.assertThat(found.getProductid()).isGreaterThan(0);
			Assertions.assertThat(found).hasNoNullFieldsOrProperties();
			assertEquals("Bichota", found.getName());
		}
	}

	
	@AfterEach
	void tearDown(){
		System.gc();
	}

	@AfterAll
	static void tearDownAfterClass() {
		System.out.println("... TEST FINISHED ...");
	}


	

	

}
