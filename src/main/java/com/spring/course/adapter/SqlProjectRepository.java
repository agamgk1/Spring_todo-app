package com.spring.course.adapter;

import com.spring.course.model.Project;
import com.spring.course.model.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//interfejs (springowy) słuzący do komunikacji z bazą danych (repozytorium) dla klasy (tabeli) TaskGroup
//rozszerza JpaRepository ze springa oraz przypisanie encji Id (id jest integerem) z klasy Task. Dodatkowo rozszeza nasz interfejs z publicznym repozytorium
//JpaRepository - repozytorium sql. Każda metoda będzie tłumaczona na zapytanie sqlowe

@Repository
interface SqlProjectRepository extends ProjectRepository, JpaRepository<Project, Integer> {
    // w query znajduje sie zapytanie na encjach (w tym przypadku nazwa encji to nazwa klasy)
    // tasks to pole z klasy TaskGroup
    // fetch zabezpieczenie przez lazy loadingiem
    @Override
    @Query("from Project p join fetch p.steps")
    List<Project> findAll();

}
