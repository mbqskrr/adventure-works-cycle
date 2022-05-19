package com.example.BalantaTaller1.service.prod;

import java.util.Optional;

import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.prod.Unitmeasure;

public interface ProductService {
	
	public Product save(Product p);
	public Product edit(Product p);
	public Iterable<Product> findAll();
	public Optional<Product> findById(Integer id);
	public Unitmeasure saveUnitmeasure(Unitmeasure unitmeasure);
	public Productmodel saveProductmodel(Productmodel productmodel);
	public Iterable<Unitmeasure> findAllUnitMeasure();
	public Iterable<Productmodel> findAllProductModel();
	public Iterable<Product> findByProductsubcategory(Integer id);

}
