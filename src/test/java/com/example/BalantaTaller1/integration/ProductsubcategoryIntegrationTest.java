package com.example.BalantaTaller1.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

//import java.sql.Timestamp;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.BalantaTaller1.dao.ProductsubcategoryDAOImpl;
import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
//import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
//import com.example.BalantaTaller1.repository.prod.ProductsubcategoryRepository;
//import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImplDAO;
//import com.example.BalantaTaller1.service.prod.ProductsubcategoryServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductsubcategoryServiceImplDAO;

@ContextConfiguration(classes = BalantaTaller1Application.class)
//@SpringBootTest
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductsubcategoryIntegrationTest {

	private Productsubcategory psc;
	private Productsubcategory productsubcategory;

	private Productcategory pc;

	private LocalDate time;

	private ProductsubcategoryDAOImpl productsubcategoryDAO;

	//private ProductcategoryRepository productcategoryRepository;

	private ProductsubcategoryServiceImplDAO productsubcategoryService;
	private ProductcategoryServiceImplDAO productcategoryService;

	// private TestEntityManager testEntityManager;

	@Autowired
	public ProductsubcategoryIntegrationTest(ProductsubcategoryDAOImpl productsubcategoryDAO,
			//ProductcategoryRepository productcategoryRepository,
			ProductsubcategoryServiceImplDAO productsubcategoryService, ProductcategoryServiceImplDAO productcategoryService) {
		this.productsubcategoryDAO = productsubcategoryDAO;
		this.productsubcategoryService = productsubcategoryService;
		this.productcategoryService = productcategoryService;
	}

	@BeforeAll
	static void start() {
		System.out.println("... TEST STARTED ...");
	}

	@BeforeEach
	void setUp() throws ParseException {
		psc = new Productsubcategory();

		pc = new Productcategory();

		time = LocalDate.parse("2022-03-27");

		pc.setName("Hogar");
		pc.setRowguid(14);
		pc.setModifieddate(time);

		pc.setProductsubcategories(null);
		
		productcategoryService.save(pc);

		// testEntityManager.persist(pc); productcategoryRepository.save(pc);
	}

	@Nested

	@DisplayName("Save method test")
	class ProductsubcategorySave {

		@Test

		@Order(1)
		void saveCorrectly() {
			psc.setModifieddate(time);
			psc.setName("Aluminio");
			psc.setProducts(null);
			psc.setRowguid(666);
			psc.setProductcategory(pc);

			productsubcategoryService.save(psc);

			Productsubcategory found = productsubcategoryDAO.findById(1);
			assertNotNull(found);
			Assertions.assertThat(found).isInstanceOfAny(Productsubcategory.class);
			Assertions.assertThat(found.getProductsubcategoryid()).isGreaterThan(0);
			assertEquals(found.getName(), "Aluminio");
			assertEquals(1, found.getProductsubcategoryid());
		}

		@Test

		@Order(2)
		void saveNameLesserThanFiveChar() {
			psc.setModifieddate(time);
			psc.setName("Al");
			psc.setProducts(null);
			psc.setProductsubcategoryid(985);
			psc.setRowguid(666);
			psc.setProductcategory(pc);

			try {
				productsubcategory = productsubcategoryService.save(psc);

				Productsubcategory found = productsubcategoryDAO
						.findById(productsubcategory.getProductsubcategoryid());
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
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
			psc.setProductcategory(null);

			try {
				productsubcategory = productsubcategoryService.save(psc);
				Productsubcategory found = productsubcategoryDAO
						.findById(productsubcategory.getProductsubcategoryid());
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
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
	@DisplayName("Edit method test")
	class ProductsubcategoryUpdate {

		@BeforeEach
		void setUpUpdate() {
			psc.setModifieddate(time);
			psc.setName("Aluminio");
			psc.setProducts(null);
			psc.setRowguid(666);
			psc.setProductcategory(pc);

			productsubcategoryService.save(psc);
		}

		@Test

		@Order(5)
		void updateCorrectly() {

			Productsubcategory psc1 = new Productsubcategory();

			psc1.setProductsubcategoryid(1);
			psc1.setModifieddate(time);
			psc1.setName("Estano");
			psc1.setProducts(null);
			psc1.setRowguid(666);
			psc1.setProductcategory(pc);

			productsubcategoryService.edit(psc1);
			Productsubcategory found = productsubcategoryDAO
					.findById(1);
			assertNotNull(found);
			Assertions.assertThat(found).isInstanceOfAny(Productsubcategory.class);
			Assertions.assertThat(found.getProductsubcategoryid()).isGreaterThan(0);
			assertEquals("Estano", found.getName());
		}

		@Test

		@Order(6)
		void updateNameLesserThanFiveChar() {

			Productsubcategory psc1 = new Productsubcategory();

			psc1.setProductsubcategoryid(1);
			psc1.setModifieddate(time);
			psc1.setName("Sn");
			psc1.setProducts(null);
			psc1.setRowguid(666);
			psc1.setProductcategory(pc);

			try {
				productsubcategory = productsubcategoryService.edit(psc1);
				Productsubcategory found = productsubcategoryDAO.findById(productsubcategory.getProductsubcategoryid());
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}

		}

		@Test

		@Order(7)
		void updateNullProductcategoryid() {

			Productsubcategory psc1 = new Productsubcategory();

			psc1.setProductsubcategoryid(null);
			psc1.setModifieddate(time);
			psc1.setName("Estano");
			psc1.setProducts(null);
			psc1.setRowguid(666);
			psc1.setProductcategory(pc);

			try {
				productsubcategory = productsubcategoryService.edit(psc1);
				Productsubcategory found = productsubcategoryDAO.findById(productsubcategory.getProductsubcategoryid());
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}

		}

		@Test

		@Order(8)
		public void updateNonExistingId() {
			Productsubcategory psc1 = new Productsubcategory();

			psc1.setProductsubcategoryid(44);
			psc1.setModifieddate(time);
			psc1.setName("Estano");
			psc1.setProducts(null);
			psc1.setRowguid(666);
			psc1.setProductcategory(pc);
			
			assertThrows(NullPointerException.class, () -> {
				productsubcategory = productsubcategoryService.edit(psc1);
				productsubcategoryDAO.findById(productsubcategory.getProductsubcategoryid());
			});
		}

		@Test

		@Order(9)
		public void updateWithNullProductcategory() {
			Productsubcategory psc1 = new Productsubcategory();

			psc1.setProductsubcategoryid(44);
			psc1.setModifieddate(time);
			psc1.setName("Estano");
			psc1.setProducts(null);
			psc1.setRowguid(666);
			psc1.setProductcategory(null);
		
			assertThrows(NullPointerException.class, () -> {
				productsubcategory = productsubcategoryService.edit(psc1);
				productsubcategoryDAO.findById(productsubcategory.getProductsubcategoryid());
			});
		}

		@Test
		@Order(10)
		public void udateNull() {
			assertThrows(NullPointerException.class, () -> {
				productsubcategoryService.edit(null);
			});
		}
	}

	@AfterAll
	static void end() {
		System.out.println("... TEST FINISHED ...");
	}

	@AfterEach
	void tearDown() {
		pc = null;
		//productcategoryRepository = null;
		psc = null;
		productsubcategoryDAO = null;
		productsubcategory = null;
		System.gc();
	}

}
