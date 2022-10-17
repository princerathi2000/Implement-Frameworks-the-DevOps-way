package com.rathifitnesss.onlineShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rathifitnesss.onlineShop.entity.User;
import com.rathifitnesss.onlineShop.service.CategoryService;
import com.rathifitnesss.onlineShop.service.UserService;


@Controller
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	// handler method to handle list user and return mode and view
	@GetMapping("/user")
	public String listUsers(Model model) {
		model.addAttribute("user", userService.getAllUser());
		return "user";
	}
	
	@GetMapping("/user/new")
	public String createUserForm(Model model) {
		
		// create user object to hold user form data
		User user = new User();
		model.addAttribute("user", user);
		return "create_user";
		
	}
	
	@PostMapping("/user")
	public String saveUser(@ModelAttribute("user") User user) {
		userService.saveUser(user);
		return "redirect:/user";
	}
	
	@GetMapping("/user/edit/{id}")
	public String editUserForm(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "edit_user";
	}

	@PostMapping("/user/{id}")
	public String updateUser(@PathVariable Integer id,
			@ModelAttribute("user") User user,
			Model model) {
		
		// get user from database by id
		User existingUser = userService.getUserById(id);
		existingUser.setId(id);
		existingUser.setName(user.getName());
		existingUser.setPassword(user.getPassword());
		existingUser.setEmail(user.getEmail());
		
		// save updated user object
		userService.updateUser(existingUser);
		return "redirect:/user";		
	}
	
	// handler method to handle delete user request
	
	@GetMapping("/user/{id}")
	public String deleteUser(@PathVariable Integer id) {
		userService.deleteUserById(id);
		return "redirect:/user";
	}
	
	
}
