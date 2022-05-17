package com.example.BalantaTaller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.model.prod.Productmodel;

@Repository
//@Transactional
@Scope("singleton")
public class ProductmodelDAOImpl implements ProductModelDAO{
	
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Productmodel pm) {
		// TODO Auto-generated method stub
		entityManager.persist(pm);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productmodel> findAll() {
		Query q = entityManager.createQuery("SELECT p FROM Productmodel p");
		return q.getResultList();
	}

}
