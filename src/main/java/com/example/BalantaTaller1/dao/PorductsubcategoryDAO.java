package com.example.BalantaTaller1.dao;

import java.util.List;

import com.example.BalantaTaller1.model.prod.Productsubcategory;

public interface PorductsubcategoryDAO {
	
	public void save(Productsubcategory psc);

	public void update(Productsubcategory psc);

	public List<Productsubcategory> findAll();

	public Productsubcategory findById(Integer id);

}
