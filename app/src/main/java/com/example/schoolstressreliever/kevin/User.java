package com.example.schoolstressreliever.kevin;

import java.util.ArrayList;

public class User {
    private String ID;
    private String name;
    private String email;
    private String password;
    private String yearLevel;
    private double hoursAvailable;
    private String areaOfInterest;
    private ArrayList<String> userSubjectList;

    public User(String ID, String name, String email, String yearLevel, String password, double hoursAvailable, String areaOfInterest) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.yearLevel = yearLevel;
        this.hoursAvailable = hoursAvailable;
        this.areaOfInterest = areaOfInterest;
    }

    public String getAreaOfInterest() {
        return areaOfInterest;
    }

    public void setAreaOfInterest(String areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }

    public double getHoursAvailable()
    {
        return hoursAvailable;
    }

    public void setHoursAvailable(double hoursAvailable)
    {
        this.hoursAvailable = hoursAvailable;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public ArrayList<String> getUserSubjectList() {
        return userSubjectList;
    }

    public void setUserSubjectList(ArrayList<String> userSubjectList) {
        this.userSubjectList = userSubjectList;
    }
}
