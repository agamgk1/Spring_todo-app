package com.spring.course.model.event;

import com.spring.course.model.Task;

import java.time.Clock;

public class TaskUndone extends TaskEvent {
     TaskUndone(Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
