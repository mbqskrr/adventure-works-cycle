package com.example.BalantaTaller1.dao;

import java.util.List;

import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Productsubcategory;

public interface ProductDAO {
	
	public void save(Product product);

	public void update(Product product);

	public List<Product> findAll();

	public Product findById(Integer id);
	
	public List<Productsubcategory> findByProductsubcategoryId(Integer productsubcategoryid);
	
	public List<Productsubcategory> findByProductmodel(Integer productmodelid);
	
	public List<Productsubcategory> findByUnitmeasureSizeCode(String unitmeasurecode);
	
	public List<Product> findAllByAtLeastTwoWorkorders();

}
