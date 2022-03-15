package com.example.schoolstressreliever.kevin;

import java.util.ArrayList;

public class User {
    private String ID;
    private String name;
    private String email;
    private String password;
    private String yearLevel;
    private boolean formFilled;
    private String interestAreaService;
    private double hoursAvailableService;
    private String interestAreaActivity;
    private double hoursAvailableActivity;
    private String hours;
    private ArrayList<String> userSubjectList;

    public User(String ID, String name, String email, String yearLevel, String password, boolean formFilled, String interestAreaService, double hoursAvailableService, String interestAreaActivity, double hoursAvailableActivity)  {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.yearLevel = yearLevel;
        this.formFilled = formFilled;
        this.interestAreaService = interestAreaService;
        this.hoursAvailableService = hoursAvailableService;
        this.interestAreaActivity = interestAreaActivity;
        this.hoursAvailableActivity = hoursAvailableActivity;
    }

    public User(String uid, String displayName, String email, String yearLevel, String password, double hoursAvailable, String arts) {
    }

    public String getInterestAreaService() {
        return interestAreaService;
    }

    public void setInterestAreaService(String interestAreaService) {
        this.interestAreaService = interestAreaService;
    }

    public double getHoursAvailableService() {
        return hoursAvailableService;
    }

    public void setHoursAvailableService(double hoursAvailableService) {
        this.hoursAvailableService = hoursAvailableService;
    }

    public String getInterestAreaActivity() {
        return interestAreaActivity;
    }

    public void setInterestAreaActivity(String interestAreaActivity) {
        this.interestAreaActivity = interestAreaActivity;
    }

    public double getHoursAvailableActivity() {
        return hoursAvailableActivity;
    }

    public void setHoursAvailableActivity(double hoursAvailableActivity) {
        this.hoursAvailableActivity = hoursAvailableActivity;
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

    public boolean isFormFilled() {
        return formFilled;
    }

    public void setFormFilled(boolean formFilled) {
        this.formFilled = formFilled;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
