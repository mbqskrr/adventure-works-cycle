package com.example.BalantaTaller1.service.prod;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.dao.ProductcategoryDAOImpl;
import com.example.BalantaTaller1.model.prod.Productcategory;

@Service
@Transactional
public class ProductcategoryServiceImplDAO implements ProductcategoryService{
	
	@Autowired
	private ProductcategoryDAOImpl productcategoryDAO;

	@Override
	public Productcategory save(Productcategory pc) {

		constraints(pc);
		
		productcategoryDAO.save(pc);

		return pc;
	}

	@Override
	public Productcategory edit(Productcategory pc) {
		Productcategory temp = null;

		Productcategory pcid = productcategoryDAO.findById(pc.getProductcategoryid());

		if (!pcid.equals(null)) {
			constraints(pc);

			//temp = save(pc);
			productcategoryDAO.update(pc);
		}

		return pc;
	}

	@Override
	public Iterable<Productcategory> findAll() {
		return productcategoryDAO.findAll();
	}

	@Override
	public Optional<Productcategory> findById(Integer id) {
		return Optional.of(productcategoryDAO.findById(id));
	}
	
	private void constraints(Productcategory pc) {
		if (pc.getName().length() < 3 || pc.getName() == null) {
			throw new RuntimeException("El nombre de la categoria no tiene al menos 3 carácteres");

		}

	}

}
