package com.pboToDoList.ToDoList.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pboToDoList.ToDoList.category.Category;
import com.pboToDoList.ToDoList.user.RegularUser;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "ruser_id"})})
public class Task {

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String title;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    private double progress;
    private boolean completed = false;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name = "ruser_id", nullable = false)
    private RegularUser ruser;

    public Task() {
    }

    public Task(String title, String description, LocalDate deadline, double progress) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public double getProgress() { return progress; }

    public void setProgress(double progress) {
        this.progress = progress;
        this.completed = progress >= 100;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public RegularUser getUser() {
        return ruser;
    }

    public void setUser(RegularUser ruser) {
        this.ruser = ruser;
    }
}
