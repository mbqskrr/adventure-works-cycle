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

import com.example.BalantaTaller1.dao.ProductDAOImpl;
import com.example.BalantaTaller1.dao.ProductcategoryDAOImpl;
import com.example.BalantaTaller1.dao.ProductmodelDAOImpl;
import com.example.BalantaTaller1.dao.ProductsubcategoryDAOImpl;
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
class WorkorderDAOTest {
	
	private ProductcategoryDAOImpl productcategoryDAO;
	private ProductsubcategoryDAOImpl productsubcategoryDAO;
	private ProductDAOImpl productDAO;
	private ProductmodelDAOImpl productmodelDAO;
	private UnitmeasureDAOImpl unitmeasureDAO;
	private WorkorderDAOImpl workorderDAO;
	private ScrapreasonDAOImpl scrapreasonDAO;

	private Productcategory productcategory;
	private Productsubcategory productsubcategory;
	private Product product;
	private Productmodel productmodel;
	private Unitmeasure unitmeasure1;
	private Unitmeasure unitmeasure2;
	private Workorder workorder;
	private Scrapreason scrapreason;
	
	@Autowired
	public WorkorderDAOTest(ProductcategoryDAOImpl productcategoryDAO, ProductsubcategoryDAOImpl productsubcategoryDAO,
			ProductDAOImpl productDAO, ProductmodelDAOImpl productmodelDAO, UnitmeasureDAOImpl unitmeasureDAO,
			WorkorderDAOImpl workorderDAO, ScrapreasonDAOImpl scrapreasonDAO) {
		this.productcategoryDAO = productcategoryDAO;
		this.productsubcategoryDAO = productsubcategoryDAO;
		this.productDAO = productDAO;
		this.productmodelDAO = productmodelDAO;
		this.unitmeasureDAO = unitmeasureDAO;
		this.workorderDAO = workorderDAO;
		this.scrapreasonDAO = scrapreasonDAO;
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
		productDAO.save(product);
		
		scrapreason = new Scrapreason();
		scrapreason.setName("None");
		scrapreasonDAO.save(scrapreason);
		
		workorder = new Workorder();
		workorder.setOrderqty(5);
		LocalDate startdate = LocalDate.parse("2022-02-01");
		workorder.setStartdate(startdate);
		LocalDate enddate = LocalDate.parse("2022-08-25");
		workorder.setEnddate(enddate);
		workorder.setScrappedqty(0);
		workorder.setProduct(product);
		workorder.setScrapreason(scrapreason);
	}
	
	@Nested
	class DAOTest{
		@Test
		@Order(1)
		void save() {
			assertNotNull(workorderDAO);
			workorderDAO.save(workorder);
			assertTrue(workorderDAO.findById(workorder.getWorkorderid()).equals(workorder));
		}
		
		@Test
		@Order(2)
		void update() {
			assertNotNull(workorderDAO);
			workorderDAO.save(workorder);
			
			LocalDate startdate = LocalDate.parse("2022-02-02");
			workorder.setStartdate(startdate);
			workorder.setOrderqty(8);
			workorderDAO.update(workorder);
			
			Workorder changed = workorderDAO.findById(workorder.getWorkorderid());
			
			assertAll(() -> assertEquals(startdate, changed.getStartdate()),
					() -> assertEquals(8, changed.getOrderqty()));
			
		}
		
		@Test
		@Order(3)
		void findAll() {
			assertNotNull(workorderDAO);
			workorderDAO.save(workorder);

			assertEquals(workorderDAO.findAll().size(), 1);
		}
		
		@Test
		@Order(4)
		void findByScrapreasonId() {
			assertNotNull(workorderDAO);
			workorderDAO.save(workorder);
			
			Workorder workorder1 = new Workorder();
			workorder1.setOrderqty(5);
			LocalDate startdate = LocalDate.parse("2022-05-17");
			workorder1.setStartdate(startdate);
			LocalDate enddate = LocalDate.parse("2023-03-07");
			workorder1.setEnddate(enddate);
			workorder1.setScrappedqty(0);
			workorder1.setProduct(product);
			workorder1.setScrapreason(scrapreason);
			workorderDAO.save(workorder1);
			
			Scrapreason scrapreason1 = new Scrapreason();
			scrapreason1.setName("Dañanado");
			scrapreasonDAO.save(scrapreason1);
			
			Workorder workorder2 = new Workorder();
			workorder2.setOrderqty(40);
			LocalDate startdate1 = LocalDate.parse("2022-05-17");
			workorder2.setStartdate(startdate1);
			LocalDate enddate1 = LocalDate.parse("2023-03-07");
			workorder2.setEnddate(enddate1);
			workorder2.setScrappedqty(20);
			workorder2.setProduct(product);
			workorder2.setScrapreason(scrapreason1);
			workorderDAO.save(workorder2);
			
			List<Workorder> listP = workorderDAO
					.findByScrapreasonId(scrapreason.getScrapreasonid());

			assertEquals(2, listP.size());
		}
		
		@Test
		@Order(5)
		void findByOrderqty() {
			assertNotNull(workorderDAO);
			workorderDAO.save(workorder);
			
			Workorder workorder1 = new Workorder();
			workorder1.setOrderqty(5);
			LocalDate startdate = LocalDate.parse("2022-05-17");
			workorder1.setStartdate(startdate);
			LocalDate enddate = LocalDate.parse("2023-03-07");
			workorder1.setEnddate(enddate);
			workorder1.setScrappedqty(0);
			workorder1.setProduct(product);
			workorder1.setScrapreason(scrapreason);
			workorderDAO.save(workorder1);
			
			Workorder workorder2 = new Workorder();
			workorder2.setOrderqty(40);
			LocalDate startdate1 = LocalDate.parse("2022-05-17");
			workorder2.setStartdate(startdate1);
			LocalDate enddate1 = LocalDate.parse("2023-03-07");
			workorder2.setEnddate(enddate1);
			workorder2.setScrappedqty(0);
			workorder2.setProduct(product);
			workorder2.setScrapreason(scrapreason);
			workorderDAO.save(workorder2);
			
			List<Workorder> listP = workorderDAO
					.findByOrderqty(5);

			assertEquals(2, listP.size());
			
			List<Workorder> listP1 = workorderDAO
					.findByOrderqty(40);

			assertEquals(1, listP1.size());
		}
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("... TEST FINISHED ...");
	}

}
