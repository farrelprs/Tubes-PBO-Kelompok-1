package com.pboToDoList.ToDoList.implement;
import com.pboToDoList.ToDoList.category.Category;
import com.pboToDoList.ToDoList.repository.CategoryRepository;
import com.pboToDoList.ToDoList.repository.TaskRepository;
import com.pboToDoList.ToDoList.task.Task;
import com.pboToDoList.ToDoList.task.EveryTask;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskImplements implements EveryTask {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public TaskImplements(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void updateTask(String taskTitle, Task task) {
        Task existingTask = taskRepository.findByTitle(taskTitle)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setDescription(task.getDescription());
        existingTask.setDeadline(task.getDeadline());
        existingTask.setProgress(task.getProgress());
        existingTask.setPriority(task.getPriority());

        if (task.getCategory() != null && task.getCategory().getName() != null) {
            Category category = categoryRepository.findByName(task.getCategory().getName())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingTask.setCategory(category);
        }

        taskRepository.save(existingTask);
    }


    @Override
    public void addTask(Task task) {
        if (task.getCategory() != null && task.getCategory().getName() != null) {
            String name = task.getCategory().getName();

            Category existingCategory = categoryRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Category '" + name + "' not found"));

            task.setCategory(existingCategory);
        }

        taskRepository.save(task);
    }


    @Override
    public void deleteTask(String taskTitle){
        Task task = taskRepository.findByTitle(taskTitle)
            .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }

    @Override
    public Task getTask(int taskId) { return taskRepository.findById(taskId).orElse(null); }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
