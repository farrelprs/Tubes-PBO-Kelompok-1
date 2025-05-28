package com.pboToDoList.ToDoList.controller;

import com.pboToDoList.ToDoList.category.Category;
import com.pboToDoList.ToDoList.category.CategoryService;
import com.pboToDoList.ToDoList.repository.RuserRepository;
import com.pboToDoList.ToDoList.user.RegularUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final RuserRepository ruserRepository;

    public CategoryController(CategoryService categoryService, RuserRepository ruserRepository) {
        this.categoryService = categoryService;
        this.ruserRepository = ruserRepository;
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category, Authentication authentication) {
        String email = authentication.getName();
        RegularUser user = ruserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        category.setRuser(user);
        categoryService.addCategory(category, user);
        return "redirect:/task/my-tasks";
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam("categoryId") int categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/task/my-tasks";
    }
}

