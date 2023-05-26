package com.spsisys.scoreboard.entities;

public class Team {
    Integer id;
    String name;

    // Constructors
    public Team(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}