package com.pboToDoList.ToDoList.repository;

import com.pboToDoList.ToDoList.task.Task;
import com.pboToDoList.ToDoList.task.TaskPriority;
import com.pboToDoList.ToDoList.user.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByRuser(RegularUser ruser);
}

