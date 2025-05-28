package com.pboToDoList.ToDoList.repository;

import com.pboToDoList.ToDoList.category.Category;
import com.pboToDoList.ToDoList.user.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
    List<Category> findByRuser(RegularUser user);
}

