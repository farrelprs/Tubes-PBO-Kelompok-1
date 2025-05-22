package com.pboToDoList.ToDoList.controller;

import com.pboToDoList.ToDoList.category.Category;
import com.pboToDoList.ToDoList.category.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public String addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return "Category has been added";
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("{CategoryId}")
    public String deleteCategoryDetails(@PathVariable("CategoryId") int CategoryId){
        categoryService.deleteCategory(CategoryId);
        return "Category has been deleted";
    }
}

