package com.spring.course.controller;

import com.spring.course.logic.ProjectService;
import com.spring.course.model.Project;
import com.spring.course.model.ProjectStep;
import com.spring.course.model.projection.ProjectWriteModel;
import io.micrometer.core.annotation.Timed;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

//klasa ktora bedzie wyświetlała okno projects.html - w return
//Model podwoli na komunikacje pomiedzy szablonem (project.html) a kontrolerem, przekazanie komunikatów itp, wysyłanie wiadomości
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
    // BindingResult sluzy do walidacji, sprawdza czy poprzedni argument (projectWriteModel) miał jakies bledy)
    // @Valid musi byc dodane. Valid uwzgledni wszystkie walidacje NotBlank: z ProjectWroteModel, ProjectStep
    @PostMapping
    String addProject(@ModelAttribute("project") @Valid ProjectWriteModel current,
                      BindingResult bindingResult,
                      Model model) {

        if (bindingResult.hasErrors()) {
            return "projects";
        }
        service.save(current);
        model.addAttribute("project", new ProjectWriteModel());
        // nadpisanie atrybutu "projects" w celu odswiezenia strony
        model.addAttribute("projects", getProjects());
        //message zostanie wysłane do <h1> w projects.html
        // dodano projekt wyswietli sie po nacisnieciu przycisku z subbmit
        model.addAttribute("message", "Dodano projekt!");
        return "projects";
    }
    //"addStep" parametr przypisany do przycisku "+" w projects.html. Plus dodatkowo trzeba dodac @ModelAtribute.
    //Metoda tworzy nowy krok
    //ModelAtribute - pozwala na zachowanie "project"
    @PostMapping(params = "addStep")
    String addProjectStep(@ModelAttribute("project") ProjectWriteModel current) {
        current.getSteps().add(new ProjectStep());
        return "projects";
    }
    //@DateTimeFormat - wskazanie w jakim formacie ma byc obslugiwana data
    // @Timed adnotacja służąca do pomiaru czasu, value = nazwa metryki
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
