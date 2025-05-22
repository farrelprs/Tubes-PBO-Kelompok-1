package com.pboToDoList.ToDoList.repository;

import com.pboToDoList.ToDoList.task.Task;
import com.pboToDoList.ToDoList.user.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuserRepository extends JpaRepository<RegularUser, Integer> {
    Optional<RegularUser> findByEmail(String email);
    Optional<RegularUser> findByUsername(String username);
}
