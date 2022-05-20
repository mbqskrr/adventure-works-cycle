package com.example.BalantaTaller1.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
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

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Unitmeasure;
import com.example.BalantaTaller1.repository.prod.ProductRepository;
/*import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
import com.example.BalantaTaller1.repository.prod.ProductmodelRepository;
import com.example.BalantaTaller1.repository.prod.ProductsubcategoryRepository;
import com.example.BalantaTaller1.repository.prod.UnitmeasureRepository;*/
import com.example.BalantaTaller1.service.prod.ProductServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductsubcategoryServiceImpl;

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

	private LocalDate modifieddate;
	private LocalDate discontinueddate;
	private LocalDate sellstartdate;
	private LocalDate sellenddate;

	//private UnitmeasureRepository unitmeasureRepository;
	private ProductRepository productRepository;
	/*private ProductmodelRepository productmodelRepository;
	private ProductsubcategoryRepository productsubcategoryRepository;
	private ProductcategoryRepository productcategoryRepository;*/

	private ProductServiceImpl productService;
	private ProductcategoryServiceImpl productcategoryService;
	private ProductsubcategoryServiceImpl productsubcategoryService;

	@Autowired
	public ProductIntegrationTest(/*UnitmeasureRepository unitmeasureRepository, */ProductRepository productRepository,
			//ProductmodelRepository productmodelRepository, ProductsubcategoryRepository productsubcategoryRepository,
			ProductServiceImpl productService, //ProductcategoryRepository productcategoryRepository,
			ProductcategoryServiceImpl productcategoryService, ProductsubcategoryServiceImpl productsubcategoryService) {
		//this.unitmeasureRepository = unitmeasureRepository;
		this.productRepository = productRepository;
		/*this.productmodelRepository = productmodelRepository;
		this.productsubcategoryRepository = productsubcategoryRepository;*/
		this.productService = productService;
		//this.productcategoryRepository = productcategoryRepository;
		this.productcategoryService = productcategoryService;
		this.productsubcategoryService = productsubcategoryService;
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

		modifieddate = LocalDate.parse("2022-03-27");
		discontinueddate = LocalDate.parse("2023-08-06");
		sellstartdate = LocalDate.parse("2022-03-30");
		sellenddate = LocalDate.parse("2022-04-13");
	}

	@BeforeEach
	void setUp1() {

		pc = new Productcategory();
		pc.setName("Hogar");
		pc.setRowguid(14);
		pc.setModifieddate(modifieddate);
		pc.setProductsubcategories(null);
		productcategoryService.save(pc);

		psc = new Productsubcategory();
		psc.setModifieddate(modifieddate);
		psc.setName("Aluminio");
		psc.setProducts(null);
		psc.setRowguid(666);
		psc.setProductcategory(pc);
		productsubcategoryService.save(psc);

		pm = new Productmodel();
		pm.setCatalogdescription("Productos nuevos");
		pm.setInstructions("Usar con cuidado");
		pm.setModifieddate(modifieddate);
		pm.setName("Todo terreno");
		pm.setRowguid(69);
		productService.saveProductmodel(pm);

		um1 = new Unitmeasure(); // um1.setUnitmeasurecode("cm");
		um1.setModifieddate(modifieddate);
		um1.setName("centimetros");
		productService.saveUnitmeasure(um1);

		um2 = new Unitmeasure(); // um2.setUnitmeasurecode("in");
		um2.setModifieddate(modifieddate);
		um2.setName("Pulgadas");
		productService.saveUnitmeasure(um2);

	}

	@Nested
	@DisplayName("Save methods")
	class ProductSave {

		@Test

		@Order(1)
		void saveCorrectly() {

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

		@Test

		@Order(2)
		void saveNegativeWeight() {

			p.setModifieddate(modifieddate);
			p.setSellstartdate(sellstartdate);
			p.setSellenddate(sellenddate);
			p.setDiscontinueddate(discontinueddate);
			p.setProductnumber("Cantidad");
			p.setWeight(new BigDecimal(-20));
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

			try {
				product = productService.save(p);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(3)
		void saveNegativeSize() {
			p.setModifieddate(modifieddate);
			p.setSellstartdate(sellstartdate);
			p.setSellenddate(sellenddate);
			p.setDiscontinueddate(discontinueddate);
			p.setProductnumber("Cantidad");
			p.setWeight(new BigDecimal(20));
			p.setSize("-12");
			p.setColor("Verde");
			p.setDaystomanufacture(8);
			p.setName("Makinon");
			p.setProductsubcategory(psc);
			p.setProductmodel(pm);
			p.setUnitmeasure1(um1);
			p.setUnitmeasure2(um2);
			p.setProductid(1);
			p.setClass_("Producto");

			try {
				product = productService.save(p);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(4)
		void saveNullNumber() {
			p.setModifieddate(modifieddate);
			p.setSellstartdate(sellstartdate);
			p.setSellenddate(sellenddate);
			p.setDiscontinueddate(discontinueddate);
			p.setProductnumber(null);
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

			try {
				product = productService.save(p);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(5)
		void saveNullUnitMeasure() {
			p.setModifieddate(modifieddate);
			p.setSellstartdate(sellstartdate);
			p.setSellenddate(sellenddate);
			p.setDiscontinueddate(discontinueddate);
			p.setProductnumber("Cantidad");
			p.setWeight(new BigDecimal(20));
			p.setSize("-12");
			p.setColor("Verde");
			p.setDaystomanufacture(8);
			p.setName("Makinon");
			p.setProductsubcategory(psc);
			p.setProductmodel(pm);
			p.setUnitmeasure1(null);
			p.setUnitmeasure2(um2);
			p.setProductid(1);
			p.setClass_("Producto");

			try {
				product = productService.save(p);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(6)
		void saveSellstartdateGreaterThanSellenddate() {

			p.setModifieddate(modifieddate);
			p.setSellstartdate(sellenddate);
			p.setSellenddate(sellstartdate);
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

			try {
				product = productService.save(p);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(7)
		void saveNullProductmodel() {
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
			p.setProductmodel(null);
			p.setUnitmeasure1(um1);
			p.setUnitmeasure2(um2);
			p.setProductid(1);
			p.setClass_("Producto");

			try {
				product = productService.save(p);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(8)
		void saveNullProductsubcategory() {
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
			p.setProductsubcategory(null);
			p.setProductmodel(pm);
			p.setUnitmeasure1(um1);
			p.setUnitmeasure2(um2);
			p.setProductid(1);
			p.setClass_("Producto");

			try {
				product = productService.save(p);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(9)
		public void saveNull() {
			assertThrows(NullPointerException.class, () -> {
				productService.save(null);
			});
		}

	}

	@Nested

	@DisplayName("Edit method test")
	class ProductUpdate {

		@BeforeEach
		void setUpUpdate() {
			um1 = new Unitmeasure();
			// um1.setUnitmeasurecode("cm"); um1.setModifieddate(modifieddate);
			um1.setName("centimetros");
			productService.saveUnitmeasure(um1);

			um2 = new Unitmeasure(); // um2.setUnitmeasurecode("in");
			um2.setModifieddate(modifieddate);
			um2.setName("Pulgadas");
			productService.saveUnitmeasure(um2);

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

		@Order(10)
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

		@Test

		@Order(11)
		void updateNegativeWeight() {

			Product p1 = new Product();

			p1.setModifieddate(modifieddate);
			p1.setSellstartdate(sellstartdate);
			p1.setSellenddate(sellenddate);
			p1.setDiscontinueddate(discontinueddate);
			p1.setProductnumber("Cantidad");
			p1.setWeight(new BigDecimal(-20));
			p1.setSize("12");
			p1.setColor("Verde");
			p1.setDaystomanufacture(8);
			p1.setName("Makinon");
			p1.setProductsubcategory(psc);
			p1.setProductmodel(pm);
			p1.setUnitmeasure1(um1);
			p1.setUnitmeasure2(um2);
			p1.setProductid(1);
			p1.setClass_("Producto");

			try {
				product = productService.edit(p1);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(12)
		void updateNegativeSize() {

			Product p1 = new Product();

			p1.setModifieddate(modifieddate);
			p1.setSellstartdate(sellstartdate);
			p1.setSellenddate(sellenddate);
			p1.setDiscontinueddate(discontinueddate);
			p1.setProductnumber("Cantidad");
			p1.setWeight(new BigDecimal(20));
			p1.setSize("-12");
			p1.setColor("Verde");
			p1.setDaystomanufacture(8);
			p1.setName("Makinon");
			p1.setProductsubcategory(psc);
			p1.setProductmodel(pm);
			p1.setUnitmeasure1(um1);
			p1.setUnitmeasure2(um2);
			p1.setProductid(1);
			p1.setClass_("Producto");

			try {
				product = productService.edit(p1);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(13)
		void updateNullNumber() {

			Product p1 = new Product();

			p1.setModifieddate(modifieddate);
			p1.setSellstartdate(sellstartdate);
			p1.setSellenddate(sellenddate);
			p1.setDiscontinueddate(discontinueddate);
			p1.setProductnumber(null);
			p1.setWeight(new BigDecimal(20));
			p1.setSize("12");
			p1.setColor("Verde");
			p1.setDaystomanufacture(8);
			p1.setName("Makinon");
			p1.setProductsubcategory(psc);
			p1.setProductmodel(pm);
			p1.setUnitmeasure1(um1);
			p1.setUnitmeasure2(um2);
			p1.setProductid(1);
			p1.setClass_("Producto");

			try {
				product = productService.edit(p1);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(14)
		void updateNullUnitMeasure() {

			Product p1 = new Product();

			p1.setModifieddate(modifieddate);
			p1.setSellstartdate(sellstartdate);
			p1.setSellenddate(sellenddate);
			p1.setDiscontinueddate(discontinueddate);
			p1.setProductnumber("Cantidad");
			p1.setWeight(new BigDecimal(20));
			p1.setSize("12");
			p1.setColor("Verde");
			p1.setDaystomanufacture(8);
			p1.setName("Makinon");
			p1.setProductsubcategory(psc);
			p1.setProductmodel(pm);
			p1.setUnitmeasure1(um1);
			p1.setUnitmeasure2(null);
			p1.setProductid(1);
			p1.setClass_("Producto");

			try {
				product = productService.edit(p1);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}

		}

		@Test

		@Order(15)
		void updateSellstartdateGreaterThanSellenddate() {

			Product p1 = new Product();

			p1.setModifieddate(modifieddate);
			p1.setSellstartdate(sellenddate);
			p1.setSellenddate(sellstartdate);
			p1.setDiscontinueddate(discontinueddate);
			p1.setProductnumber("Cantidad");
			p1.setWeight(new BigDecimal(20));
			p1.setSize("12");
			p1.setColor("Verde");
			p1.setDaystomanufacture(8);
			p1.setName("Makinon");
			p1.setProductsubcategory(psc);
			p1.setProductmodel(pm);
			p1.setUnitmeasure1(um1);
			p1.setUnitmeasure2(um2);
			p1.setProductid(1);
			p1.setClass_("Producto");

			try {
				product = productService.edit(p1);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}

		}

		@Test

		@Order(16)
		void updateNullProductmodel() {

			Product p1 = new Product();

			p1.setModifieddate(modifieddate);
			p1.setSellstartdate(sellstartdate);
			p1.setSellenddate(sellenddate);
			p1.setDiscontinueddate(discontinueddate);
			p1.setProductnumber("Cantidad");
			p1.setWeight(new BigDecimal(20));
			p1.setSize("12");
			p1.setColor("Verde");
			p1.setDaystomanufacture(8);
			p1.setName("Makinon");
			p1.setProductsubcategory(psc);
			p1.setProductmodel(null);
			p1.setUnitmeasure1(um1);
			p1.setUnitmeasure2(um2);
			p1.setProductid(1);
			p1.setClass_("Producto");

			try {
				product = productService.edit(p1);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(17)
		void updateNullProductsubcategory() {

			Product p1 = new Product();

			p1.setModifieddate(modifieddate);
			p1.setSellstartdate(sellstartdate);
			p1.setSellenddate(sellenddate);
			p1.setDiscontinueddate(discontinueddate);
			p1.setProductnumber("Cantidad");
			p1.setWeight(new BigDecimal(20));
			p1.setSize("12");
			p1.setColor("Verde");
			p1.setDaystomanufacture(8);
			p1.setName("Makinon");
			p1.setProductsubcategory(null);
			p1.setProductmodel(pm);
			p1.setUnitmeasure1(um1);
			p1.setUnitmeasure2(um2);
			p1.setProductid(1);
			p1.setClass_("Producto");

			try {
				product = productService.edit(p1);

				Product found = productRepository.findById(product.getProductid()).get();
				Assertions.assertThat(found).hasAllNullFieldsOrProperties();
				assertNull(found);
			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}
		}

		@Test

		@Order(18)
		public void updateNull() {
			assertThrows(NullPointerException.class, () -> {
				productService.edit(null);
			});
		}

	}

	@AfterEach
	void tearDown() {
		System.gc();
	}

	@AfterAll
	static void tearDownAfterClass() {
		System.out.println("... TEST FINISHED ...");
	}

}
