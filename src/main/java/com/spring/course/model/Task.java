package com.spring.course.model;

import com.spring.course.model.event.TaskEvent;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks") // nazwa tabeli
public class Task  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Task's description must not be null and not empty") 
    private String description;
    private boolean done;
    private LocalDateTime deadline;
    @Embedded
    private Audit audit = new Audit();
    @ManyToOne
    @JoinColumn(name = "task_group_id")
    private TaskGroup group;

   public Task() {
    }

    public Task(String description, LocalDateTime deadline) {
        this(description, deadline, null);
    }

    public Task(@NotBlank(message = "Task's description must not be null and not empty") String description, LocalDateTime deadline, TaskGroup group) {
        this.description = description;
        this.deadline = deadline;
        if(group != null) {
            this.group = group;
        }
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

    public TaskEvent toggle() {
        this.done = !this.done;
        return TaskEvent.changed(this);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    TaskGroup getGroup() {
        return group;
    }

    void setGroup(TaskGroup group) {
        this.group = group;
    }

    //metoda pomocnicza do aktualizacji pol
    public void updateFrom(final Task source) {
        description = source.description;
        done = source.done;
        deadline = source.deadline;
        group = source.group;
    }


}

