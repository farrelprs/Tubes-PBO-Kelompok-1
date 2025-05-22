package com.pboToDoList.ToDoList.category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);
    List<Category> getAllCategories();
    public abstract void deleteCategory(int CategoryId);
}

