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
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.validation.ProductcategoryValidation;
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
	
	@GetMapping("/productcategory")
	public String productcategories(Model model) {
		model.addAttribute("productcategories", productcategoryService.findAll());
		return "admin/productcategory";
	}
	
	@GetMapping("/productcategory/add")
	public String addProduct(Model model) {
		model.addAttribute("productcategory", new Productcategory());
		return "admin/addProductcategory";
	}
	
	@PostMapping("/productcategory/add")
	public String saveProductcategory(@Validated(ProductcategoryValidation.class) @ModelAttribute Productcategory productcategory, 
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) {
			return "redirect:/productcategory/";
		}else {
			if(bindingResult.hasErrors()) {
				//model.addAttribute("productcategory", new Productcategory());
				return "/admin/addProductcategory";
			}
			productcategoryService.save(productcategory);
			return "redirect:/productcategory/";
		}
		
		
	}
	
	@GetMapping("/productsubcategory")
	public String productsubcategories(Model model) {
		model.addAttribute("productsubcategories", productsubcategoryService.findAll());
		return "admin/productsubcategory";
	}
	
	@GetMapping("/productsubcategory/add")
	public String addProductsubcategory(Model model) {
		model.addAttribute("productsubcategory", new Productsubcategory());
		model.addAttribute("productcategories", productcategoryService.findAll());
		return "admin/addProductsubcategory";
		
	}
	
	@PostMapping("/productsubcategory/add")
	public String saveProductsubcategory(@Validated Productsubcategory productsubcategory, BindingResult bindingResult, 
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) {
			return "redirect:/productsubcategory";
		}else {
			if(bindingResult.hasErrors()) {
				model.addAttribute("productsubcategory", new Productsubcategory());
				model.addAttribute("productcategories", productcategoryService.findAll());
				return "admin/addProductsubcategory";
			}
			
			productsubcategoryService.save(productsubcategory);
			return "redirect:/productsubcategory/add";
		}
		
		
	}

}
