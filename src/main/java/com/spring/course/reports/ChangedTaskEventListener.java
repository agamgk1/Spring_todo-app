package com.spring.course.reports;

import com.spring.course.model.event.TaskDone;
import com.spring.course.model.event.TaskUndone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
class ChangedTaskEventListener {
    private static final Logger logger = LoggerFactory.getLogger(ChangedTaskEventListener.class);
    private final PersistedTaskEventRepository repository;

    ChangedTaskEventListener(PersistedTaskEventRepository repository) {
        this.repository = repository;
    }
    
    @Async
    @EventListener
    void on(TaskDone event) {
        logger.info("got" + event);
        repository.save(new PersistedTaskEvent(event));
    }
    @Async
    @EventListener
    void on(TaskUndone event) {
        logger.info("got" + event);
        repository.save(new PersistedTaskEvent(event));
    }
}
