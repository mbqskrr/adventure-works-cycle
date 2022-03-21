package com.example.BalantaTaller1.service.prod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;

public class ProductcategoryServiceImpl implements ProductcategoryService {

	private ProductcategoryRepository productcategoryRepository;

	@Autowired
	public ProductcategoryServiceImpl(ProductcategoryRepository productcategoryRepository) {
		this.productcategoryRepository = productcategoryRepository;
	}

	@Override
	@Transactional
	public Productcategory save(Productcategory pc) {

		if (pc.getName().length() >= 3 || !pc.getName().equals(null)) {

			throw new RuntimeException();
		}
		return productcategoryRepository.save(pc);
	}

	@Override
	@Transactional
	public Productcategory edit(Productcategory pc) {
		Productcategory tempPc = productcategoryRepository.getById(pc.getProductcategoryid());

		if (pc.getName().length() >= 3 || !pc.getName().equals(null)) {
			throw new RuntimeException();

		}

		tempPc.setModifieddate(pc.getModifieddate());
		tempPc.setName(pc.getName());
		tempPc.setRowguid(pc.getRowguid());
		tempPc.setProductsubcategories(pc.getProductsubcategories());
		return productcategoryRepository.save(tempPc);

	}

}
