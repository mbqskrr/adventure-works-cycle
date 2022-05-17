package com.example.BalantaTaller1.dao;

import java.util.List;

import com.example.BalantaTaller1.model.prod.Scrapreason;

public interface ScrapreasonDAO {
	
	public void save();
	public List<Scrapreason> findAll();

}
