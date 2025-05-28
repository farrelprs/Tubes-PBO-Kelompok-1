package com.pboToDoList.ToDoList.category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pboToDoList.ToDoList.task.Task;
import com.pboToDoList.ToDoList.user.RegularUser;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "ruser_id"})})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "ruser_id", nullable = true)
    private RegularUser ruser;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Task> tasks;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return id != null && id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public RegularUser getRuser() {return ruser;}
    public void setRuser(RegularUser ruser) {this.ruser = ruser;}

    public List<Task> getTasks() { return tasks; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }

    @Override
    public String toString() {
        return "Category{id=" + id + ", name='" + name + "'}";
    }
}

