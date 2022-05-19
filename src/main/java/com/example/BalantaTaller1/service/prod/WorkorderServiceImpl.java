package com.example.BalantaTaller1.service.prod;

import java.util.List;
import java.util.Optional;

//import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.model.prod.Product;
//import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Scrapreason;
import com.example.BalantaTaller1.model.prod.Workorder;
import com.example.BalantaTaller1.repository.prod.ProductRepository;
import com.example.BalantaTaller1.repository.prod.ScrapreasonRepository;
import com.example.BalantaTaller1.repository.prod.WorkorderRepository;

@Service
public class WorkorderServiceImpl implements WorkorderSerivice {

	private WorkorderRepository workorderRepository;
	private ScrapreasonRepository scrapreasonRepository;
	private ProductRepository productRepository;

	@Autowired
	public WorkorderServiceImpl(WorkorderRepository workorderRepository, ScrapreasonRepository scrapreasonRepository,
			ProductRepository productRepository) {
		this.productRepository = productRepository;
		this.scrapreasonRepository = scrapreasonRepository;
		this.workorderRepository = workorderRepository;
	}

	@Override
	@Transactional
	public Workorder save(Workorder wo) {

		Workorder temp = null;
		constraints(wo);

		Optional<Product> op = this.productRepository.findById(wo.getProduct().getProductid());
		Optional<Scrapreason> op2 = this.scrapreasonRepository.findById(wo.getScrapreason().getScrapreasonid());
		if (op.isPresent() && op2.isPresent()) {
			wo.setProduct(op.get());
			wo.setScrapreason(op2.get());
			temp = workorderRepository.save(wo);
		}

		return temp;
	}

	@Override
	@Transactional
	public Workorder edit(Workorder wo) {
		Workorder temp = null;

		Optional<Workorder> optional = this.workorderRepository.findById(wo.getWorkorderid());
		if (optional.isPresent()) {
			constraints(wo);
			temp = save(wo);
		}

		return temp;
	}

	@Override
	@Transactional
	public Scrapreason saveScrapreason(Scrapreason sr) {
		return scrapreasonRepository.save(sr);
	}

	@Override
	public Iterable<Workorder> findAll() {
		return workorderRepository.findAll();
	}

	@Override
	public Iterable<Scrapreason> findAllScrapreason() {
		return scrapreasonRepository.findAll();
	}

	@Override
	public Iterable<Workorder> findByProduct(Integer id) {
		Product p = productRepository.findById(id).get();
		List<Workorder> woPL = p.getWorkorders();

		Iterable<Workorder> woPI = woPL;
		return woPI;
	}

	@Override
	public Optional<Workorder> findById(Integer id) {
		return workorderRepository.findById(id);
	}

	private void constraints(Workorder wo) {
		if (wo.getOrderqty() < 0 || wo.getScrappedqty() < 0) {
			throw new RuntimeException("Cantidad de orden menor a 0");
		}
		if (wo.getScrappedqty() < 0) {
			throw new RuntimeException("Cantidad de rechazo menor a 0");
		}
		if (!wo.getEnddate().isAfter(wo.getStartdate())) {
			throw new RuntimeException("Error en fecha inicial y fecha final");
		}
	}

}
