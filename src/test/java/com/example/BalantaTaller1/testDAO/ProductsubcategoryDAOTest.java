package com.example.BalantaTaller1.testDAO;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

import com.example.BalantaTaller1.dao.ProductDAOImpl;
import com.example.BalantaTaller1.dao.ProductcategoryDAOImpl;
import com.example.BalantaTaller1.dao.ProductmodelDAOImpl;
import com.example.BalantaTaller1.dao.ProductsubcategoryDAOImpl;
import com.example.BalantaTaller1.dao.UnitmeasureDAOImpl;
import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Unitmeasure;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = BalantaTaller1Application.class)
class ProductsubcategoryDAOTest {
	
	
	private ProductcategoryDAOImpl productcategoryDAO;
	private ProductsubcategoryDAOImpl productsubcategoryDAO;
	private ProductDAOImpl productDAO;
	private ProductmodelDAOImpl productmodelDAO;
	private UnitmeasureDAOImpl unitmeasureDAO;
	
	private Productcategory productcategory;
	private Productsubcategory productsubcategory;
	
	@Autowired
	public ProductsubcategoryDAOTest(ProductcategoryDAOImpl productcategoryDAO, ProductsubcategoryDAOImpl productsubcategoryDAO,
			ProductDAOImpl productDAO, ProductmodelDAOImpl productmodelDAO, UnitmeasureDAOImpl unitmeasureDAO) {
		this.productcategoryDAO = productcategoryDAO;
		this.productsubcategoryDAO = productsubcategoryDAO;
		this.productDAO = productDAO;
		this.unitmeasureDAO = unitmeasureDAO;
		this.productmodelDAO = productmodelDAO;
	}


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("... TEST STARTED ...");
	}

	@BeforeEach
	void setUp() throws Exception {
		productcategory = new Productcategory();
		productcategory.setName("Categoria");
		LocalDate modifieddate = LocalDate.parse("2020-01-08");
		productcategory.setModifieddate(modifieddate);
		productcategory.setRowguid(666);
		productcategoryDAO.save(productcategory);
		
		productsubcategory = new Productsubcategory();
		productsubcategory.setName("Subcategoria");
		LocalDate modifieddate1 = LocalDate.parse("2020-01-11");
		productsubcategory.setModifieddate(modifieddate1);
		productsubcategory.setRowguid(4466);
		productsubcategory.setProductcategory(productcategory);
		
	}
	
	@Nested
	class DAOTest{
		
		@Test
		@Order(1)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void save() {
			assertNotNull(productsubcategoryDAO);
			productsubcategoryDAO.save(productsubcategory);
			assertTrue(productsubcategoryDAO.findById(productsubcategory.getProductsubcategoryid()).equals(productsubcategory));
		}
		
		@Test
		@Order(2)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void update() {
			assertNotNull(productsubcategoryDAO);
			productsubcategoryDAO.save(productsubcategory);
			
			productsubcategory.setName("Subcategoria Nueva :v");
			LocalDate modifieddate = LocalDate.parse("2022-01-08");
			productsubcategory.setModifieddate(modifieddate);
			productsubcategory.setRowguid(7);
			
			Productsubcategory changed = productsubcategoryDAO.findById(productsubcategory.getProductsubcategoryid());
			
			assertAll(                
					() -> assertEquals("Subcategoria Nueva :v", changed.getName()),
					() -> assertEquals(modifieddate, changed.getModifieddate()),                 
					() -> assertEquals(7, changed.getRowguid())
					);
		}
		
		@Test
		@Order(3)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findAll() {
			assertNotNull(productsubcategoryDAO);
			productsubcategoryDAO.save(productsubcategory);
			
			assertEquals(productsubcategoryDAO.findAll().size(), 1);
		}
		
		@Test
		@Order(4)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findByProductcategoryId() {
			assertNotNull(productsubcategoryDAO);
			productsubcategoryDAO.save(productsubcategory);
			
			Productsubcategory productsubcategory1 = new Productsubcategory();
			productsubcategory1.setName("Moscow Mule");
			LocalDate modifieddate1 = LocalDate.parse("2020-01-14");
			productsubcategory1.setModifieddate(modifieddate1);
			productsubcategory1.setRowguid(46);
			productsubcategory1.setProductcategory(productcategory);
			productsubcategoryDAO.save(productsubcategory1);
			
			Productcategory productcategory1 = new Productcategory();
			productcategory1.setName("Titi Me Preguntó");
			productcategory1.setRowguid(451);
			LocalDate modifieddate2 = LocalDate.parse("2020-01-07");
			productcategory1.setModifieddate(modifieddate2);
			productcategoryDAO.save(productcategory1);
			
			Productsubcategory productsubcategory2 = new Productsubcategory();
			productsubcategory2.setName("Me Porto Bonito");
			LocalDate modifieddate3 = LocalDate.parse("2020-01-08");
			productsubcategory2.setModifieddate(modifieddate3);
			productsubcategory2.setRowguid(46);
			productsubcategory2.setProductcategory(productcategory1);
			productsubcategoryDAO.save(productsubcategory2);
			
			List<Productsubcategory> listPsc = productsubcategoryDAO.findByProductcategoryId(productcategory.getProductcategoryid());
			
			assertEquals(2, listPsc.size());
			
		}
		
		@Test
		@Order(5)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findByProductcategoryName() {
			assertNotNull(productsubcategoryDAO);
			productsubcategoryDAO.save(productsubcategory);
			
			Productsubcategory productsubcategory1 = new Productsubcategory();
			productsubcategory1.setName("Moscow Mule");
			LocalDate modifieddate1 = LocalDate.parse("2020-01-14");
			productsubcategory1.setModifieddate(modifieddate1);
			productsubcategory1.setRowguid(46);
			productsubcategory1.setProductcategory(productcategory);
			productsubcategoryDAO.save(productsubcategory1);
			
			Productcategory productcategory1 = new Productcategory();
			productcategory1.setName("Titi Me Preguntó");
			productcategory1.setRowguid(451);
			LocalDate modifieddate2 = LocalDate.parse("2020-01-07");
			productcategory1.setModifieddate(modifieddate2);
			productcategoryDAO.save(productcategory1);
			
			Productsubcategory productsubcategory2 = new Productsubcategory();
			productsubcategory2.setName("Me Porto Bonito");
			LocalDate modifieddate3 = LocalDate.parse("2020-01-08");
			productsubcategory2.setModifieddate(modifieddate3);
			productsubcategory2.setRowguid(46);
			productsubcategory2.setProductcategory(productcategory1);
			productsubcategoryDAO.save(productsubcategory2);
			
			List<Productsubcategory> listPsc = productsubcategoryDAO.findByProductcategoryName(productcategory1.getName());
			
			assertEquals(1, listPsc.size());
			
		}
		
		@Test
		@Order(6)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findByProductsubcategoryBetweenDatesOrderedByProductName() {
			Productmodel productmodel = new Productmodel();
			productmodel.setName("Nuevecito");
			productmodelDAO.save(productmodel);

			Unitmeasure unitmeasure1 = new Unitmeasure();
			unitmeasure1.setName("Centimetros");
			unitmeasureDAO.save(unitmeasure1);

			Unitmeasure unitmeasure2 = new Unitmeasure();
			unitmeasure2.setName("Libras");
			unitmeasureDAO.save(unitmeasure2);

			Product product = new Product();
			product.setName("Productazo");
			product.setDaystomanufacture(9);
			product.setSize("40");
			product.setUnitmeasure1(unitmeasure1);
			product.setWeight(new BigDecimal(2));
			product.setUnitmeasure2(unitmeasure2);
			LocalDate sellstartdate = LocalDate.parse("2020-02-01");
			product.setSellstartdate(sellstartdate);
			LocalDate sellenddate = LocalDate.parse("2022-08-25");
			product.setSellenddate(sellenddate);
			product.setProductmodel(productmodel);
			product.setProductsubcategory(productsubcategory);
			productDAO.save(product);
			
			LocalDate date1 = LocalDate.parse("2020-01-01");
			LocalDate date2 = LocalDate.parse("2020-02-03");
			
			List<Object[]> listP = productsubcategoryDAO.findByProductsubcategoryBetweenDatesOrderedByProductName(date1, date2, 
					productcategory);
			
			 assertEquals(1, listP.size());
		}
		
	}
	
	@AfterEach
	void tearDown() throws Exception {
		productcategoryDAO = null;
		productcategory = null;
		productsubcategoryDAO = null;
		productsubcategory = null;
		System.gc();
	}

	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("... TEST FINISHED ...");
	}

}
