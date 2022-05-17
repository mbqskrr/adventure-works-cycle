package com.example.BalantaTaller1.controller;

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

import com.example.BalantaTaller1.model.prod.Product;
//import com.example.BalantaTaller1.model.prod.Productcategory;
//import com.example.BalantaTaller1.model.prod.Productcategory;
import com.example.BalantaTaller1.model.prod.Productmodel;
//import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Scrapreason;
//import com.example.BalantaTaller1.model.prod.Productsubcategory;
import com.example.BalantaTaller1.model.prod.Unitmeasure;
import com.example.BalantaTaller1.model.prod.Workorder;
import com.example.BalantaTaller1.model.validation.ProductValidation;
//import com.example.BalantaTaller1.model.validation.ProductcategoryValidation;
import com.example.BalantaTaller1.model.validation.WorkorderValidation;
//import com.example.BalantaTaller1.model.validation.ProductcategoryValidation;
//import com.example.BalantaTaller1.model.validation.ProductsubcategoryValidation;
import com.example.BalantaTaller1.service.prod.ProductServiceImpl;
//import com.example.BalantaTaller1.service.prod.ProductcategoryServiceImpl;
import com.example.BalantaTaller1.service.prod.ProductsubcategoryServiceImpl;
import com.example.BalantaTaller1.service.prod.WorkorderServiceImpl;

@Controller
public class OperatorController {

	private ProductsubcategoryServiceImpl productsubcategoryService;
	private ProductServiceImpl productService;
	private WorkorderServiceImpl workorderService;
	
	@Autowired
	public OperatorController(ProductsubcategoryServiceImpl productsubcategoryService,ProductServiceImpl productService,
			WorkorderServiceImpl workorderService) {
		this.productsubcategoryService = productsubcategoryService;
		this.productService = productService;
		this.workorderService = workorderService;
	}
	
	@GetMapping("/productmodel")
	public String productmodel(Model model) {
		model.addAttribute("productmodel", new Productmodel());
		return "operator/productmodel";
	}
	
	@PostMapping("/productmodel")
	public String saveProductmodel(@ModelAttribute  Productmodel productmodel, 
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
	public String saveUnitmeasure(@ModelAttribute  Unitmeasure unitmeasure, 
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
	
	@GetMapping("/scrapreason")
	public String scrapreason(Model model) {
		model.addAttribute("scrapreason", new Scrapreason());
		return "operator/scrapreason";
	}
	
	@PostMapping("/scrapreason")
	public String saveScrapreason(@ModelAttribute  Scrapreason scrapreason, 
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) {
			return "redirect:/operator/operator.html";
		}
		if (bindingResult.hasErrors()) {
			return "/operator/scrapreason";
		} else {
			workorderService.saveScrapreason(scrapreason);
			return "redirect:/operator/operator.html";
		}

	}
	
	@GetMapping("/product")
	public String products(Model model) {
		model.addAttribute("products", productService.findAll());
		return "operator/product";
	}
	
	@GetMapping("/product/add")
	public String addProductsubcategory(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("unitmeasures", productService.findAllUnitMeasure());
		model.addAttribute("productmodels", productService.findAllProductModel());
		model.addAttribute("productsubcategories", productsubcategoryService.findAll());
		return "operator/addProduct";
	}
	
	@PostMapping("/product/add")
	public String saveProduct(@Validated(ProductValidation.class) @ModelAttribute Product product, BindingResult bindingResult, 
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) {
			return "redirect:/product/";
		}else {
			if(bindingResult.hasErrors()) {
				model.addAttribute("product", product);
				model.addAttribute("productsubcategories", productsubcategoryService.findAll());
				model.addAttribute("unitmeasures", productService.findAllUnitMeasure());
				model.addAttribute("productmodels", productService.findAllProductModel());
				return "/operator/addProduct";
			}else {
				productService.save(product);
				return "redirect:/product/";
			}	
		}
	}
	
	@GetMapping("/product/edit/{id}")
	public String updateProduct(@PathVariable("id") Integer id, Model model) {
		Product p = productService.findById(id).get();
		if (p == null)
			throw new IllegalArgumentException("Invalid product Id:" + id);
		
		model.addAttribute("product", p);
		model.addAttribute("productsubcategories", productsubcategoryService.findAll());
		model.addAttribute("unitmeasures", productService.findAllUnitMeasure());
		model.addAttribute("productmodels", productService.findAllProductModel());
		
		return "operator/editProduct";
	}
	
	@PostMapping("/product/edit/{id}")
	public String updateProduct(@PathVariable("id") Integer id, @Validated(ProductValidation.class) 
	@ModelAttribute Product product, BindingResult bindingResult, 
	Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) {
			return "redirect:/product";
		}
		if(bindingResult.hasErrors()) {
			Product p = productService.findById(id).get();
			if (p == null)
				throw new IllegalArgumentException("Invalid product Id:" + id);
			
			model.addAttribute("product", p);
			model.addAttribute("productsubcategories", productsubcategoryService.findAll());
			model.addAttribute("unitmeasures", productService.findAllUnitMeasure());
			model.addAttribute("productmodels", productService.findAllProductModel());
			return "operator/editProduct";
		}
		product.setProductid(id);
		productService.edit(product);
		return "redirect:/product";
	}
	
	@GetMapping("/Workorder/{id}")
    public String queryWorkordersByProduct(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("workorders", workorderService.findByProduct(id));
        return "operator/workordersByProducts";
    }
	
	@GetMapping("/workorder")
	public String workorder(Model model) {
		model.addAttribute("workorder", workorderService.findAll());
		return "operator/workorder";
	}
	
	@GetMapping("/workorder/add")
	public String addWorkorder(Model model) {
		model.addAttribute("workorder", new Workorder());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("scrapreasons", workorderService.findAllScrapreason());
		return "operator/addWorkorder";
	}
	
	@PostMapping("/workorder/add")
	public String saveWorkorder(@Validated(WorkorderValidation.class) @ModelAttribute  Workorder workorder, 
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) {
			return "redirect:/operator/operator.html";
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("workorder", workorder);
			model.addAttribute("products", productService.findAll());
			model.addAttribute("scrapreasons", workorderService.findAllScrapreason());
			return "/operator/addWorkorder";
		} else {
			workorderService.save(workorder);
			return "redirect:/workorder/";
		}

	}
	
	@GetMapping("/workorder/edit/{id}")
	public String updateWorkorder(@PathVariable("id") Integer id, Model model) {
		Workorder wo = workorderService.findById(id).get();
		if (wo == null)
			throw new IllegalArgumentException("Invalid workorder Id:" + id);
		
		model.addAttribute("workorder", wo);
		model.addAttribute("products", productService.findAll());
		model.addAttribute("scrapreasons", workorderService.findAllScrapreason());
		
		return "operator/editWorkorder";
	}
	
	@PostMapping("/workorder/edit/{id}")
	public String updateWorkorder(@PathVariable("id") Integer id, @Validated(WorkorderValidation.class) 
	@ModelAttribute Workorder workorder, BindingResult bindingResult, 
	Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) {
			return "redirect:/workorder";
		}
		if(bindingResult.hasErrors()) {
			Workorder wo = workorderService.findById(id).get();
			if (wo == null)
				throw new IllegalArgumentException("Invalid workorder Id:" + id);
			
			model.addAttribute("workorder", wo);
			model.addAttribute("products", productService.findAll());
			model.addAttribute("scrapreasons", workorderService.findAllScrapreason());
			return "operator/editWorkorder";
		}
		workorder.setWorkorderid(id);
		workorderService.edit(workorder);
		return "redirect:/workorder";
	}
	
}
