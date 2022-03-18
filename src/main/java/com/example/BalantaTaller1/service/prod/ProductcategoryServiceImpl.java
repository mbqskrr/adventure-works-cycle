package com.example.BalantaTaller1.service.prod;

import javax.transaction.Transactional;

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
	public boolean save(Productcategory pc) {
		try {
			if (pc.getName().length()>=3 && !pc.getName().equals(null)) {
				productcategoryRepository.save(pc);
				return true;
			}else {
				throw new RuntimeException();
			}
		} catch (RuntimeException re) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean edit(Productcategory pc) {
		Productcategory tempPc = productcategoryRepository.getById(pc.getProductcategoryid());
		
		try {
			if (pc.getName().length()>=3 && !pc.getName().equals(null)) {
				tempPc.setModifieddate(pc.getModifieddate());
				tempPc.setName(pc.getName());
				tempPc.setRowguid(pc.getRowguid());
				tempPc.setProductsubcategories(pc.getProductsubcategories());
				productcategoryRepository.save(pc);
				return true;
			}else {
				throw new RuntimeException();
			}
		} catch (RuntimeException re) {
			return false;
		}
	}
	
	

}
