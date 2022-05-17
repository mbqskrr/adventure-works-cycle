package com.example.BalantaTaller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Workorder;

@Repository
//@Transactional
@Scope("singleton")
public class WorkorderDAOImpl implements WorkorderDAO{
	
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Workorder workorder) {
		entityManager.persist(workorder);
	}

	@Override
	@Transactional
	public void update(Workorder workorder) {
		entityManager.merge(workorder);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Workorder> findAll() {
		Query q = entityManager.createQuery("SELECT w FROM Workorder w");
        return q.getResultList();
	}

	@Override
	@Transactional
	public Workorder findById(Integer workorderid) {
		return entityManager.find(Workorder.class, workorderid);
	}

	@Override
	@Transactional
	public List<Workorder> findByScrapreasonId(Integer scrapreasonid) {
		String jpql = "SELECT w FROM Workorder w WHERE w.scrapreason.scrapreasonid = '"+scrapreasonid+"'";
		return entityManager.createQuery(jpql, Workorder.class).getResultList();
	}

	@Override
	@Transactional
	public List<Workorder> findByOrderqty(Integer orderqty) {
		String jpql = "SELECT w FROM Workorder w WHERE w.orderqty = '"+orderqty+"'";
		return entityManager.createQuery(jpql, Workorder.class).getResultList();
	}

}
