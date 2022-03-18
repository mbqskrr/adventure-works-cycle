package com.example.BalantaTaller1.service.prod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
import com.example.BalantaTaller1.repository.prod.ProductsubcategoryRepository;

@Service
public class ProductsubcategoryServiceImpl implements ProductsubcategoryService{
	
	private ProductsubcategoryRepository productsubcategoryRepository;
	private ProductcategoryRepository productcategoryRepository;
	
	@Autowired
	public ProductsubcategoryServiceImpl(ProductsubcategoryRepository productsubcategoryRepository, ProductcategoryRepository productcategoryRepository) {
		this.productcategoryRepository = productcategoryRepository;
		this.productsubcategoryRepository = productsubcategoryRepository;
	}

	@Override
	@Transactional
	public boolean save(Productsubcategory psc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean edit(Productsubcategory psc) {
		// TODO Auto-generated method stub
		return false;
	}

}
