package com.spring.course.logic;

import com.spring.course.TaskConfigurationProperties;
import com.spring.course.model.*;
import com.spring.course.model.projection.GroupReadModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private ProjectRepository repository;
    private TaskGroupRepository taskGroupRepository;
    private TaskConfigurationProperties config;

    public ProjectService(ProjectRepository repository, TaskGroupRepository taskGroupRepository, TaskConfigurationProperties config) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
    }

    private List<Project> readAll() {
        return repository.findAll();
    }

    private Project save(Project toSave) {
        return repository.save(toSave);
    }

    // tworzenie grupy dla danego projektu
    public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {

        if (!config.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        TaskGroup result = repository.findById(projectId)
                .map(project -> {
                    var targetGroup = new TaskGroup();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks
                            (project.getSteps()
                                    .stream()
                                    .map(ProjectStep -> new Task
                                            (ProjectStep.getDescription(), deadline.plusDays(ProjectStep.getDaysToDeadline())))
                                    .collect(Collectors.toSet())

                            );
                    targetGroup.setProject(project);
                    return taskGroupRepository.save(targetGroup);
                }).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
        return new GroupReadModel(result);
    }
}
