package com.pboToDoList.ToDoList.implement;

import com.pboToDoList.ToDoList.category.Category;
import com.pboToDoList.ToDoList.category.CategoryService;
import com.pboToDoList.ToDoList.repository.CategoryRepository;
import com.pboToDoList.ToDoList.user.RegularUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImplements implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryImplements(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(Category category, RegularUser user) {
        category.setRuser(user);
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> getCategoriesByUser(RegularUser user) {
        return categoryRepository.findByRuser(user);
    }

    @Override
    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }
}

