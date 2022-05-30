package com.example.BalantaTaller1.service.prod;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.dao.ProductcategoryDAOImpl;
import com.example.BalantaTaller1.dao.ProductsubcategoryDAOImpl;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
import com.example.BalantaTaller1.repository.prod.ProductsubcategoryRepository;

@Service
@Transactional
public class ProductsubcategoryServiceImplDAO implements ProductsubcategoryService{
	
	private ProductsubcategoryDAOImpl productsubcategoryDAO;
	private ProductcategoryDAOImpl productcategoryDAO;
	
	@Autowired
	public ProductsubcategoryServiceImplDAO(ProductsubcategoryDAOImpl productsubcategoryDAO,
			ProductcategoryDAOImpl productcategoryDAO) {
		this.productcategoryDAO = productcategoryDAO;
		this.productsubcategoryDAO = productsubcategoryDAO;
	}

	@Override
	public Productsubcategory save(Productsubcategory psc) {
		constraints(psc);
		productsubcategoryDAO.save(psc);
		return psc;
	}

	@Override
	public Productsubcategory edit(Productsubcategory psc) {
		
		Productcategory pcid = productcategoryDAO.findById(psc.getProductcategory().getProductcategoryid());
		Productsubcategory pscid = productsubcategoryDAO.findById(psc.getProductsubcategoryid());

		if (!pscid.equals(null) && !pcid.equals(null)) {
			constraints(psc);
			productsubcategoryDAO.update(psc);
		}

		return psc;
	}

	@Override
	public Iterable<Productsubcategory> findAll() {
		return productsubcategoryDAO.findAll();
	}

	@Override
	public Iterable<Productsubcategory> findByProductcategory(Integer id) {
		return productsubcategoryDAO.findByProductcategoryId(id);
	}

	@Override
	public Optional<Productsubcategory> findById(Integer id) {
		return Optional.of(productsubcategoryDAO.findById(id));
	}
	
	private void constraints(Productsubcategory psc) {
		if (psc.getName().length() < 5 || psc.getName() == null) {
			throw new RuntimeException("Nombre de subcategoria no valido");
		}
		if (productcategoryDAO.findById(psc.getProductcategory().getProductcategoryid()).equals(null)) {
			throw new RuntimeException("Categoria asociada no existe");
		}
	}

}
