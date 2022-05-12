package com.example.BalantaTaller1.controller;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.validation.ProductcategoryValidation;
import com.example.BalantaTaller1.model.validation.ProductsubcategoryValidation;
import com.example.BalantaTaller1.service.prod.ProductServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductsubcategoryServiceImpl;


@Controller
public class AdminController {
	
	private ProductcategoryServiceImpl productcategoryService;
	private ProductsubcategoryServiceImpl productsubcategoryService;
	private ProductServiceImpl productService;
	
	@Autowired
	public AdminController(ProductcategoryServiceImpl productcategoryService, ProductsubcategoryServiceImpl productsubcategoryService,
			ProductServiceImpl productService) {
		this.productcategoryService = productcategoryService;
		this.productsubcategoryService = productsubcategoryService;
		this.productService = productService;
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
	public String saveProductcategory(@Validated(ProductcategoryValidation.class) @ModelAttribute  Productcategory productcategory, 
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) {
			return "redirect:/productcategory/";
		}
		if (bindingResult.hasErrors()) {
			//model.addAttribute("productcategory", productcategory);
			return "/admin/addProductcategory";
		} else {
			productcategoryService.save(productcategory);
			return "redirect:/productcategory/";
		}

	}

	@GetMapping("/Productsubcategory/{id}")
    public String queryProductsubcategoriesByProductcategory(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("prodcutsubcategories", productsubcategoryService.findByProductcategory(id));
        return "admin/productsubcategoriesByProductcategory";
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
	public String saveProductsubcategory(@Validated(ProductsubcategoryValidation.class) @ModelAttribute Productsubcategory productsubcategory, BindingResult bindingResult, 
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) {
			return "redirect:/productsubcategory/";
		}else {
			if(bindingResult.hasErrors()) {
				model.addAttribute("productsubcategory", productsubcategory);
				model.addAttribute("productcategories", productcategoryService.findAll());
				return "/admin/addProductsubcategory";
			}else {
				productsubcategoryService.save(productsubcategory);
				return "redirect:/productsubcategory/";
			}	
		}
	}
	
	@GetMapping("/Product/{id}")
    public String queryProductsByProductsubcategory(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("products", productService.findByProductsubcategory(id));
        return "admin/productsByProductsubcategory";
    }

}
