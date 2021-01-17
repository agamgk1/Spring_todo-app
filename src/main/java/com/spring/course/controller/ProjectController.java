package com.spring.course.controller;

import com.spring.course.logic.ProjectService;
import com.spring.course.model.Project;
import com.spring.course.model.ProjectStep;
import com.spring.course.model.projection.ProjectWriteModel;
import io.micrometer.core.annotation.Timed;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

//klasa ktora bedzie wyświetlała okno projects.html - w return
//Model podwoli na komunikacje pomiedzy szablonem (project.html) a kontrolerem, przekazanie komunikatów itp, wysyłanie wiadomości
//Definicja Autoruzacji w adnotacji na klasie - do projektu maga wejsc tylko uzytkownicy z rola admin
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/projects")
class ProjectController {
    private final ProjectService service;

    ProjectController(final ProjectService service) {
        this.service = service;
    }

    @GetMapping
    String showProjects(Model model, Authentication auth) {
     
            model.addAttribute("project", new ProjectWriteModel());
            return "projects";
    }
    
    @PostMapping
    String addProject(@ModelAttribute("project") @Valid ProjectWriteModel current,
                      BindingResult bindingResult,
                      Model model) {

        if (bindingResult.hasErrors()) {
            return "projects";
        }
        service.save(current);
        model.addAttribute("project", new ProjectWriteModel());
        model.addAttribute("projects", getProjects());
        model.addAttribute("message", "Dodano projekt!");
        return "projects";
    }
    @PostMapping(params = "addStep")
    String addProjectStep(@ModelAttribute("project") ProjectWriteModel current) {
        current.getSteps().add(new ProjectStep());
        return "projects";
    }
    @Timed(value = "project.create.group",histogram = true, percentiles = {0.5, 0.95, 0.99})
    @PostMapping("/{id}")
    String createGroup(
        @ModelAttribute("project") ProjectWriteModel current,
        Model model,
        @PathVariable int id,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime deadline
    ) {
        try {
            service.createGroup(deadline,id);
            model.addAttribute("message", "Dodano grupę");
        } catch (IllegalStateException | IllegalArgumentException exception) {
            model.addAttribute("message", "Błąd podczas tworzenia grupy");
        }
        return "projects";
    }
    
    @ModelAttribute("projects")
    List<Project> getProjects() {
        return service.readAll();
    }

}
