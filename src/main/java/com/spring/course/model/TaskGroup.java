package com.spring.course.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "task_groups") // nazwa tabeli
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Task group's description must not be null and not empty")  //adnotacja służąca do validacji - zaklada ze opis (pole) nie moze byc pusty albo null albo same spacj itp
    private String description;
    private boolean done;
    // jezeli w tasku bylo manytoone to tutaj musi byc OneToMany + zbiór taskow + public getter
    // cascade.all - jezeli usuwamy grupe to usuwamy jej wszyskie taski
    // mapped - pokazanie ze wewnatrztaska grupa zmapowana jest jako pole grup
    @OneToMany(cascade = CascadeType.ALL, mappedBy ="group")
    private Set<Task> tasks;

    TaskGroup() {
    }

    public int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    void setDescription(String description) {
        this.description = description;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public Set<Task> getTasks() {
        return tasks;
    }
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}

