package com.spring.course.adapter;

import com.spring.course.model.Task;
import com.spring.course.model.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//interfejs (springowy) słuzący do komunikacji z bazą danych (repozytorium) dla klasy (tabeli) Task
//rozszerza JpaRepository ze springa oraz przypisanie encji Id (id jest integerem) z klasy Task. Dodatkowo rozszeza nasz interfejs z publicznym repozytorium
//JpaRepository - repozytorium sql. Każda metoda będzie tłumaczona na zapytanie sqlowe
@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> {

    //nadpisanie metody oraz wykorzystanie w niej sql. musza byc obie adnotacje
    // metody existsby sa to metody ze springa - spring podpowiada
    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from tasks where id=?1")
    boolean existsById(@Param("id") Integer id);
    // wyszukanie niezrobionych taskow w obrebie grupy
    @Override
    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);

    @Override
    List<Task> findAllByGroup_Id(Integer groupId);
}
