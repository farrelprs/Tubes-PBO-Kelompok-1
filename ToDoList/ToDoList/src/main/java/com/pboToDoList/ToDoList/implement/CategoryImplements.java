package com.pboToDoList.ToDoList.implement;

import com.pboToDoList.ToDoList.category.Category;
import com.pboToDoList.ToDoList.category.CategoryService;
import com.pboToDoList.ToDoList.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImplements implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryImplements(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new RuntimeException("Category name already exists");
        }else{
            categoryRepository.save(category);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(int CategoryId){
        categoryRepository.deleteById(CategoryId);
    }
}

