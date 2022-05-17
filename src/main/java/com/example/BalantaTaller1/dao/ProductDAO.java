package com.example.BalantaTaller1.dao;

import java.util.List;

import com.example.BalantaTaller1.model.prod.Product;
//import com.example.BalantaTaller1.model.prod.Productsubcategory;

public interface ProductDAO {
	
	public void save(Product product);

	public void update(Product product);

	public List<Product> findAll();

	public Product findById(Integer id);
	
	public List<Product> findByProductsubcategoryId(Integer productsubcategoryid);
	
	public List<Product> findByProductmodel(Integer productmodelid);
	
	public List<Product> findByUnitmeasureSizeCode(String unitmeasurecode);
	
	public List<Product> findByAtLeastTwoWorkorders();

}
