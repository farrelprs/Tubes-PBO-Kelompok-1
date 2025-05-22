package com.pboToDoList.ToDoList.task;

import java.util.List;

public interface EveryTask {
    public abstract void addTask(Task task);
    public abstract void updateTask(String taskTitle, Task updatedTask);
    public abstract void deleteTask(String taskTitle);
    public abstract Task getTask(int taskId);
    public abstract List<Task> getAllTasks();

}
