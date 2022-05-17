package com.example.BalantaTaller1.dao;

import java.util.List;

import com.example.BalantaTaller1.model.prod.Scrapreason;

public interface ScrapreasonDAO {
	
	public void save(Scrapreason sr);
	public List<Scrapreason> findAll();

}
