package com.example.BalantaTaller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.model.prod.Productsubcategory;

@Repository
//@Transactional
@Scope("singleton")
public class ProductsubcategoryDAOImpl implements ProductsubcategoryDAO {

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Productsubcategory psc) {
		/*
		 * EntityManager em = entityManagerFactory.createEntityManager();
		 * 
		 * em.getTransaction().begin();
		 * 
		 * em.persist(psc); em.getTransaction().commit();
		 * 
		 * em.close();
		 */
		entityManager.persist(psc);
	}

	@Override
	@Transactional
	public void update(Productsubcategory psc) {
		/*
		 * EntityManager em = entityManager.createEntityManager();
		 * 
		 * em.getTransaction().begin();
		 * 
		 * em.merge(psc); em.getTransaction().commit();
		 * 
		 * em.close();
		 */

		entityManager.merge(psc);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productsubcategory> findAll() {
		// EntityManager em = entityManager.createEntityManager();
		Query q = entityManager.createQuery("SELECT p FROM Productsubcategory p");
		return q.getResultList();
	}

	@Override
	@Transactional
	public Productsubcategory findById(Integer productsubcategoryid) {
		// EntityManager em = entityManager.createEntityManager();
		return entityManager.find(Productsubcategory.class, productsubcategoryid);
	}

	// @SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productsubcategory> findByProductcategoryId(Integer productcategoryid) {
		// EntityManager em = entityManager.createEntityManager();
		// Query q = entityManager.createQuery("SELECT p FROM Productsubcategory p WHERE
		// p.productcategory.productcategoryid = '"+productcategoryid+"'");
		// q.setParameter("productcategoryid", productcategoryid);
		// return q.getResultList();
		String jpql = "SELECT p FROM Productsubcategory p WHERE p.productcategory.productcategoryid = '"
				+ productcategoryid + "'";
		return entityManager.createQuery(jpql, Productsubcategory.class).getResultList();
	}

	//@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productsubcategory> findByProductcategoryName(String name) {
		// EntityManager em = entityManager.createEntityManager();
		/*
		 * Query q = entityManager
		 * .createQuery("SELECT p FROM Productsubcategory p WHERE p.productcategory.name = '"
		 * + name + "'"); q.setParameter("name", name); return q.getResultList();
		 */
		String jpql = "SELECT p FROM Productsubcategory p WHERE p.productcategory.name = '" + name + "'";
		return entityManager.createQuery(jpql, Productsubcategory.class).getResultList();
	}

}