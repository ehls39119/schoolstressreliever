package com.example.schoolstressreliever.vico;

import java.util.ArrayList;

public class CCA {

    private String name;
    private String teacher;
    private ArrayList<String> intrestArea;;
    private double price;
    private int capacity;
    private ArrayList<String> participants;
    private String hours;

    public CCA(String name,
               String teacher,
               ArrayList<String> intrestArea,
               double price,
               ArrayList<String> participants,
               String hours) {

        this.name = name;
        this.teacher = teacher;
        this.intrestArea = intrestArea;
        this.price = price;
        this.participants = participants;
        this.hours = hours;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public ArrayList<String> getIntrestArea() {
        return intrestArea;
    }

    public void setIntrestArea(ArrayList<String> intrestArea) {
        this.intrestArea = intrestArea;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getHours() {
        return hours;
    }

    public void setInfo(String info) {
        this.hours = hours;
    }
}
