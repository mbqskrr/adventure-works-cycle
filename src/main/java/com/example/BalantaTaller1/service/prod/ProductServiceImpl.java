package com.example.BalantaTaller1.service.prod;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BalantaTaller1.model.prod.Product;
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
		this.productmodelRepository = productmodelRepository;
		this.productRepository = productRepository;
		this.unitmeasureRepository = unitmeasureRepository;
		this.productsubcategoryRepository = productsubcategoryRepository;
	}

	@Override
	public Product save(Product p) {
		Integer size = Integer.parseInt(p.getSize());
		Integer weight = p.getWeight().intValueExact();
		if(p.getProductnumber().equals(null) || !p.getSellenddate().after(p.getSellstartdate()) || size < 0
				|| weight < 0 || unitmeasureRepository.findById(p.getUnitmeasure1().getUnitmeasurecode()).isEmpty() 
				|| unitmeasureRepository.findById(p.getUnitmeasure2().getUnitmeasurecode()).isEmpty()
				|| productsubcategoryRepository.findById(p.getProductsubcategory().getProductsubcategoryid()).isEmpty()
				|| productmodelRepository.findById(p.getProductmodel().getProductmodelid()).isEmpty()) {
			throw new RuntimeException();
		}
		p.setUnitmeasure1(unitmeasureRepository.getById(p.getUnitmeasure1().getUnitmeasurecode()));
		p.setUnitmeasure2(unitmeasureRepository.getById(p.getUnitmeasure2().getUnitmeasurecode()));
		p.setProductsubcategory(productsubcategoryRepository.getById(p.getProductsubcategory().getProductsubcategoryid()));
		p.setProductmodel(productmodelRepository.getById(p.getProductmodel().getProductmodelid()));
		return productRepository.save(p);
	}

	@Override
	public Product edit(Product p) {
		Integer size = Integer.parseInt(p.getSize());
		Integer weight = p.getWeight().intValueExact();
		if(p.getProductnumber().equals(null) || !p.getSellenddate().after(p.getSellstartdate()) || size < 0
				|| weight < 0 || unitmeasureRepository.findById(p.getUnitmeasure1().getUnitmeasurecode()).isEmpty() 
				|| unitmeasureRepository.findById(p.getUnitmeasure2().getUnitmeasurecode()).isEmpty()
				|| productsubcategoryRepository.findById(p.getProductsubcategory().getProductsubcategoryid()).isEmpty()
				|| productmodelRepository.findById(p.getProductmodel().getProductmodelid()).isEmpty()) {
			throw new RuntimeException();
		}
		Product tempP = new Product();
		tempP.setBillofmaterials1(p.getBillofmaterials1());
		tempP.setBillofmaterials2(p.getBillofmaterials2());
		tempP.setColor(p.getColor());
		tempP.setDaystomanufacture(p.getDaystomanufacture());
		tempP.setDiscontinueddate(p.getDiscontinueddate());
		tempP.setFinishedgoodsflag(p.getFinishedgoodsflag());
		tempP.setListprice(p.getListprice());
		tempP.setMakeflag(p.getMakeflag());
		tempP.setModifieddate(p.getModifieddate());
		tempP.setName(p.getName());
		tempP.setProductcosthistories(p.getProductcosthistories());
		tempP.setProductdocuments(p.getProductdocuments());
		tempP.setProductinventories(p.getProductinventories());
		tempP.setProductline(p.getProductline());
		tempP.setProductnumber(p.getProductnumber());
		tempP.setWeight(p.getWeight());
		tempP.setStyle(p.getStyle());
		tempP.setSize(p.getSize());
		tempP.setSellstartdate(p.getSellstartdate());
		tempP.setSellenddate(p.getSellenddate());
		p.setUnitmeasure1(unitmeasureRepository.getById(p.getUnitmeasure1().getUnitmeasurecode()));
		p.setUnitmeasure2(unitmeasureRepository.getById(p.getUnitmeasure2().getUnitmeasurecode()));
		p.setProductsubcategory(productsubcategoryRepository.getById(p.getProductsubcategory().getProductsubcategoryid()));
		p.setProductmodel(productmodelRepository.getById(p.getProductmodel().getProductmodelid()));
		return productRepository.save(tempP);
	}

	
}
