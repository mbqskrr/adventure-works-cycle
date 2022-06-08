package com.example.BalantaTaller1.dao;

import java.util.List;

import com.example.BalantaTaller1.model.prod.Workorder;

public interface WorkorderDAO {
	
	public void save(Workorder workorder);

	public void update(Workorder workorder);

	public List<Workorder> findAll();

	public Workorder findById(Integer workorderid);
	
	public List<Workorder> findByScrapreasonId(Integer scrapreasonid);
	
	public List<Workorder> findByOrderqty(Integer orderqtyorderqty);

	public List<Workorder> findByProductId(Integer productid);

}
