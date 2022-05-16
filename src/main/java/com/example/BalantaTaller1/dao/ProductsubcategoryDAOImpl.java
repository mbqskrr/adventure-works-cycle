package com.example.BalantaTaller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.example.BalantaTaller1.model.prod.Productsubcategory;

@Repository
@Transactional
public class ProductsubcategoryDAOImpl implements ProductsubcategoryDAO{
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;


	@Override
	@Transactional
	public void save(Productsubcategory psc) {
		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		em.persist(psc);
		em.getTransaction().commit();

		em.close();
	}

	@Override
	@Transactional
	public void update(Productsubcategory psc) {
		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		em.merge(psc);
		em.getTransaction().commit();

		em.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productsubcategory> findAll() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Productsubcategory p");
        return q.getResultList();
	}

	@Override
	@Transactional
	public Productsubcategory findById(Integer productsubcategoryid) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.find(Productsubcategory.class, productsubcategoryid);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productsubcategory> findByProductcategoryId(Integer productcategoryid) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Productsubcategory p WHERE p.productcategory.productcategoryid = "+productcategoryid);
		q.setParameter("productcategoryid", productcategoryid);
        return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productsubcategory> findByProductcategoryName(String name) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Productsubcategory p WHERE p.productcategory.name = "+name);
		q.setParameter("name", name);
        return q.getResultList();
	}

}
