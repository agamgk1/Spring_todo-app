package com.spring.course.model.event;

import com.spring.course.model.Task;

import java.time.Clock;
import java.time.Instant;

public abstract class TaskEvent {

    public static TaskEvent changed(Task source) {
        return source.isDone() ? new TaskDone(source) : new TaskUndone(source);
    }

    private int taskId;
    private Instant occurrence;

    public int getTaskId() {
        return taskId;
    }

    TaskEvent(int taskId, Clock clock) {
        this.taskId = taskId;
        this.occurrence = Instant.now();
    }

    public Instant getOccurrence() {
        return occurrence;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "taskId=" + taskId +
                ", occurrence=" + occurrence +
                '}';
    }
}
