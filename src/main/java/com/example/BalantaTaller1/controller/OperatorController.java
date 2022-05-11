package com.example.BalantaTaller1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BalantaTaller1.model.prod.Productcategory;
//import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productmodel;
import com.example.BalantaTaller1.model.prod.Unitmeasure;
import com.example.BalantaTaller1.model.validation.ProductcategoryValidation;
import com.example.BalantaTaller1.service.prod.ProductServiceImpl;
//import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductsubcategoryServiceImpl;

@Controller
public class OperatorController {

	private ProductsubcategoryServiceImpl productsubcategoryService;
	private ProductServiceImpl productService;
	
	@Autowired
	public OperatorController(ProductsubcategoryServiceImpl productsubcategoryService,ProductServiceImpl productService) {
		this.productsubcategoryService = productsubcategoryService;
		this.productService = productService;
	}
	
	@GetMapping("/productmodel")
	public String productmodel(Model model) {
		model.addAttribute("productmodel", new Productmodel());
		return "operator/productmodel";
	}
	
	@PostMapping("/productmodel")
	public String saveProductModel(@ModelAttribute  Productmodel productmodel, 
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) {
			return "redirect:/operator/operator.html";
		}
		if (bindingResult.hasErrors()) {
			return "/operator/productmodel";
		} else {
			productService.saveProductmodel(productmodel);
			return "redirect:/operator/operator.html";
		}

	}
	
	@GetMapping("/unitmeasure")
	public String unitmeasure(Model model) {
		model.addAttribute("unitmeasure", new Unitmeasure());
		return "operator/unitmeasure";
	}
	
	@PostMapping("/unitmeasure")
	public String saveProductcategory(@ModelAttribute  Unitmeasure unitmeasure, 
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) {
			return "redirect:/operator/operator.html";
		}
		if (bindingResult.hasErrors()) {
			return "/operator/unitmeasure";
		} else {
			productService.saveUnitmeasure(unitmeasure);
			return "redirect:/operator/operator.html";
		}

	}
	
	@GetMapping("/product")
	public String products(Model model) {
		model.addAttribute("products", productService.findAll());
		return "operator/product";
	}
	
	
}
