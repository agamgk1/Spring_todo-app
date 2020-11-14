package com.spring.course.reports;

import com.spring.course.model.event.TaskEvent;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "task_events")
class PersistedTaskEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int taskId;
    String name;
    LocalDateTime occurrence;

    // ze względu na JPA musi być dodany bezparametrowy konstruktor
    public PersistedTaskEvent() {
    }

    PersistedTaskEvent(TaskEvent source) {
        taskId = source.getTaskId();
        name = source.getClass().getSimpleName();
        occurrence = LocalDateTime.ofInstant(source.getOccurrence(), ZoneId.systemDefault());
    }


}
