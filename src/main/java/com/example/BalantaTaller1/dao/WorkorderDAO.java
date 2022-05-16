package com.example.BalantaTaller1.dao;

import java.util.List;

import com.example.BalantaTaller1.model.prod.Workorder;

public interface WorkorderDAO {
	
	public void save(Workorder product);

	public void update(Workorder product);

	public List<Workorder> findAll();

	public Workorder findById(Integer id);

}
