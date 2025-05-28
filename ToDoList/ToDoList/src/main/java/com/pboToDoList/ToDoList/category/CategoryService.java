package com.pboToDoList.ToDoList.category;

import com.pboToDoList.ToDoList.user.RegularUser;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void addCategory(Category category, RegularUser user);
    List<Category> getAllCategories();
    void deleteCategory(int categoryId);
    List<Category> getCategoriesByUser(RegularUser user);
    public Optional<Category> getCategoryById(Integer id);
    Optional<Category> findById(Integer categoryId);
}

