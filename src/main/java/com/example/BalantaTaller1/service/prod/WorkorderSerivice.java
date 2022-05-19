package com.example.BalantaTaller1.service.prod;

import java.util.Optional;

import com.example.BalantaTaller1.model.prod.Scrapreason;
import com.example.BalantaTaller1.model.prod.Workorder;

public interface WorkorderSerivice {
	
	public Workorder save(Workorder wo);
	public Workorder edit(Workorder wo);
	public Scrapreason saveScrapreason(Scrapreason sr);
	public Optional<Workorder> findById(Integer id);
	public Iterable<Workorder> findByProduct(Integer id);
	public Iterable<Scrapreason> findAllScrapreason();
	public Iterable<Workorder> findAll();

}
