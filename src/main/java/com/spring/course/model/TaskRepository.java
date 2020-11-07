package com.spring.course.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

// publiczne repozytorium z metodami udostepnionymi publicznie dla klasy (tabeli) Task
public interface TaskRepository {

    List<Task> findAll();

    Optional<Task> findById(Integer id);

    boolean existsById(Integer id);

    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);

    Task save(Task entity);

    Page<Task> findAll(Pageable pageable);

    List<Task>findByDone(boolean state);

    List<Task> findAllByGroup_Id(Integer groupId);
}
