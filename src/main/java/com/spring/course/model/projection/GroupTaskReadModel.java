package com.spring.course.model.projection;

import com.spring.course.model.Task;

// task czytany w obrebie grupy
// powienien powstawac z innego taska
public class GroupTaskReadModel {
    private boolean done;
    private String description;

    // konstruktor - tworzy z innego taska
    public GroupTaskReadModel(Task source) {
        description = source.getDescription();
        done = source.isDone();
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
