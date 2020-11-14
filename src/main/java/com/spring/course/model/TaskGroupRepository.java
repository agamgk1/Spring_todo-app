package com.spring.course.model;

import java.util.List;
import java.util.Optional;

// publiczne repozytorium z metodami udostepnionymi publicznie dla klasy (tabeli) TaskGroup
public interface TaskGroupRepository {
    List<TaskGroup> findAll();

    Optional<TaskGroup> findById(Integer id);

    TaskGroup save(TaskGroup entity);

    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);

    boolean existsByDescription(String description);

}
