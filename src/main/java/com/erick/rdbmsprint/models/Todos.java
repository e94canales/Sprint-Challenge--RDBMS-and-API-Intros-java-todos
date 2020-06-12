package com.erick.rdbmsprint.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todos extends Auditor{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;
    @Column(nullable = false)
    private String description;
    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("todos")
    private User user;

    public Todos() {
    }

    public Todos(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
