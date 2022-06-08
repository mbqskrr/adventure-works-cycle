package com.example.BalantaTaller1.service.prod;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.dao.ProductDAOImpl;
import com.example.BalantaTaller1.dao.ProductmodelDAOImpl;
import com.example.BalantaTaller1.dao.ProductsubcategoryDAOImpl;
import com.example.BalantaTaller1.dao.UnitmeasureDAOImpl;
import com.example.BalantaTaller1.model.prod.Product;
import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Unitmeasure;

@Service
@Transactional
public class ProductServiceImplDAO implements ProductService {

	private UnitmeasureDAOImpl unitmeasureDAO;
	private ProductDAOImpl productDAO;
	private ProductmodelDAOImpl productmodelDAO;
	private ProductsubcategoryDAOImpl productsubcategoryDAO;

	@Autowired
	public ProductServiceImplDAO(UnitmeasureDAOImpl unitmeasureDAO, ProductDAOImpl productDAO,
			ProductmodelDAOImpl productmodelDAO, ProductsubcategoryDAOImpl productsubcategoryDAO) {
		this.unitmeasureDAO = unitmeasureDAO;
		this.productDAO = productDAO;
		this.productmodelDAO = productmodelDAO;
		this.productsubcategoryDAO = productsubcategoryDAO;
	}

	@Override
	@Transactional
	public Product save(Product p) {

		Unitmeasure opUm = this.unitmeasureDAO.findById(p.getUnitmeasure1().getUnitmeasurecode());
		Unitmeasure opUm1 = this.unitmeasureDAO.findById(p.getUnitmeasure2().getUnitmeasurecode());
		Productsubcategory opPsc = this.productsubcategoryDAO.findById(p.getProductsubcategory().getProductsubcategoryid());
		Productmodel opPm = this.productmodelDAO.findById(p.getProductmodel().getProductmodelid());
		if (!opPm.equals(null) && !opUm.equals(null) && !opUm1.equals(null) && !opPsc.equals(null)) {
			constraints(p);
			p.setUnitmeasure1(opUm);
			p.setUnitmeasure2(opUm1);
			p.setProductsubcategory(opPsc);
			p.setProductmodel(opPm);
			productDAO.save(p);
		}

		return p;
	}

	@Override
	public Product edit(Product p) {

		Product optional = this.productDAO.findById(p.getProductid());
		if(!optional.equals(null)) {
			constraints(p);
			productDAO.update(p);
		}
		
		return p;
	}

	@Override
	public Iterable<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return Optional.of(productDAO.findById(id));
	}

	@Override
	public Unitmeasure saveUnitmeasure(Unitmeasure unitmeasure) {
		unitmeasureDAO.save(unitmeasure);
		return unitmeasure;
	}

	@Override
	public Productmodel saveProductmodel(Productmodel productmodel) {
		productmodelDAO.save(productmodel);
		return productmodel;
	}

	@Override
	public Iterable<Unitmeasure> findAllUnitMeasure() {
		return unitmeasureDAO.findAll();
	}

	@Override
	public Iterable<Productmodel> findAllProductModel() {
		return productmodelDAO.findAll();
	}

	@Override
	public Iterable<Product> findByProductsubcategory(Integer id) {
		return productDAO.findByProductsubcategoryId(id);
	}

	private void constraints(Product p) {
		Integer size = Integer.parseInt(p.getSize());
		Integer weight = p.getWeight().intValueExact();
		if (p.getProductnumber().equals(null)) {
			throw new RuntimeException("Numero de producto no valido");
		}
		if (!p.getSellenddate().isAfter(p.getSellstartdate())) {
			throw new RuntimeException("Error en las fechas");
		}
		if (size < 0) {
			throw new RuntimeException("Tamaño menor a 0");
		}
		if (weight < 0) {
			throw new RuntimeException("Peso menor a 0");
		}
	}

}
