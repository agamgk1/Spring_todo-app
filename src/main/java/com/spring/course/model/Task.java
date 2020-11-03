package com.spring.course.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

//oznacza że w tej klasie bedzie tworzona tabela bazy danych
@Entity
@Table(name = "tasks") // nazwa tabeli
public class Task  {
    //te pola zostana zmapowane na kolumny table "tasks
    // pole id musi miec specjalne oznaczenie (hibernate musi je odroznic)
    // adnotacja generated - umozliwia generowanie id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //wtedy kolumna w bazie bedzie nazywała sie desc zamias description column umozliwia rowniez inne ustawienia
  //  @Column(name = "desc") - hibernate
    @NotBlank(message = "Task's description must not be null and not empty")  //adnotacja służąca do validacji - zaklada ze opis (pole) nie moze byc pusty albo null albo same spacj itp
    private String description;
    private boolean done;
    private LocalDateTime deadline;
    // osadzenie klasy Audit ktora jest embdabled. Czyli pola z klasy Audit beda dostepne tutaj
    @Embedded
    private Audit audit = new Audit();
    // umozliwia zapis wielu do jednej grupy (wiele taskow do jednej grupy) + wskazanie grupy do join
    @ManyToOne
    @JoinColumn(name = "task_group_id")
    private TaskGroup group;


    //konstruktor potrzebny jest do hibernate
   public Task() {
    }

    public Task(String description, LocalDateTime deadline) {
        this(description, deadline, null);
    }

    // konstruktor ktory pozwoli stworzyc taska na podstawie description i deadline i taskGroup
    public Task(@NotBlank(message = "Task's description must not be null and not empty") String description, LocalDateTime deadline, TaskGroup group) {
        this.description = description;
        this.deadline = deadline;
        if(group != null) {
            this.group = group;
        }
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    TaskGroup getGroup() {
        return group;
    }

    void setGroup(TaskGroup group) {
        this.group = group;
    }

    //metoda pomocnicza do aktualizacji pol
    public void updateFrom(final Task source) {
        description = source.description;
        done = source.done;
        deadline = source.deadline;
        group = source.group;
    }


}

