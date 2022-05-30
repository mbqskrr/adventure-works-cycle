package com.example.BalantaTaller1.dao;


import java.util.List;
import java.util.Optional;

import com.example.BalantaTaller1.model.prod.Productcategory;


public interface ProductcategoryDAO {

	public void save(Productcategory pc);

	public void update(Productcategory pc);

	public List<Productcategory> findAll();

	public Productcategory findById(Integer productcategoryid);
	
	

}
