package com.spring.course.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Project's description must not be empty")
    private String description;
    // projekt bedzie posiadal liste wszystkich grup
    @OneToMany(mappedBy = "project")
    private Set<TaskGroup> groups;
    // projekt bedzie posiadal liste wszystkich project step
    // plus przy kazdej zmianie projektu zmieniamy wszyskie jego kroki - cascade
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<ProjectStep> steps;

    Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    Set<TaskGroup> getGroups() {
        return groups;
    }

    void setGroups(Set<TaskGroup> groups) {
        this.groups = groups;
    }

    public Set<ProjectStep> getSteps() {
        return steps;
    }

    void setSteps(Set<ProjectStep> steps) {
        this.steps = steps;
    }
}
