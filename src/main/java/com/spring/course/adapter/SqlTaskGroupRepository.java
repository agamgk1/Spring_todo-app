package com.spring.course.adapter;

import com.spring.course.model.TaskGroup;
import com.spring.course.model.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

//interfejs (springowy) słuzący do komunikacji z bazą danych (repozytorium) dla klasy (tabeli) TaskGroup
//rozszerza JpaRepository ze springa oraz przypisanie encji Id (id jest integerem) z klasy Task. Dodatkowo rozszeza nasz interfejs z publicznym repozytorium
//JpaRepository - repozytorium sql. Każda metoda będzie tłumaczona na zapytanie sqlowe

interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Integer> {

}
