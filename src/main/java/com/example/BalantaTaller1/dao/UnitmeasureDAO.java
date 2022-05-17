package com.example.BalantaTaller1.dao;

import java.util.List;

import com.example.BalantaTaller1.model.prod.Unitmeasure;

public interface UnitmeasureDAO {
	
	public void save(Unitmeasure um);
	public List<Unitmeasure> findAll();
}
