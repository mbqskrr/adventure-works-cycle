package com.example.BalantaTaller1.service.prod;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.repository.prod.ProductcategoryRepository;
//import com.sun.istack.NotNull;

@Service
public class ProductcategoryServiceImpl implements ProductcategoryService {

	private ProductcategoryRepository productcategoryRepository;

	@Autowired
	public ProductcategoryServiceImpl(ProductcategoryRepository productcategoryRepository) {
		this.productcategoryRepository = productcategoryRepository;
	}

	@Override
	@Transactional
	public Productcategory save(Productcategory pc) {
		//Optional<Productcategory> tempPc = null;
		
		//validateConstrains(pc);

		if (pc.getName().length() <= 3 || pc.getName().equals(null)) {

			throw new RuntimeException("Nombre de categoria no valido");
		}/*else {
			tempPc = Optional.of(productcategoryRepository.save(pc));
		}*/
		return productcategoryRepository.save(pc);
	}

	@Override
	@Transactional
	public Productcategory edit(Productcategory pc) {
		if (pc.getName().length() <= 3 || pc.getName().equals(null)) {
			throw new RuntimeException();

		}
		Productcategory tempPc = productcategoryRepository.getById(pc.getProductcategoryid());
		tempPc.setModifieddate(pc.getModifieddate());
		tempPc.setName(pc.getName());
		tempPc.setRowguid(pc.getRowguid());
		tempPc.setProductsubcategories(pc.getProductsubcategories());
		return productcategoryRepository.save(tempPc);

	}
	
	/*@NotNull
	public void validateConstrains(Optional<Productcategory> pc) {
		if (pc.get().getName().length() <= 3 ) {
			throw new RuntimeException("El nombre de la categoria no tiene al menos 3 carácteres");

		}
		//return null;
	}*/

}
