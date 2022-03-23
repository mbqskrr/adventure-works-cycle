package com.example.BalantaTaller1.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.BalantaTaller1.main.BalantaTaller1Application;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
import com.example.BalantaTaller1.service.prod.ProductcategoryService;
import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BalantaTaller1Application.class)
@ContextConfiguration(classes = ProductcategoryRepository.class)
class ProductcategoryITest {

	private ProductcategoryRepository productcategoryRepository;
	private ProductcategoryService productcategoryService;

	@Autowired
	public ProductcategoryITest(ProductcategoryRepository productcategoryRepository) {
		this.productcategoryRepository = productcategoryRepository;
		this.productcategoryService = new ProductcategoryServiceImpl(productcategoryRepository);
	}

	@BeforeAll
	static void start() {
		System.out.println("... TEST STARTED ...");
	}

	@Nested
	@DisplayName("Save methods")
	class ProductcategoryServiceSave {
		@Test
		public void saveCorrectly() {
			Productcategory productCategory = new Productcategory();
			
			try {
				productCategory.setProductcategoryid(13);
				productCategory.setName("Hogar");
				productCategory.setModifieddate(null);
				productCategory.setProductsubcategories(null);
				productcategoryService.save(productCategory);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			assertTrue(productCategory.equals(productcategoryRepository.findById(productCategory.getProductcategoryid()).get()));
			//fail("Not yet implemented");
		}
	}

	@AfterAll
	static void finish() {
		System.out.println("... TEST FINISHED ...");
	}

}
