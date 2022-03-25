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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;

@ContextConfiguration(classes = BalantaTaller1Application.class)
@SpringBootTest()
class ProductcategoryTest {

	//Optional<Productcategory> pc;
	Productcategory pc;
	SimpleDateFormat df; //= new SimpleDateFormat("dd-MM-yyyy");
	Date date;  //= df.parse("24-03-2022");
	long time1; //= date.getTime();
	Timestamp time; //= new Timestamp(time1);
	
	@Mock
	private ProductcategoryRepository productcategoryRepository;
	@InjectMocks
	private ProductcategoryServiceImpl productcategoryService;

	@BeforeAll
	static void start() {
		System.out.println("... TEST STARTED ...");
	}
	
	@BeforeEach
	void setUp() throws ParseException {
		//pc = Optional.of(new Productcategory());
		pc = new Productcategory();
		df = new SimpleDateFormat("dd-MM-yyyy");
		date  = df.parse("24-03-2022");
		time1 = date.getTime();
		time = new Timestamp(time1);
	}

	@Nested
	@DisplayName("Save methods")
	class ProductcategoryServiceSave {
		@Test
		public void saveCorrectly() throws ParseException {
			
			/*pc.get().setProductcategoryid(7);
			pc.get().setName("Hogar");
			pc.get().setRowguid(14);
			pc.get().setModifieddate(time);
			pc.get().setProductsubcategories(null);*/
			pc.setProductcategoryid(7);
			pc.setName("Hogar");
			pc.setRowguid(14);
			pc.setModifieddate(time);
			pc.setProductsubcategories(null);
			//when(productcategoryRepository.findById(7)).thenReturn(pc);
			//Productcategory pdCt = new Productcategory();
			//when(productcategoryRepository.save(pc)).thenReturn(pc);
			when(productcategoryRepository.getById(7)).thenReturn(pc);
			try {
				productcategoryService.save(pc);
				/*Optional<Productcategory> tempPc =productcategoryService.save(pc);*/
				//assertNotNull(productcategoryService.save(pc));
				//Productcategory tempPc =productcategoryService.save(pc);
				//assertNotNull(productcategoryService.save(pc));
				
			}catch(RuntimeException rte) {
				rte.printStackTrace();
			}
			
			Productcategory testPc = productcategoryRepository.getById(7);
			assertNotNull(testPc);
			assertFalse(testPc.getName().isEmpty());
			assertEquals(testPc.getProductcategoryid(), 7);
			assertNull(testPc.getProductsubcategories());
			assertEquals(testPc.getName(), "Hogar");
			assertEquals(testPc.getModifieddate(), time);
			verify(productcategoryRepository).getById(7);
			verify(productcategoryRepository).save(pc);
			
		}
		
		@Test
		public void saveWithNameLesserThanThreeChar() throws ParseException {
			
			/*pc.get().setProductcategoryid(6);
			pc.get().setName("I");
			pc.get().setRowguid(13);
			pc.get().setModifieddate(time);
			pc.get().setProductsubcategories(null);*/
			pc.setProductcategoryid(6);
			pc.setName("I");
			pc.setRowguid(13);
			pc.setModifieddate(time);
			pc.setProductsubcategories(null);
			when(productcategoryRepository.findById(6)).thenThrow(RuntimeException.class);
			
			try {
				assertThrows(RuntimeException.class, ()->{
					productcategoryService.save(pc);
				});
				//Optional<Productcategory> tempPc =productcategoryService.save(pc);
				//assertNull(tempPc);
				Productcategory tempPc =productcategoryService.save(pc);
				assertNull(tempPc);
			}catch(RuntimeException rte) {
				rte.printStackTrace();
			}
			
		}
		
		@Test
		public void saveNull() {
			assertThrows(NullPointerException.class, () -> {
				productcategoryService.save(null);
			});
		}
	}
	
	@Nested
	@DisplayName("Update methods")
	class ProductcategoryServiceEdit{
		
	}

	@AfterAll
	static void finish() {
		System.out.println("... TEST FINISHED ...");
	}

}
