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
	public Productsubcategory save(Productsubcategory psc) {
		if(psc.getName().length()>=5 
				|| productcategoryRepository.findById(psc.getProductcategory().getProductcategoryid()).isEmpty()) {
			throw new RuntimeException();
		}
		psc.setProductcategory(productcategoryRepository.getById(psc.getProductcategory().getProductcategoryid()));
		return productsubcategoryRepository.save(psc);
	}

	@Override
	@Transactional
	public Productsubcategory edit(Productsubcategory psc) {
		if(psc.getName().length()>=5 
				|| productcategoryRepository.findById(psc.getProductcategory().getProductcategoryid()).isEmpty()) {
			throw new RuntimeException();
		}
		Productsubcategory tempPsc = productsubcategoryRepository.getById(psc.getProductsubcategoryid());
		tempPsc.setModifieddate(psc.getModifieddate());
		tempPsc.setName(psc.getName());
		tempPsc.setProducts(psc.getProducts());
		tempPsc.setProductcategory(productcategoryRepository.getById(psc.getProductcategory().getProductcategoryid()));
		return productsubcategoryRepository.save(tempPsc);
	}

}
