package com.example.BalantaTaller1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductsubcategoryServiceImpl;


@Controller
public class AdminController {
	
	private ProductcategoryServiceImpl productcategoryService;
	private ProductsubcategoryServiceImpl productsubcategoryService;
	
	@Autowired
	public AdminController(ProductcategoryServiceImpl productcategoryService, ProductsubcategoryServiceImpl productsubcategoryService) {
		this.productcategoryService = productcategoryService;
		this.productsubcategoryService = productsubcategoryService;
	}
	
	@GetMapping("/productcategory/add")
	public String addProductcategory(Model model) {
		model.addAttribute("productcategory", new Productcategory());
		return "admin/addProductcategory";
	}

}
