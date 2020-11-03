package com.spring.course.model.projection;

import com.spring.course.model.Task;
import com.spring.course.model.TaskGroup;

import java.time.LocalDateTime;


// Faktyczny task powstaje wlasnie z tego Grouptaska
// task wysylany w obrebie grupy
public class GroupTaskWriteModel {
    private String description;
    private LocalDateTime deadline;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    // zwroci nowego taska
    public Task toTask(TaskGroup group) {
        return new Task(description, deadline, group);
    }
}

