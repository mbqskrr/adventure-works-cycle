package com.example.BalantaTaller1.service.prod;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.model.prod.Productcategory;
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
		
		Productsubcategory temp = null;
		
		constraints(psc);
		
		Optional<Productcategory> optional = this.productcategoryRepository.findById(psc.getProductcategory().getProductcategoryid()); 
		if(optional.isPresent()) {
			psc.setProductcategory(optional.get());
			temp = productsubcategoryRepository.save(psc);
		}
		
		return temp;
	}

	@Override
	@Transactional
	public Productsubcategory edit(Productsubcategory psc) {
		Productsubcategory temp = null;
		
		Optional<Productsubcategory> optional = this.productsubcategoryRepository.findById(psc.getProductsubcategoryid());
		
		if(optional.isPresent()) {
			constraints(psc);
			temp = save(psc);
		}
		
		return temp;
	}
	
	@NotNull
	private void constraints(Productsubcategory psc) {
		if(psc.getName().length()<=5 || psc.getName()==null) {
			throw new RuntimeException("Nombre de subcategoria no valido");
		}
		if (productcategoryRepository.findById(psc.getProductcategory().getProductcategoryid()).isEmpty()) {
			throw new RuntimeException("Categoria asociada no existe");
		}
	}

}
