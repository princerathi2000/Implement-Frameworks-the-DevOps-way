package com.rathifitnesss.onlineShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rathifitnesss.onlineShop.entity.Category;
import com.rathifitnesss.onlineShop.entity.Product;
import com.rathifitnesss.onlineShop.entity.User;
import com.rathifitnesss.onlineShop.service.CategoryService;


@Controller
public class CategoryController {
	
	CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	// handler method to handle list product and return mode and view
		@GetMapping("/admin/categories")
		public String listCategories(Model model) {
			System.out.println(categoryService.getAllCategory());
			model.addAttribute("category", categoryService.getAllCategory());
			return "category";
		}
	
		@GetMapping("/admin/categories/add")
		public String createCategoryForm(Model model) {
			
			// create product object to hold product form data
			Category category = new Category();
			model.addAttribute("category", category);
			return "create_category";
		}
		
		@PostMapping("/admin/categories")
		public String saveCategory(@ModelAttribute("category") Category category) {
			categoryService.saveCategory(category);
			return "redirect:/admin/categories";
		}
		
		//got to edit page 
		@GetMapping("/admin/category/edit/{id}")
		public String editCategoryForm(@PathVariable Integer id, Model model) {
			model.addAttribute("category", categoryService.getCategoryById(id));
			return "edit_category";
		}

		//handel to update the category
		@PostMapping("/admin/category/{id}")
		public String updateCategory(@PathVariable Integer id,
				@ModelAttribute("category") Category category,
				Model model) {
			
			// get product from database by id
			Category existingCategory = categoryService.getCategoryById(id);
			existingCategory.setId(id);
			existingCategory.setName(category.getName());
			
			// save updated product object
			categoryService.updateCategory(existingCategory);
			return "redirect:/admin/categories";		
		}
		
		// handler method to handle delete category request
		
		@GetMapping("/admin/category/{id}")
		public String deleteCategory(@PathVariable Integer id) {
			categoryService.deleteCategoryById(id);
			return "redirect:/admin/categories";
		}
}
