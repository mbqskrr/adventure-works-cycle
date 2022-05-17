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

import com.example.BalantaTaller1.model.prod.Product;
//import com.example.BalantaTaller1.model.prod.Productsubcategory;

@Repository
//@Transactional
@Scope("singleton")
public class ProductDAOImpl implements ProductDAO{
	
	//@PersistenceUnit
	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Product product) {
		//EntityManager em = entityManager.createEntityManager();

		//em.getTransaction().begin();

		entityManager.persist(product);
		//em.getTransaction().commit();

		//em.close();
	}

	@Override
	@Transactional
	public void update(Product product) {
		//EntityManager em = entityManager.createEntityManager();

		//em.getTransaction().begin();

		entityManager.merge(product);
		//em.getTransaction().commit();

		//em.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> findAll() {
		//EntityManager em = entityManager.createEntityManager();
		Query q = entityManager.createQuery("SELECT p FROM Product p");
        return q.getResultList();
	}

	@Override
	@Transactional
	public Product findById(Integer id) {
		//EntityManager em = entityManager.createEntityManager();
		return entityManager.find(Product.class, id);
	}

	//@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> findByProductsubcategoryId(Integer productsubcategoryid) {
		String jpql = "SELECT p FROM Product p WHERE p.productsubcategory.productsubcategoryid = '"+productsubcategoryid+"'";
		/*EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.productsubcategory.productsubcategoryid = "+productsubcategoryid);
		q.setParameter("productsubcategoryid", productsubcategoryid);
        return q.getResultList();*/
		return entityManager.createQuery(jpql, Product.class).getResultList();
	}

	//@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> findByProductmodel(Integer productmodelid) {
		//EntityManager em = entityManager.createEntityManager();
		//Query q = em.createQuery("SELECT p FROM Product p WHERE p.productmodel.productmodelid = "+productmodelid);
		//q.setParameter("productmodelid", productmodelid);
		String jpql = "SELECT p FROM Product p WHERE p.productmodel.productmodelid = '"+productmodelid+"'";
        return entityManager.createQuery(jpql, Product.class).getResultList();
	}

	//@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> findByUnitmeasureSizeCode(String unitmeasurecode) {
		//EntityManager em = entityManager.createEntityManager();
		//Query q = em.createQuery("SELECT p FROM Product p WHERE p.unitmeasure.unitmeasure1 = "+unitmeasurecode);
		//q.setParameter("unitmeasurecode", unitmeasurecode);
		String jpql = "SELECT p FROM Product p WHERE p.unitmeasure1.unitmeasurecode = '"+unitmeasurecode+"'";
        return entityManager.createQuery(jpql, Product.class).getResultList();
	}

	//@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> findByAtLeastTwoWorkorders() {
		//EntityManager em = entityManager.createEntityManager();
		//Query q = em.createQuery("SELECT p FROM Product p WHERE SIZE(p.workorders) > 1");
		String jpql = "SELECT p FROM Product p WHERE SIZE(p.workorders) > 1";
        return entityManager.createQuery(jpql, Product.class).getResultList();
	}

}
