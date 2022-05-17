package com.example.BalantaTaller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.example.BalantaTaller1.model.prod.Unitmeasure;

@Repository
//@Transactional
@Scope("singleton")
public class UnitmeasureDAOImpl implements UnitmeasureDAO{
	
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Unitmeasure> findAll() {
		Query q = entityManager.createQuery("SELECT u FROM Unitmeasure u");
		return q.getResultList();
	}

	@Override
	public void save(Unitmeasure um) {
		entityManager.persist(um);
	}

}
