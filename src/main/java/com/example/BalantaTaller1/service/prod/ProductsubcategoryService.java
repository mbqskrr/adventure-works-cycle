package com.example.BalantaTaller1.service.prod;

import java.util.Optional;

import com.example.BalantaTaller1.model.prod.Productsubcategory;

public interface ProductsubcategoryService {
	
	public Productsubcategory save(Productsubcategory psc);
	
	public Productsubcategory edit(Productsubcategory  psc);

	public Iterable<Productsubcategory> findAll();

	public Iterable<Productsubcategory> findByProductcategory(Integer id);

	public Optional<Productsubcategory> findById(Integer id);

}
