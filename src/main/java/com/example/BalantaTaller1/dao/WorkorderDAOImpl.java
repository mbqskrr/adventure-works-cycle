package com.example.BalantaTaller1.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.model.prod.Workorder;

@Repository
@Transactional
public class WorkorderDAOImpl implements WorkorderDAO{

	@Override
	public void save(Workorder workorder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Workorder workorder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Workorder> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Workorder findById(Integer workorderid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Workorder> findByScrapreasonId(Integer scrapreasonid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Workorder> findByOrderqty(Integer orderqtyorderqty) {
		// TODO Auto-generated method stub
		return null;
	}

}
