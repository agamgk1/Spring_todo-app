package com.spring.course.model;

import java.util.List;
import java.util.Optional;

// publiczne repozytorium z metodami udostepnionymi publicznie dla klasy (tabeli) TaskGroup
public interface ProjectRepository {

    List<Project> findAll();

    Optional<Project> findById(Integer id);

    Project save(Project entity);

}
