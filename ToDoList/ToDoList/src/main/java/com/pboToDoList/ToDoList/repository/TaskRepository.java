package com.pboToDoList.ToDoList.repository;

import com.pboToDoList.ToDoList.task.Task;
import com.pboToDoList.ToDoList.task.TaskPriority;
import com.pboToDoList.ToDoList.user.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findByTitle(String title);
    void deleteByTitle(String title);
    List<Task> findByPriority(TaskPriority priority);
    List<Task> findByRuser(RegularUser ruser); // fixed
}

