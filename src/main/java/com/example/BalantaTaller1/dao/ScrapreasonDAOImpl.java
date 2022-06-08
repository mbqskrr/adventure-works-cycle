package com.example.BalantaTaller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.model.prod.Scrapreason;

@Repository
//@Transactional
@Scope("singleton")
public class ScrapreasonDAOImpl implements ScrapreasonDAO{
	
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Scrapreason sr) {
		entityManager.persist(sr);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Scrapreason> findAll() {
		Query q = entityManager.createQuery("SELECT s FROM Scrapreason s");
		return q.getResultList();
	}

	@Transactional
	@Override
    public Scrapreason findById(Integer scrapreasonid) {
        return entityManager.find(Scrapreason.class, scrapreasonid);
    }

	

}
