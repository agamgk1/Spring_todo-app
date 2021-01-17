package com.spring.course.logic;

import com.spring.course.TaskConfigurationProperties;
import com.spring.course.model.*;
import com.spring.course.model.projection.GroupReadModel;
import com.spring.course.model.projection.GroupTaskWriteModel;
import com.spring.course.model.projection.GroupWriteModel;
import com.spring.course.model.projection.ProjectWriteModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private ProjectRepository repository;
    private TaskGroupRepository taskGroupRepository;
    private TaskGroupService taskGroupService;
    private TaskConfigurationProperties config;

    public ProjectService(ProjectRepository repository, TaskGroupRepository taskGroupRepository,TaskGroupService taskGroupService, TaskConfigurationProperties config) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
        this.taskGroupService = taskGroupService;
        this.config = config;
    }
    public List<Project> readAll() {
        return repository.findAll();
    }

    public Project save(ProjectWriteModel toSave) {
        return repository.save(toSave.toProject());
    }

    public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {

        if (!config.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        return repository.findById(projectId)
                .map(project -> {
                    var targetGroup = new GroupWriteModel();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks
                            (project.getSteps()
                                    .stream()
                                    .map(ProjectStep -> {
                                        var task = new GroupTaskWriteModel();
                                        task.setDescription(ProjectStep.getDescription());
                                        task.setDeadline(deadline.plusDays(ProjectStep.getDaysToDeadline()));
                                        return task;
                                    }
                                    ).collect(Collectors.toList())

                            );
                   return taskGroupService.createGroup(targetGroup, project);
                }).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
    }
}
