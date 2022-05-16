package com.example.BalantaTaller1.service.prod;

import java.util.List;
import java.util.Optional;



//import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.model.prod.Product;
//import com.example.BalantaTaller1.model.prod.Productcategory;
//import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Unitmeasure;
import com.example.BalantaTaller1.repository.prod.ProductRepository;
import com.example.BalantaTaller1.repository.prod.ProductmodelRepository;
import com.example.BalantaTaller1.repository.prod.ProductsubcategoryRepository;
import com.example.BalantaTaller1.repository.prod.UnitmeasureRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	private UnitmeasureRepository unitmeasureRepository;
	private ProductRepository productRepository;
	private ProductmodelRepository productmodelRepository;
	private ProductsubcategoryRepository productsubcategoryRepository;
	
	@Autowired
	public ProductServiceImpl(UnitmeasureRepository unitmeasureRepository, ProductRepository productRepository, ProductmodelRepository productmodelRepository, ProductsubcategoryRepository productsubcategoryRepository) {
		this.unitmeasureRepository = unitmeasureRepository;
		this.productRepository = productRepository;
		this.productmodelRepository = productmodelRepository;
		this.productsubcategoryRepository = productsubcategoryRepository;
	}

	@Override
	@Transactional
	public Product save(Product p) {
		Product temp = null;
		
		//constraints(p);
		
		Optional<Unitmeasure> opUm = this.unitmeasureRepository.findById(p.getUnitmeasure1().getUnitmeasurecode());
		Optional<Unitmeasure> opUm1 = this.unitmeasureRepository.findById(p.getUnitmeasure2().getUnitmeasurecode());
		Optional<Productsubcategory> opPsc = this.productsubcategoryRepository.findById(p.getProductsubcategory().getProductsubcategoryid());
		Optional<Productmodel> opPm = this.productmodelRepository.findById(p.getProductmodel().getProductmodelid());
		if(opPm.isPresent() && opUm.isPresent() && opUm1.isPresent() && opPsc.isPresent()) {
			p.setUnitmeasure1(opUm.get());
			p.setUnitmeasure2(opUm1.get());
			p.setProductsubcategory(opPsc.get());
			p.setProductmodel(opPm.get());
			temp = productRepository.save(p);
		}
		return temp;
	}

	@Override
	@Transactional
	public Product edit(Product p) {
		Product temp = null;
		Optional<Product> optional = this.productRepository.findById(p.getProductid());
		if(optional.isPresent()) {
			//constraints(p);
			temp = save(p);
		}
		
		return productRepository.save(temp);
	}

	/*@NotNull
	private void constraints(Product p) {
		Integer size = Integer.parseInt(p.getSize());
		Integer weight = p.getWeight().intValueExact();
		if(p.getProductnumber().equals(null)) {
			throw new RuntimeException("Numero de producto no valido");
		}
		if ( !p.getSellenddate().after(p.getSellstartdate())) {
			throw new RuntimeException("Error en las fechas");
		}
		if (size < 0) {
			throw new RuntimeException("Tamaño menor a 0");
		}
		if (weight < 0) {
			throw new RuntimeException("Peso menor a 0");
		}
	}*/

	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	public Iterable<Product> findByProductsubcategory(Integer id) {
		Productsubcategory psc = productsubcategoryRepository.findById(id).get();
		List<Product> pscPL = psc.getProducts();
		
		Iterable<Product> pscPI = pscPL;
		return pscPI;
	}
	
	public Iterable<Productmodel> findAllProductModel() {
		return productmodelRepository.findAll();
	}
	
	public Iterable<Unitmeasure> findAllUnitMeasure() {
		return unitmeasureRepository.findAll();
	}

	public Productmodel saveProductmodel(Productmodel productmodel) {
		return productmodelRepository.save(productmodel);
		// TODO Auto-generated method stub
		
	}

	public Unitmeasure saveUnitmeasure(Unitmeasure unitmeasure) {
		return unitmeasureRepository.save(unitmeasure);
		// TODO Auto-generated method stub
		
	}

	public Optional<Product> findById(Integer id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id);
	}
	
	
	
}
