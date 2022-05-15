package com.example.BalantaTaller1.integration;

/*import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
import com.example.BalantaTaller1.repository.prod.ProductsubcategoryRepository;
import com.example.BalantaTaller1.service.prod.ProductsubcategoryServiceImpl;

@ContextConfiguration(classes = BalantaTaller1Application.class)
@SpringBootTest*/
public class ProductsubcategoryIntegrationTest {

	/*
	 * private Productsubcategory psc; private Productsubcategory
	 * productsubcategory;
	 * 
	 * private Productcategory pc;
	 * 
	 * private SimpleDateFormat df; private Date date; private long time1; private
	 * Timestamp time;
	 * 
	 * 
	 * private ProductsubcategoryRepository productsubcategoryRepository;
	 * 
	 * private ProductcategoryRepository productcategoryRepository;
	 * 
	 * private ProductsubcategoryServiceImpl productsubcategoryService;
	 * 
	 * //private TestEntityManager testEntityManager;
	 * 
	 * @Autowired public
	 * ProductsubcategoryIntegrationTest(ProductsubcategoryRepository
	 * productsubcategoryRepository, ProductcategoryRepository
	 * productcategoryRepository, ProductsubcategoryServiceImpl
	 * productsubcategoryService TestEntityManager testEntityManager) {
	 * this.productsubcategoryRepository = productsubcategoryRepository;
	 * this.productcategoryRepository = productcategoryRepository;
	 * this.productsubcategoryService = productsubcategoryService;
	 * //this.testEntityManager = testEntityManager; }
	 * 
	 * @BeforeAll static void start() { System.out.println("... TEST STARTED ...");
	 * }
	 * 
	 * @BeforeEach void setUp() throws ParseException { psc = new
	 * Productsubcategory(); pc = new Productcategory(); df = new
	 * SimpleDateFormat("dd-MM-yyyy"); date = df.parse("27-03-2022"); time1 =
	 * date.getTime(); time = new Timestamp(time1);
	 * 
	 * pc.setName("Hogar"); pc.setRowguid(14); pc.setModifieddate(time);
	 * 
	 * pc.setProductsubcategories(null);
	 * 
	 * //testEntityManager.persist(pc); productcategoryRepository.save(pc); }
	 * 
	 * 
	 * @Nested
	 * 
	 * @DisplayName("Save method test") class ProductsubcategorySave {
	 * 
	 * @Test
	 * 
	 * @Order(1) void saveCorrectly() { psc.setModifieddate(time);
	 * psc.setName("Aluminio"); psc.setProducts(null); psc.setRowguid(666);
	 * psc.setProductcategory(pc);
	 * 
	 * productsubcategory = productsubcategoryService.save(psc);
	 * 
	 * Productsubcategory found =
	 * productsubcategoryRepository.findById(productsubcategory.
	 * getProductsubcategoryid()).get(); assertNotNull(found);
	 * assertEquals(productsubcategory.getProductsubcategoryid(),
	 * found.getProductsubcategoryid());
	 * Assertions.assertThat(found).isInstanceOfAny(Productsubcategory.class);
	 * Assertions.assertThat(found.getProductsubcategoryid()).isGreaterThan(0);
	 * Assertions.assertThat(found).hasNoNullFieldsOrProperties();
	 * assertEquals(found.getName(), "Aluminio"); assertEquals(1,
	 * found.getProductsubcategoryid()); }
	 * 
	 * @Test
	 * 
	 * @Order(2) void saveNameLesserThanFiveChar() { psc.setModifieddate(time);
	 * psc.setName("Al"); psc.setProducts(null); psc.setProductsubcategoryid(985);
	 * psc.setRowguid(666); psc.setProductcategory(pc);
	 * 
	 * try { productsubcategory = productsubcategoryService.save(psc);
	 * 
	 * Productsubcategory found =
	 * productsubcategoryRepository.findById(productsubcategory.
	 * getProductsubcategoryid()).get();
	 * Assertions.assertThat(found).hasAllNullFieldsOrProperties();
	 * assertNull(found); } catch (RuntimeException rte) { rte.printStackTrace(); }
	 * }
	 * 
	 * @Test
	 * 
	 * @Order(3) void saveNullProductcategoryid() { psc.setModifieddate(time);
	 * psc.setName("Aluminio"); psc.setProducts(null);
	 * psc.setProductsubcategoryid(985); psc.setRowguid(666);
	 * psc.setProductcategory(null);
	 * 
	 * try { productsubcategory = productsubcategoryService.save(psc);
	 * Productsubcategory found =
	 * productsubcategoryRepository.findById(productsubcategory.
	 * getProductsubcategoryid()).get();
	 * Assertions.assertThat(found).hasAllNullFieldsOrProperties();
	 * assertNull(found); } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(4) public void saveNull() { assertThrows(NullPointerException.class,
	 * () -> { productsubcategoryService.save(null); }); } }
	 * 
	 * @Nested
	 * 
	 * @DisplayName("Edit method test") class ProductsubcategoryUpdate {
	 * 
	 * @BeforeEach void setUpUpdate() { psc.setModifieddate(time);
	 * psc.setName("Aluminio"); psc.setProducts(null); psc.setRowguid(666);
	 * psc.setProductcategory(pc);
	 * 
	 * productsubcategoryService.save(psc); }
	 * 
	 * @Test
	 * 
	 * @Order(5) void updateCorrectly() {
	 * 
	 * Productsubcategory psc1 = new Productsubcategory();
	 * 
	 * psc1.setProductsubcategoryid(1); psc1.setModifieddate(time);
	 * psc1.setName("Estano"); psc1.setProducts(null); psc1.setRowguid(666);
	 * psc1.setProductcategory(pc);
	 * 
	 * productsubcategory = productsubcategoryService.edit(psc1); Productsubcategory
	 * found = productsubcategoryRepository.findById(productsubcategory.
	 * getProductsubcategoryid()).get(); assertNotNull(found);
	 * assertEquals(productsubcategory.getProductsubcategoryid(),
	 * found.getProductsubcategoryid());
	 * Assertions.assertThat(found).isInstanceOfAny(Productsubcategory.class);
	 * Assertions.assertThat(found.getProductsubcategoryid()).isGreaterThan(0);
	 * Assertions.assertThat(found).hasNoNullFieldsOrProperties();
	 * assertEquals("Estano", found.getName()); }
	 * 
	 * @Test
	 * 
	 * @Order(6) void updateNameLesserThanFiveChar() {
	 * 
	 * Productsubcategory psc1 = new Productsubcategory();
	 * 
	 * psc1.setProductsubcategoryid(1); psc1.setModifieddate(time);
	 * psc1.setName("Sn"); psc1.setProducts(null); psc1.setRowguid(666);
	 * psc1.setProductcategory(pc);
	 * 
	 * try { productsubcategory = productsubcategoryService.edit(psc1);
	 * Productcategory found =
	 * productcategoryRepository.findById(productsubcategory.getProductsubcategoryid
	 * ()).get(); Assertions.assertThat(found).hasAllNullFieldsOrProperties();
	 * assertNull(found); } catch (RuntimeException rte) { rte.printStackTrace(); }
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @Order(7) void updateNullProductcategoryid() {
	 * 
	 * Productsubcategory psc1 = new Productsubcategory();
	 * 
	 * psc1.setProductsubcategoryid(null); psc1.setModifieddate(time);
	 * psc1.setName("Estano"); psc1.setProducts(null); psc1.setRowguid(666);
	 * psc1.setProductcategory(pc);
	 * 
	 * try { productsubcategory = productsubcategoryService.edit(psc1);
	 * Productcategory found =
	 * productcategoryRepository.findById(productsubcategory.getProductsubcategoryid
	 * ()).get(); Assertions.assertThat(found).hasAllNullFieldsOrProperties();
	 * assertNull(found); } catch (RuntimeException rte) { rte.printStackTrace(); }
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @Order(8) public void updateNonExistingId() { Productsubcategory psc1 = new
	 * Productsubcategory();
	 * 
	 * psc1.setProductsubcategoryid(44); psc1.setModifieddate(time);
	 * psc1.setName("Estano"); psc1.setProducts(null); psc1.setRowguid(666);
	 * psc1.setProductcategory(pc); productsubcategory =
	 * productsubcategoryService.edit(psc1);
	 * assertThrows(NullPointerException.class, () -> {
	 * productsubcategoryRepository.findById(productsubcategory.
	 * getProductsubcategoryid()).get(); }); }
	 * 
	 * @Test
	 * 
	 * @Order(9) public void updateWithNullProductcategory() { Productsubcategory
	 * psc1 = new Productsubcategory();
	 * 
	 * psc1.setProductsubcategoryid(44); psc1.setModifieddate(time);
	 * psc1.setName("Estano"); psc1.setProducts(null); psc1.setRowguid(666);
	 * psc1.setProductcategory(null); productsubcategory =
	 * productsubcategoryService.edit(psc1);
	 * assertThrows(NullPointerException.class, () -> {
	 * productsubcategoryRepository.findById(productsubcategory.
	 * getProductsubcategoryid()).get(); }); }
	 * 
	 * @Test
	 * 
	 * @Order(10) public void udateNull() { assertThrows(NullPointerException.class,
	 * () -> { productsubcategoryService.edit(null); }); } }
	 * 
	 * 
	 * @AfterAll static void end() { System.out.println("... TEST FINISHED ..."); }
	 * 
	 * @AfterEach void tearDown() { pc = null; productcategoryRepository = null; psc
	 * = null; productsubcategoryRepository = null; productsubcategory = null;
	 * System.gc(); }
	 */

}
