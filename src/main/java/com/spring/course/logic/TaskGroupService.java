package com.spring.course.logic;

import com.spring.course.model.Project;
import com.spring.course.model.TaskGroup;
import com.spring.course.model.TaskGroupRepository;
import com.spring.course.model.TaskRepository;
import com.spring.course.model.projection.GroupReadModel;
import com.spring.course.model.projection.GroupWriteModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//serwis to warstwa logiki posrednia miedzy repozytorium a kontrolerem. Jeden TaskGroupService na cała aplikacje. Z tego servisu moze korzystac tylko jeden kontroler - domyslne ustawienie w @Scope
// @RequestScope - W obrebie jednego żądania mamy unikalna instancje  serwisu - unikalny identyficator dla danego requesta. Od servisu z RequestScope zależą inne serwisy
//
@Service
public class TaskGroupService {
    // pozwoli na odczyt i zapis do bazy dany za posrednictwem serwisu
    private TaskGroupRepository repository;
    // dzikei temu bedziemy mogli dokonac sprawdzen
    private TaskRepository taskRepository;

    // konstruktor pobierajacy repozytoria
    public TaskGroupService(TaskGroupRepository repository, TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }
    // metoda tworzaca grupe z writemodel
    public GroupReadModel createGroup(final GroupWriteModel source) {
       return createGroup(source, null);
    }
    GroupReadModel createGroup(GroupWriteModel source, Project project) {
        TaskGroup result = repository.save(source.toGroup(project));
        return new GroupReadModel(result);
    }
    // metoda umozliwiajaca czytanie grup
    public List<GroupReadModel> readAll() {
       return repository.findAll()
                .stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }
    // zakonczenie grupy - operacja toggle na danej grupie
    // nie mozna zakonczyc grupy (ustawic done na true) jezeli grupa nie ma wszystkich taskow zrobionych na done
    public void toggleGroup(int groupId) {
        if (taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)) {
            throw new IllegalStateException("Group has undone tasks. Done all the tasks first");
        }
        TaskGroup result = repository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("TaskGroup with given Id not found"));
        result.setDone(!result.isDone());
        repository.save(result);
    }


}

