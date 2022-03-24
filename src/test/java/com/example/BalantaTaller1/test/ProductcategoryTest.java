package com.example.BalantaTaller1.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
//import com.example.BalantaTaller1.service.prod.ProductcategoryService;
//import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;


//@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BalantaTaller1Application.class)
@SpringBootTest()
class ProductcategoryTest {

	@Mock
	private ProductcategoryRepository productcategoryRepository;
	@InjectMocks
	private ProductcategoryServiceImpl productcategoryService;

	/*@Autowired
	public ProductcategoryTest(ProductcategoryRepository productcategoryRepository) {
		this.productcategoryService = new ProductcategoryServiceImpl(productcategoryRepository);
	}*/

	@BeforeAll
	static void start() {
		System.out.println("... TEST STARTED ...");
	}

	@Nested
	@DisplayName("Save methods")
	class ProductcategoryServiceSave {
		@Test
		public void saveCorrectly() {
			Optional<Productcategory> pc = Optional.of(new Productcategory());
			//Productcategory pc = new Productcategory();
			pc.get().setProductcategoryid(7);
			pc.get().setName("Hogar");
			pc.get().setRowguid(14);
			pc.get().setModifieddate(null);
			pc.get().setProductsubcategories(null);
			when(productcategoryRepository.findById(7)).thenReturn(pc);
			
			try {
				productcategoryService.save(pc);
				
			}catch(RuntimeException rte) {
				rte.printStackTrace();
			}
			
			Productcategory testPc = productcategoryRepository.findById(7).get();
			
			assertFalse(testPc.getName().isEmpty());
			
			//fail("Not yet implemented");
		}
	}

	@AfterAll
	static void finish() {
		System.out.println("... TEST FINISHED ...");
	}

}
