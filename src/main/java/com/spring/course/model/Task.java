package com.spring.course.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//oznacza że w tej klasie bedzie tworzona tabela bazy danych
@Entity
// nazwa tabeli
@Table(name = "tasks")
public class Task {
    //te pola zostana zmapowane na kolumny table "tasks
    // pole id musi miec specjalne oznaczenie (hibernate musi je odroznic)
    @Id
    private int id;
    //wtedy kolumna w bazie bedzie nazywała sie desc zamias description
  //  @Column(name = "desc")
    private String description;
    private boolean done;

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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

