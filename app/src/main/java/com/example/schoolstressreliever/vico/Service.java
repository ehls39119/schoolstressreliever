package com.example.schoolstressreliever.vico;

import java.util.ArrayList;

public class Service {

    private String name;
    private ArrayList<String> intrestArea;;
    private String email;
    private ArrayList<String> participants;
    private String description;

    public Service(String name,
                   String email,
                   ArrayList<String> intrestArea,
                   ArrayList<String> participants,
                   String description) {

        this.name = name;
        this.email = email;
        this.intrestArea = intrestArea;
        this.participants = participants;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIntrestArea() {
        return intrestArea;
    }

    public void setIntrestArea(ArrayList<String> intrestArea) {
        this.intrestArea = intrestArea;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
