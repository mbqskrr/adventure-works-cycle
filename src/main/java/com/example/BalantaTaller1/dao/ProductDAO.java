package com.example.BalantaTaller1.dao;

import java.util.List;

import com.example.BalantaTaller1.model.prod.Product;

public interface ProductDAO {
	
	public void save(Product product);

	public void update(Product product);

	public List<Product> findAll();

	public Product findById(Integer id);

}
