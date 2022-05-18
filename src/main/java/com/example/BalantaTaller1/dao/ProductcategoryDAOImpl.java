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

import com.example.BalantaTaller1.model.prod.Productcategory;

@Repository
//@Transactional
@Scope("singleton")
public class ProductcategoryDAOImpl implements ProductcategoryDAO {

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;
	

	@Override
	@Transactional
	public void save(Productcategory pc) {

		/*EntityManager em = entityManager.createEntityManager();

		em.getTransaction().begin();

		em.persist(pc);
		em.getTransaction().commit();

		em.close();*/
		entityManager.persist(pc);
	}

	@Override
	@Transactional
	public void update(Productcategory pc) {
		
		/*EntityManager em = entityManager.createEntityManager();

		em.getTransaction().begin();

		em.merge(pc);
		em.getTransaction().commit();

		em.close();*/
		entityManager.merge(pc);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productcategory> findAll() {
		/*EntityManager em = entityManager.createEntityManager();*/
		Query q = entityManager.createQuery("SELECT p FROM Productcategory p");
        return q.getResultList();
        
        /*String jpql = "Select p from Productcategory p";
		return 	entityManager.createQuery(jpql, Productcategory.class).getResultList();*/
	}

	@Override
	@Transactional
	public Productcategory findById(Integer productcategoryid) {
		/*EntityManager em = entityManager.createEntityManager();
		return em.find(Productcategory.class, productcategoryid);*/
		return entityManager.find(Productcategory.class, productcategoryid);
	}
	
	

}
