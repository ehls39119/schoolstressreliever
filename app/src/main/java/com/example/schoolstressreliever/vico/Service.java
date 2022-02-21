package com.example.schoolstressreliever.vico;

import java.util.ArrayList;

public class Service {

    private String name;
    private ArrayList<String> intrestArea;;
    private int capacity;
    private ArrayList<String> participants;
    private String info;

    public Service(String name,
               String teacher,
               ArrayList<String> intrestArea,
               double price,
               ArrayList<String> participants,
               String info) {

        this.name = name;
        this.intrestArea = intrestArea;
        this.participants = participants;
        this.info = info;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
