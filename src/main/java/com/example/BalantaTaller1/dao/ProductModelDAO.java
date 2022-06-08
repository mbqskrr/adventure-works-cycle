package com.example.BalantaTaller1.dao;

import java.util.List;

import com.example.BalantaTaller1.model.prod.Productmodel;

public interface ProductModelDAO {
	public void save(Productmodel pm);
	public List<Productmodel> findAll();
	Productmodel findById(Integer productmodelid);
}
