package com.example.BalantaTaller1.service.prod;



import java.util.Optional;

//import java.util.Optional;

import com.example.BalantaTaller1.model.prod.Productcategory;

public interface ProductcategoryService {
	
	public Productcategory save(Productcategory pc);
	
	public Productcategory edit(Productcategory pc);

	public Iterable<Productcategory> findAll();

	public Optional<Productcategory> findById(Integer id);
	
}
