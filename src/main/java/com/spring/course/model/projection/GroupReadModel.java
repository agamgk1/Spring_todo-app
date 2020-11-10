package com.spring.course.model.projection;

import com.spring.course.model.Task;
import com.spring.course.model.TaskGroup;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

// wszystkie klasy model z projection (dto) okreslaja jak encja powinna wygladac dla uzytkownika
public class GroupReadModel {
    private int id;
    private String description;
    // deadliine ostatniego taska w grupie
    private LocalDateTime deadline;
    // zbior taskow do odczytu
    private Set<GroupTaskReadModel> tasks;

    // konstruktor pobierajacy faktyczna grupe
    public GroupReadModel(TaskGroup source) {
        id = source.getId();
        description = source.getDescription();
        // stream bo w taskGroup task jest zbiorem. Zwroci najwyzsza date i przypisze pod deadline
        source.getTasks().stream()
                .map(Task::getDeadline)
                //filtrowanie zeby nie było nulla
                .filter(Objects::nonNull)
                .max(LocalDateTime::compareTo)
                .ifPresent(date -> deadline = date);
        tasks = source.getTasks().stream()
                .map(GroupTaskReadModel::new)
                .collect(Collectors.toSet());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Set<GroupTaskReadModel> getTasks() {
        return tasks;
    }

    public void setTasks(Set<GroupTaskReadModel> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}