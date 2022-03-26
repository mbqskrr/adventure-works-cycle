package com.example.BalantaTaller1.service.prod;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
//import com.sun.istack.NotNull;

@Service
public class ProductcategoryServiceImpl implements ProductcategoryService {

	private ProductcategoryRepository productcategoryRepository;

	@Autowired
	public ProductcategoryServiceImpl(ProductcategoryRepository productcategoryRepository) {
		this.productcategoryRepository = productcategoryRepository;
	}

	@Override
	@Transactional
	public Productcategory save(Productcategory pc) {
		Productcategory tempPc = null;
		
		constrains(pc);
		
		tempPc = productcategoryRepository.save(pc);

		
		return tempPc;
	}

	@Override
	@Transactional
	public Productcategory edit(Productcategory pc) {
	
		Productcategory temp = null;
		
		
		Optional<Productcategory> optional = productcategoryRepository.findById(pc.getProductcategoryid());
		
		if (optional.isPresent()) {
			constrains(pc);
			
			temp = save(pc);
		}
		
		return temp;

	}
	
	@NotNull
	public void constrains(Productcategory pc) {
		if (pc.getName().length() <= 3 || pc.getName()==null) {
			throw new RuntimeException("El nombre de la categoria no tiene al menos 3 carácteres");

		}
	}

}
