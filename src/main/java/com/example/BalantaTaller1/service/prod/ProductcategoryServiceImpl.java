package com.example.BalantaTaller1.service.prod;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.validation.ProductcategoryValidation;
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
	public Productcategory save(@Validated(ProductcategoryValidation.class) Productcategory pc) {
		Productcategory tempPc = null;

		constraints(pc);

		tempPc = productcategoryRepository.save(pc);

		return tempPc;
	}

	@Override
	@Transactional
	public Productcategory edit(Productcategory pc) {

		Productcategory temp = null;

		Optional<Productcategory> optional = productcategoryRepository.findById(pc.getProductcategoryid());

		if (optional.isPresent()) {
			constraints(pc);

			temp = save(pc);
		}

		return temp;

	}

	@Override
	public Iterable<Productcategory> findAll() {
		return productcategoryRepository.findAll();
	}

	@Override
	public Optional<Productcategory> findById(Integer id) {
		return productcategoryRepository.findById(id);
	}

	//@NotNull
	private void constraints(Productcategory pc) {
		if (pc.getName().length() < 3 || pc.getName() == null) {
			throw new RuntimeException("El nombre de la categoria no tiene al menos 3 carácteres");

		}

	}

}
