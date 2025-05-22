package com.pboToDoList.ToDoList.controller;

import com.pboToDoList.ToDoList.repository.RuserRepository;
import com.pboToDoList.ToDoList.task.EveryTask;
import com.pboToDoList.ToDoList.task.Task;
import com.pboToDoList.ToDoList.user.RegularUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private EveryTask etask;
    private RuserRepository ruserRepository;

    @Autowired
    public TaskController(EveryTask etask, RuserRepository ruserRepository) {
        this.etask = etask;
        this.ruserRepository = ruserRepository;
    }

    @GetMapping("{taskId}")
    public Task getTaskDetails(@PathVariable("taskId") int taskId){
        return etask.getTask(taskId);
    }

    @PostMapping("/add/{username}")
    public String addTaskDetails(@PathVariable String username, @RequestBody Task task) {
        RegularUser ruser = ruserRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        task.setUser(ruser);
        etask.addTask(task);
        return "Task has been created for user " + ruser.getUsername();
    }

    @PutMapping("/{taskTitle}")
    public ResponseEntity<String> updateTaskDetails(@PathVariable String taskTitle, @RequestBody Task task){
        try {
            etask.updateTask(taskTitle, task);
            return ResponseEntity.ok("Task has been updated");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Update failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/{taskTitle}")
    public String deleteTaskDetails(@PathVariable("taskTitle") String taskTitle){
        etask.deleteTask(taskTitle);
        return "Task has been deleted";
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return etask.getAllTasks();
    }

}
