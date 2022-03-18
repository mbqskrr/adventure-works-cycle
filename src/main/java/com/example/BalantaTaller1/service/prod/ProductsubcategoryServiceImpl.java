package com.example.BalantaTaller1.service.prod;

import org.springframework.stereotype.Service;

import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
import com.example.BalantaTaller1.repository.prod.ProductsubcategoryRepository;

@Service
public class ProductsubcategoryServiceImpl implements ProductsubcategoryService{
	
	private ProductsubcategoryRepository productsubcategoryRepository;
	private ProductcategoryRepository productcategoryRepository;
	
	public ProductsubcategoryServiceImpl(ProductsubcategoryRepository productsubcategoryRepository, ProductcategoryRepository productcategoryRepository) {
		this.productcategoryRepository = productcategoryRepository;
		this.productsubcategoryRepository = productsubcategoryRepository;
	}

	@Override
	public boolean save(Productsubcategory psc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(Productsubcategory psc) {
		// TODO Auto-generated method stub
		return false;
	}

}
