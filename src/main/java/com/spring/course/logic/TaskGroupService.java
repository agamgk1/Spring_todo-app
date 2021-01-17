package com.spring.course.logic;

import com.spring.course.model.Project;
import com.spring.course.model.TaskGroup;
import com.spring.course.model.TaskGroupRepository;
import com.spring.course.model.TaskRepository;
import com.spring.course.model.projection.GroupReadModel;
import com.spring.course.model.projection.GroupWriteModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskGroupService {
    private TaskGroupRepository repository;
    private TaskRepository taskRepository;

    public TaskGroupService(TaskGroupRepository repository, TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }
    public GroupReadModel createGroup(final GroupWriteModel source) {
       return createGroup(source, null);
    }
    GroupReadModel createGroup(GroupWriteModel source, Project project) {
        TaskGroup result = repository.save(source.toGroup(project));
        return new GroupReadModel(result);
    }
    public List<GroupReadModel> readAll() {
       return repository.findAll()
                .stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }
    public void toggleGroup(int groupId) {
        if (taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)) {
            throw new IllegalStateException("Group has undone tasks. Done all the tasks first");
        }
        TaskGroup result = repository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("TaskGroup with given Id not found"));
        result.setDone(!result.isDone());
        repository.save(result);
    }
}
