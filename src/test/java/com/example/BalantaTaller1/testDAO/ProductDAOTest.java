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
//import com.example.BalantaTaller1.dao.ScrapreasonDAO;
import com.example.BalantaTaller1.dao.ScrapreasonDAOImpl;
import com.example.BalantaTaller1.dao.UnitmeasureDAOImpl;
import com.example.BalantaTaller1.dao.WorkorderDAOImpl;
import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Scrapreason;
import com.example.BalantaTaller1.model.prod.Unitmeasure;
import com.example.BalantaTaller1.model.prod.Workorder;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = BalantaTaller1Application.class)
class ProductDAOTest {

	private ProductcategoryDAOImpl productcategoryDAO;
	private ProductsubcategoryDAOImpl productsubcategoryDAO;
	private ProductDAOImpl productDAO;
	private ProductmodelDAOImpl productmodelDAO;
	private UnitmeasureDAOImpl unitmeasureDAO;
	private ScrapreasonDAOImpl scrapreasonDAO;
	private WorkorderDAOImpl workorderDAO;

	private Productcategory productcategory;
	private Productsubcategory productsubcategory;
	private Product product;
	private Productmodel productmodel;
	private Unitmeasure unitmeasure1;
	private Unitmeasure unitmeasure2;

	@Autowired
	public ProductDAOTest(ProductcategoryDAOImpl productcategoryDAO, ProductsubcategoryDAOImpl productsubcategoryDAO,
			ProductDAOImpl productDAO, ProductmodelDAOImpl productmodelDAO, UnitmeasureDAOImpl unitmeasureDAO,
			ScrapreasonDAOImpl scrapreasonDAO, WorkorderDAOImpl workorderDAO) {
		this.productcategoryDAO = productcategoryDAO;
		this.productsubcategoryDAO = productsubcategoryDAO;
		this.productDAO = productDAO;
		this.productmodelDAO = productmodelDAO;
		this.unitmeasureDAO = unitmeasureDAO;
		this.scrapreasonDAO = scrapreasonDAO;
		this.workorderDAO = workorderDAO;
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
		productsubcategoryDAO.save(productsubcategory);

		productmodel = new Productmodel();
		productmodel.setName("Nuevecito");
		productmodelDAO.save(productmodel);

		unitmeasure1 = new Unitmeasure();
		unitmeasure1.setName("Centimetros");
		unitmeasureDAO.save(unitmeasure1);

		unitmeasure2 = new Unitmeasure();
		unitmeasure2.setName("Libras");
		unitmeasureDAO.save(unitmeasure2);

		product = new Product();
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

	}

	@Nested
	class DAOTest {
		@Test
		@Order(1)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void save() {
			assertNotNull(productDAO);
			productDAO.save(product);
			assertTrue(productDAO.findById(product.getProductid()).equals(product));
		}

		@Test
		@Order(2)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void update() {
			assertNotNull(productDAO);
			productDAO.save(product);

			product.setName("Productazo Nuevo :v");
			LocalDate sellstartdate = LocalDate.parse("2022-02-02");
			product.setSellstartdate(sellstartdate);
			product.setDaystomanufacture(10);

			Product changed = productDAO.findById(product.getProductid());

			assertAll(() -> assertEquals("Productazo Nuevo :v", changed.getName()),
					() -> assertEquals(sellstartdate, changed.getSellstartdate()),
					() -> assertEquals(10, changed.getDaystomanufacture()));
		}

		@Test
		@Order(3)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findAll() {
			assertNotNull(productDAO);
			productDAO.save(product);

			assertEquals(productDAO.findAll().size(), 2);
		}

		@Test
		@Order(4)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findByProductsubcategoryId() {
			assertNotNull(productDAO);
			productDAO.save(product);

			Product product1 = new Product();
			product1.setName("ULTRA SOLO");
			product1.setDaystomanufacture(9);
			product1.setSize("2");
			product1.setUnitmeasure1(unitmeasure1);
			product1.setWeight(new BigDecimal(2));
			product1.setUnitmeasure2(unitmeasure2);
			LocalDate sellstartdate = LocalDate.parse("2021-02-01");
			product1.setSellstartdate(sellstartdate);
			LocalDate sellenddate = LocalDate.parse("2022-06-25");
			product1.setSellenddate(sellenddate);
			product1.setProductmodel(productmodel);
			product1.setProductsubcategory(productsubcategory);
			productDAO.save(product1);

			Productsubcategory productsubcategory1 = new Productsubcategory();
			productsubcategory1.setName("Moscow Mule");
			LocalDate modifieddate1 = LocalDate.parse("2020-01-14");
			productsubcategory1.setModifieddate(modifieddate1);
			productsubcategory1.setRowguid(46);
			productsubcategory1.setProductcategory(productcategory);
			productsubcategoryDAO.save(productsubcategory1);

			Product product2 = new Product();
			product2.setName("A$AP");
			product2.setDaystomanufacture(5);
			product2.setSize("4");
			product2.setUnitmeasure1(unitmeasure1);
			product2.setWeight(new BigDecimal(4));
			product2.setUnitmeasure2(unitmeasure2);
			LocalDate sellstartdate1 = LocalDate.parse("2021-02-01");
			product2.setSellstartdate(sellstartdate1);
			LocalDate sellenddate1 = LocalDate.parse("2022-06-25");
			product2.setSellenddate(sellenddate1);
			product2.setProductmodel(productmodel);
			product2.setProductsubcategory(productsubcategory1);
			productDAO.save(product2);

			List<Product> listP = productDAO
					.findByProductsubcategoryId(productsubcategory.getProductsubcategoryid());

			assertEquals(2, listP.size());
		}
		
		@Test
		@Order(5)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findByProductmodel() {
			assertNotNull(productDAO);
			productDAO.save(product);

			Product product1 = new Product();
			product1.setName("ULTRA SOLO");
			product1.setDaystomanufacture(9);
			product1.setSize("2");
			product1.setUnitmeasure1(unitmeasure1);
			product1.setWeight(new BigDecimal(2));
			product1.setUnitmeasure2(unitmeasure2);
			LocalDate sellstartdate = LocalDate.parse("2021-02-01");
			product1.setSellstartdate(sellstartdate);
			LocalDate sellenddate = LocalDate.parse("2022-06-25");
			product1.setSellenddate(sellenddate);
			product1.setProductmodel(productmodel);
			product1.setProductsubcategory(productsubcategory);
			productDAO.save(product1);
			
			Productmodel productmodel1 = new Productmodel();
			productmodel1.setName("Viejito");
			productmodelDAO.save(productmodel1);
			
			Product product2 = new Product();
			product2.setName("A$AP");
			product2.setDaystomanufacture(2);
			product2.setSize("4");
			product2.setUnitmeasure1(unitmeasure1);
			product2.setWeight(new BigDecimal(2));
			product2.setUnitmeasure2(unitmeasure2);
			LocalDate sellstartdate1 = LocalDate.parse("2022-02-01");
			product2.setSellstartdate(sellstartdate1);
			LocalDate sellenddate1 = LocalDate.parse("2022-06-25");
			product2.setSellenddate(sellenddate1);
			product2.setProductmodel(productmodel1);
			product2.setProductsubcategory(productsubcategory);
			productDAO.save(product2);
			
			List<Product> listP = productDAO
					.findByProductmodel(productmodel.getProductmodelid());

			assertEquals(2, listP.size());

		}
		
		@Test
		@Order(6)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findByUnitmeasureSizeCode() {
			assertNotNull(productDAO);
			productDAO.save(product);

			Product product1 = new Product();
			product1.setName("ULTRA SOLO");
			product1.setDaystomanufacture(9);
			product1.setSize("2");
			product1.setUnitmeasure1(unitmeasure1);
			product1.setWeight(new BigDecimal(2));
			product1.setUnitmeasure2(unitmeasure2);
			LocalDate sellstartdate = LocalDate.parse("2021-02-01");
			product1.setSellstartdate(sellstartdate);
			LocalDate sellenddate = LocalDate.parse("2022-06-25");
			product1.setSellenddate(sellenddate);
			product1.setProductmodel(productmodel);
			product1.setProductsubcategory(productsubcategory);
			productDAO.save(product1);
			
			Unitmeasure um1 = new Unitmeasure();
			um1.setName("Metros");
			unitmeasureDAO.save(um1);
			
			Product product2 = new Product();
			product2.setName("A$AP");
			product2.setDaystomanufacture(2);
			product2.setSize("1");
			product2.setUnitmeasure1(um1);
			product2.setWeight(new BigDecimal(40));
			product2.setUnitmeasure2(unitmeasure2);
			LocalDate sellstartdate1 = LocalDate.parse("2022-02-01");
			product2.setSellstartdate(sellstartdate1);
			LocalDate sellenddate1 = LocalDate.parse("2022-06-25");
			product2.setSellenddate(sellenddate1);
			product2.setProductmodel(productmodel);
			product2.setProductsubcategory(productsubcategory);
			productDAO.save(product2);
			
			List<Product> listP = productDAO
					.findByUnitmeasureSizeCode(unitmeasure1.getUnitmeasurecode());

			assertEquals(2, listP.size());

		}
		
		@Test
		@Order(7)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		void findByAtLeastTwoWorkorders() {
			assertNotNull(productDAO);
			productDAO.save(product);
			
			Scrapreason scrapreason = new Scrapreason();
			scrapreason.setName("Esta malo");
			scrapreasonDAO.save(scrapreason);
			
			Workorder workorder = new Workorder();
			workorder.setOrderqty(5);
			LocalDate startdate = LocalDate.parse("2022-02-01");
			workorder.setStartdate(startdate);
			LocalDate enddate = LocalDate.parse("2022-08-25");
			workorder.setEnddate(enddate);
			workorder.setScrappedqty(0);
			workorder.setProduct(product);
			workorder.setScrapreason(scrapreason);
			workorderDAO.save(workorder);
			
			Workorder workorder1 = new Workorder();
			workorder1.setOrderqty(5);
			LocalDate startdate1 = LocalDate.parse("2022-05-17");
			workorder1.setStartdate(startdate1);
			LocalDate enddate1 = LocalDate.parse("2023-03-07");
			workorder1.setEnddate(enddate1);
			workorder1.setScrappedqty(0);
			workorder1.setProduct(product);
			workorder1.setScrapreason(scrapreason);
			workorderDAO.save(workorder1);
			
			Product product1 = new Product();
			product1.setName("ULTRA SOLO");
			product1.setDaystomanufacture(9);
			product1.setSize("2");
			product1.setUnitmeasure1(unitmeasure1);
			product1.setWeight(new BigDecimal(2));
			product1.setUnitmeasure2(unitmeasure2);
			LocalDate sellstartdate = LocalDate.parse("2021-02-01");
			product1.setSellstartdate(sellstartdate);
			LocalDate sellenddate = LocalDate.parse("2022-06-25");
			product1.setSellenddate(sellenddate);
			product1.setProductmodel(productmodel);
			product1.setProductsubcategory(productsubcategory);
			productDAO.save(product1);
			
			List<Product> listP = productDAO
					.findByAtLeastTwoWorkorders();
			
			assertEquals(1, listP.size());
			assertEquals(product.getName(), listP.get(0).getName());
			
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		productcategoryDAO = null;
		productcategory = null;
		productsubcategoryDAO = null;
		productsubcategory = null;
		productDAO = null;
		product = null;
		unitmeasure1 = null;
		unitmeasure2 = null;
		unitmeasureDAO = null;
		productmodel = null;
		productmodelDAO = null;
		System.gc();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("... TEST FINISHED ...");
	}

}
