package com.example.BalantaTaller1.test;

/*import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Unitmeasure;
import com.example.BalantaTaller1.repository.prod.ProductRepository;
import com.example.BalantaTaller1.repository.prod.ProductmodelRepository;
import com.example.BalantaTaller1.repository.prod.ProductsubcategoryRepository;
import com.example.BalantaTaller1.repository.prod.UnitmeasureRepository;
import com.example.BalantaTaller1.service.prod.ProductServiceImpl;

@ContextConfiguration(classes = BalantaTaller1Application.class)
@SpringBootTest()*/
class ProductTest {
	
	/*
	 * private Unitmeasure um1; private Unitmeasure um2; private Product p; private
	 * Productsubcategory psc; private Productmodel pm; private SimpleDateFormat df;
	 * private Date date; private long time1; private Timestamp modifieddate;
	 * 
	 * private SimpleDateFormat df1; private Date date1; private long time2; private
	 * Timestamp discontinueddate;
	 * 
	 * private SimpleDateFormat df2; private Date date2; private long time3; private
	 * Timestamp sellstartdate;
	 * 
	 * private SimpleDateFormat df3; private Date date3; private long time4; private
	 * Timestamp sellenddate;
	 * 
	 * @Mock private UnitmeasureRepository unitmeasureRepository;
	 * 
	 * @Mock private ProductRepository productRepository;
	 * 
	 * @Mock private ProductmodelRepository productmodelRepository;
	 * 
	 * @Mock private ProductsubcategoryRepository productsubcategoryRepository;
	 * 
	 * @InjectMocks private ProductServiceImpl productService;
	 * 
	 * @BeforeAll static void start() { System.out.println("... TEST STARTED ...");
	 * }
	 * 
	 * @BeforeEach void setUp() throws ParseException { um1 = new Unitmeasure(); um2
	 * = new Unitmeasure(); p = new Product(); pm = new Productmodel(); psc = new
	 * Productsubcategory();
	 * 
	 * df = new SimpleDateFormat("dd-MM-yyyy"); date = df.parse("27-03-2022"); time1
	 * = date.getTime(); modifieddate = new Timestamp(time1);
	 * 
	 * df1 = new SimpleDateFormat("dd-MM-yyyy"); date1 = df1.parse("06-08-2023");
	 * time2 = date1.getTime(); discontinueddate = new Timestamp(time2);
	 * 
	 * df2 = new SimpleDateFormat("dd-MM-yyyy"); date2 = df2.parse("30-03-2022");
	 * time3 = date2.getTime(); sellstartdate = new Timestamp(time3);
	 * 
	 * df3 = new SimpleDateFormat("dd-MM-yyyy"); date3 = df3.parse("13-04-2022");
	 * time4 = date3.getTime(); sellenddate = new Timestamp(time4); }
	 * 
	 * @Nested
	 * 
	 * @DisplayName("Save methods") class ProductSave {
	 * 
	 * @Test
	 * 
	 * @Order(1) void saveCorrectly() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber("Cantidad"); p.setWeight(new BigDecimal(20));
	 * p.setSize("120"); p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Verde"); p.setDaystomanufacture(8); p.setName("Makinon");
	 * p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.save(p)).thenReturn(p);
	 * 
	 * Product test = productService.save(p); assertNotNull(test);
	 * assertEquals("Makinon", test.getName()); assertEquals(003,
	 * test.getProductid()); verify(productRepository).save(p); }
	 * 
	 * @Test
	 * 
	 * @Order(2) void saveNegativeWeight() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber("Cantidad"); p.setWeight(new BigDecimal(-20));
	 * p.setSize("120"); p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Verde"); p.setDaystomanufacture(8); p.setName("Makinon");
	 * p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.save(p)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.save(p); }); try
	 * { Product test = productService.save(p); assertNull(test); } catch
	 * (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(3) void saveNegativeSize() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber("Cantidad"); p.setWeight(new BigDecimal(20));
	 * p.setSize("-120"); p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Verde"); p.setDaystomanufacture(8); p.setName("Makinon");
	 * p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.save(p)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.save(p); }); try
	 * { Product test = productService.save(p); assertNull(test); } catch
	 * (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(4) void saveNullNumber() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber(null); p.setWeight(new BigDecimal(20)); p.setSize("120");
	 * p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Verde"); p.setDaystomanufacture(8); p.setName("Makinon");
	 * p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.save(p)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.save(p); }); try
	 * { Product test = productService.save(p); assertNull(test); } catch
	 * (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(5) void saveNullUnitMeasure() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber("Cantidad"); p.setWeight(new BigDecimal(20));
	 * p.setSize("120"); p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Verde"); p.setDaystomanufacture(8); p.setName("Makinon");
	 * p.setWorkorders(null);
	 * 
	 * um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.save(p)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.save(p); }); try
	 * { Product test = productService.save(p); assertNull(test); } catch
	 * (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(6) void saveSellstartdateGreaterThanSellenddate() {
	 * p.setProductid(003); p.setModifieddate(modifieddate);
	 * p.setSellstartdate(sellenddate); p.setSellenddate(sellstartdate);
	 * p.setDiscontinueddate(discontinueddate); p.setProductnumber("Cantidad");
	 * p.setWeight(new BigDecimal(20)); p.setSize("120");
	 * p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Verde"); p.setDaystomanufacture(8); p.setName("Makinon");
	 * p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.save(p)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.save(p); }); try
	 * { Product test = productService.save(p); assertNull(test); } catch
	 * (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(7) void saveNullProductmodel() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber(null); p.setWeight(new BigDecimal(20)); p.setSize("120");
	 * p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Verde"); p.setDaystomanufacture(8); p.setName("Makinon");
	 * p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.save(p)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.save(p); }); try
	 * { Product test = productService.save(p); assertNull(test); } catch
	 * (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(8) void saveNullProductsubcategory() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber(null); p.setWeight(new BigDecimal(20)); p.setSize("120");
	 * p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Verde"); p.setDaystomanufacture(8); p.setName("Makinon");
	 * p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * 
	 * when(productRepository.save(p)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.save(p); }); try
	 * { Product test = productService.save(p); assertNull(test); } catch
	 * (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(9) public void saveNull() { assertThrows(NullPointerException.class,
	 * () -> { productService.save(null); }); } }
	 * 
	 * @Nested
	 * 
	 * @DisplayName("Update methods") class ProductUpdate {
	 * 
	 * @Test
	 * 
	 * @Order(10) void updateCorrectly() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber("Cantidad"); p.setWeight(new BigDecimal(20));
	 * p.setSize("120"); p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Azul"); p.setDaystomanufacture(8); p.setName("Bichota");
	 * p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.findById(003)).thenReturn(Optional.of(p));
	 * 
	 * productService.edit(p);
	 * 
	 * Product test = productRepository.findById(003).get(); assertNotNull(test);
	 * assertEquals("Bichota", test.getName()); assertEquals(003,
	 * test.getProductid()); verify(productRepository,
	 * VerificationModeFactory.times(2)).findById(003); }
	 * 
	 * @Test
	 * 
	 * @Order(11) void updateNegativeWeight() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber("Cantidad"); p.setWeight(new BigDecimal(-20));
	 * p.setSize("120"); p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Azul"); p.setDaystomanufacture(8); p.setName("Bichota");
	 * p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.findById(003)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.edit(p); }); try
	 * { Product test = productRepository.findById(003).get(); assertNull(test); }
	 * catch (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(12) void updateNegativeSize() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber("Cantidad"); p.setWeight(new BigDecimal(20));
	 * p.setSize("-120"); p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Azul"); p.setDaystomanufacture(8); p.setName("Bichota");
	 * p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.findById(003)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.edit(p); }); try
	 * { Product test = productRepository.findById(003).get(); assertNull(test); }
	 * catch (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(13) void updateNullNumber() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber(null); p.setWeight(new BigDecimal(20)); p.setSize("120");
	 * p.setBillofmaterials1(null); p.setBillofmaterials2(null); p.setColor("Azul");
	 * p.setDaystomanufacture(8); p.setName("Bichota"); p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.findById(003)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.edit(p); }); try
	 * { Product test = productRepository.findById(003).get(); assertNull(test); }
	 * catch (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(14) void updateNullUnitMeasure() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber("Cantidad"); p.setWeight(new BigDecimal(20));
	 * p.setSize("120"); p.setBillofmaterials1(null); p.setBillofmaterials2(null);
	 * p.setColor("Azul"); p.setDaystomanufacture(8); p.setName("Bichota");
	 * p.setWorkorders(null);
	 * 
	 * um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.findById(003)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.edit(p); }); try
	 * { Product test = productRepository.findById(003).get(); assertNull(test); }
	 * catch (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(15) void updateSellstartdateGreaterThanSellenddate() {
	 * p.setProductid(003); p.setModifieddate(modifieddate);
	 * p.setSellstartdate(sellenddate); p.setSellenddate(sellstartdate);
	 * p.setDiscontinueddate(discontinueddate); p.setProductnumber("Cantidad");
	 * p.setWeight(new BigDecimal(20)); p.setSize("120");
	 * p.setBillofmaterials1(null); p.setBillofmaterials2(null); p.setColor("Azul");
	 * p.setDaystomanufacture(8); p.setName("Bichota"); p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.findById(003)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.edit(p); }); try
	 * { Product test = productRepository.findById(003).get(); assertNull(test); }
	 * catch (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(16) void updateNullProductmodel() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber(null); p.setWeight(new BigDecimal(20)); p.setSize("120");
	 * p.setBillofmaterials1(null); p.setBillofmaterials2(null); p.setColor("Azul");
	 * p.setDaystomanufacture(8); p.setName("Bichota"); p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * psc.setProductsubcategoryid(39);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productsubcategoryRepository.findById(39)).thenReturn(Optional.of(psc));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductsubcategory(productsubcategoryRepository.findById(39).get());
	 * 
	 * when(productRepository.findById(003)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.edit(p); }); try
	 * { Product test = productRepository.findById(003).get(); assertNull(test); }
	 * catch (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(17) void updateNullProductsubcategory() { p.setProductid(003);
	 * p.setModifieddate(modifieddate); p.setSellstartdate(sellstartdate);
	 * p.setSellenddate(sellenddate); p.setDiscontinueddate(discontinueddate);
	 * p.setProductnumber(null); p.setWeight(new BigDecimal(20)); p.setSize("120");
	 * p.setBillofmaterials1(null); p.setBillofmaterials2(null); p.setColor("Azul");
	 * p.setDaystomanufacture(8); p.setName("Bichota"); p.setWorkorders(null);
	 * 
	 * um1.setUnitmeasurecode("A00"); um2.setUnitmeasurecode("A35");
	 * 
	 * pm.setProductmodelid(9999);
	 * 
	 * when(unitmeasureRepository.findById("A00")).thenReturn(Optional.of(um1));
	 * when(unitmeasureRepository.findById("A35")).thenReturn(Optional.of(um2));
	 * when(productmodelRepository.findById(9999)).thenReturn(Optional.of(pm));
	 * 
	 * p.setUnitmeasure1(unitmeasureRepository.findById("A00").get());
	 * p.setUnitmeasure2(unitmeasureRepository.findById("A35").get());
	 * p.setProductmodel(productmodelRepository.findById(9999).get());
	 * 
	 * when(productRepository.findById(003)).thenThrow(RuntimeException.class);
	 * 
	 * assertThrows(RuntimeException.class, () -> { productService.edit(p); }); try
	 * { Product test = productRepository.findById(003).get(); assertNull(test); }
	 * catch (RuntimeException e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Order(18) public void updateNull() {
	 * assertThrows(NullPointerException.class, () -> { productService.edit(null);
	 * }); }
	 * 
	 * }
	 * 
	 * @AfterEach void tearDown() throws Exception { um1 = null; um2 = null; p =
	 * null; pm = null; psc = null;
	 * 
	 * System.gc(); }
	 * 
	 * @AfterAll static void end() { System.out.println("... TEST FINISHED ..."); }
	 */

}
