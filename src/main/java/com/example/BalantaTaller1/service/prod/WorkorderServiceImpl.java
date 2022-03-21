package com.example.BalantaTaller1.service.prod;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BalantaTaller1.model.prod.Workorder;
import com.example.BalantaTaller1.repository.prod.ProductRepository;
import com.example.BalantaTaller1.repository.prod.ScrapreasonRepository;
import com.example.BalantaTaller1.repository.prod.WorkorderRepository;

public class WorkorderServiceImpl implements WorkorderSerivice{
	
	private WorkorderRepository workorderRepository;
	private ScrapreasonRepository scrapreasonRepository;
	private ProductRepository productRepository;
	
	@Autowired
	public WorkorderServiceImpl(WorkorderRepository workorderRepository, ScrapreasonRepository scrapreasonRepository, ProductRepository productRepository) {
		this.productRepository = productRepository;
		this.scrapreasonRepository = scrapreasonRepository;
		this.workorderRepository = workorderRepository;
	}

	@Override
	public Workorder save(Workorder wo) {
		if(wo.getOrderqty()>0 || wo.getScrappedqty()>0 || wo.getEnddate().after(wo.getStartdate())) {
			throw new RuntimeException();
		}
		wo.setProduct(productRepository.getById(wo.getProduct().getProductid()));
		wo.setScrapreason(scrapreasonRepository.getById(wo.getScrapreason().getScrapreasonid()));
		return workorderRepository.save(wo);
	}

	@Override
	public Workorder edit(Workorder wo) {
		if(wo.getOrderqty()>0 || wo.getScrappedqty()>0 || wo.getEnddate().after(wo.getStartdate())) {
			throw new RuntimeException();
		}
		return null;
	}

}
