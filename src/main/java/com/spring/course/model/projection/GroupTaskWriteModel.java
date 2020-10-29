package com.spring.course.model.projection;

import com.spring.course.model.Task;

import java.time.LocalDateTime;


// Faktyczny task powstaje wlasnie z tego Grouptaska
// task wysylany w obrebie grupy
class GroupTaskWriteModel {
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
    public Task toTask() {
        return new Task(description, deadline);
    }
}

