package com.spring.course;

import com.spring.course.model.Task;
import com.spring.course.model.TaskGroup;
import com.spring.course.model.TaskGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

//Klasa służąca do zaapylania przykładowych danych, do tworzenia catcha
//ContextRefreshedEvent - w momencie gdy kontrkst springa jest juz odswieżony robimy jakas akcje
@Component
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(Warmup.class);
    private final TaskGroupRepository groupRepository;

    public Warmup(TaskGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Aplication warmup after contex refreshed");
        final String description = "ApplicationContextEvent";
        if (!groupRepository.existsByDescription(description)) {
            logger.info("Ne required group found! Adding it!");
            var group = new TaskGroup();
            group.setDescription(description);
            group.setTasks(Set.of(
                    new Task("ContexClosedEvent", null, group),
                    new Task("ContexRefreshEvent", null, group),
                    new Task("ContexStopedEvent", null, group),
                    new Task("ContexStartedEvent", null, group)
            ));
            groupRepository.save(group);
        }
    }
}
