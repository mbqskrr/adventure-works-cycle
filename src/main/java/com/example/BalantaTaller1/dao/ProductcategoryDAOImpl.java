package com.example.BalantaTaller1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.model.prod.Productcategory;

@Repository
@Transactional
public class ProductcategoryDAOImpl implements ProductcategoryDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional
	public void save(Productcategory pc) {

		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		em.persist(pc);
		em.getTransaction().commit();

		em.close();
	}

	@Override
	@Transactional
	public void update(Productcategory pc) {
		
		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		em.merge(pc);
		em.getTransaction().commit();

		em.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Productcategory> findAll() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Productcategory p");
        return q.getResultList();
	}

	@Override
	@Transactional
	public Productcategory findById(Integer productcategoryid) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.find(Productcategory.class, productcategoryid);
	}

}
