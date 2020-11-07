package com.spring.course.controller;

import com.spring.course.logic.ProjectService;
import com.spring.course.model.Project;
import com.spring.course.model.ProjectStep;
import com.spring.course.model.projection.ProjectWriteModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//klasa ktora bedzie wyświetlała okno projects.html - w return
//Model podwoli na komunikacje pomiedzy szablonem (project.html) a kontrolerem
@Controller
@RequestMapping("/projects")
class ProjectController {
    private final ProjectService service;

    ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    String showProjects(Model model) {
        model.addAttribute("project", new ProjectWriteModel());
        return "projects";
    }
    @PostMapping
    String addProject(@ModelAttribute("project") ProjectWriteModel current, Model model) {
        service.save(current);
        model.addAttribute("project", new ProjectWriteModel());
        //message zostanie wysłane do <h1> w projects.html
        // dodano projekt wyswietli sie po nacisnieciu przycisku z subbmit
        model.addAttribute("message", "Dodano projekt!");
        return "projects";
    }

    //"addStep" parametr przypisany do przycisku "+" w projects.html. Plus dodatkowo trzeba dodac @ModelAtribute.
    //Metoda tworzy nowy krok
    @PostMapping(params = "addStep")
    String addProjectStep(@ModelAttribute("project") ProjectWriteModel current) {
        current.getSteps().add(new ProjectStep());
        return "projects";
    }
    @ModelAttribute("projectsList")
    List<Project> getProjects() {
        return service.readAll();
    }

}
