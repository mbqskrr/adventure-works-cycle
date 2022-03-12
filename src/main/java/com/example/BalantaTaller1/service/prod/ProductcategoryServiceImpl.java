package com.example.BalantaTaller1.service.prod;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;

public class ProductcategoryServiceImpl implements ProductcategoryService{

	private ProductcategoryRepository productcategoryRepository;
	
	@Autowired
	public ProductcategoryServiceImpl(ProductcategoryRepository productcategoryRepository){
		this.productcategoryRepository = productcategoryRepository;
	}
	
	@Override
	public Productcategory save(Productcategory pc) {
		return productcategoryRepository.save(pc);
	}
	
	

}
