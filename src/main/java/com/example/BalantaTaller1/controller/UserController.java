package com.example.BalantaTaller1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BalantaTaller1.model.user.User;
import com.example.BalantaTaller1.service.user.UserServiceImpl;

public class UserController {
	
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@GetMapping("/login")
	public String loginPrincipal() {
		return "/login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "/login";
	}
	
	@GetMapping("/users/add")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("types", userService.getTypes());
		return "users/add-user";
	}
	
	@PostMapping("/users/add")
	public String saveUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model, 
			@RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("types", userService.getTypes());
				return "users/add-user";
			}
			userService.save(user);
		}
		return "redirect:/users/";
	}
	
	@PostMapping("/users/edit/{id}")
	public String updateUser(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action,
			@ModelAttribute("user") User user,
			BindingResult bindingResult, Model model) {

		if (action.equals("Cancel")) {
			return "redirect:/users/";
		} else {
			if (bindingResult.hasErrors()) {
				model.addAttribute("types", userService.getTypes());
				return "users/update-user";
			}
			String pass = user.getPassword();
			String re = user.getRepeatPassword();
			if((pass.length()>=8 && pass.equals(re))) {
				userService.update(user);
			}else {
				user.setPassword(userService.findById(id).get().getPassword());
				userService.update(user);
			}
			model.addAttribute("users", userService.findAll());
			return "redirect:/users/";
		}
	}
	
	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Optional<User> user = userService.findById(id);
		if (user.isEmpty())
			throw new IllegalArgumentException("Invalid id:" + id);
		model.addAttribute("user", user.get());
		model.addAttribute("types", userService.getTypes());
		return "users/update-user";
	}
}
