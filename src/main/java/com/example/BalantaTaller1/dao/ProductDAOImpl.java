package com.example.BalantaTaller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Productsubcategory;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO{
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional
	public void save(Product product) {
		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		em.persist(product);
		em.getTransaction().commit();

		em.close();
	}

	@Override
	@Transactional
	public void update(Product product) {
		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		em.merge(product);
		em.getTransaction().commit();

		em.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> findAll() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Product p");
        return q.getResultList();
	}

	@Override
	@Transactional
	public Product findById(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.find(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productsubcategory> findByProductsubcategoryId(Integer productsubcategoryid) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.productsubcategory.productsubcategoryid = "+productsubcategoryid);
		q.setParameter("productsubcategoryid", productsubcategoryid);
        return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productsubcategory> findByProductmodel(Integer productmodelid) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.productmodel.productmodelid = "+productmodelid);
		q.setParameter("productmodelid", productmodelid);
        return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productsubcategory> findByUnitmeasureSizeCode(String unitmeasurecode) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.unitmeasure.unitmeasure1 = "+unitmeasurecode);
		q.setParameter("unitmeasurecode", unitmeasurecode);
        return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> findAllByAtLeastTwoWorkorders() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Product p WHERE SIZE(p.workorders) > 1");
        return q.getResultList();
	}

}
