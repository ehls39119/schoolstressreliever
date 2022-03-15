package com.example.schoolstressreliever.kevin;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String ID;
    private String name;
    private String email;
    private String password;
    private String yearLevel;
    private double hoursAvailable;
    private boolean formFilled;
    private String interestArea;
    private String hours;
    public String Subjects;
    private ArrayList<String> userSubjectList;

    public User(String ID, String name, String email, String yearLevel, String password, double hoursAvailable, boolean formFilled, String interestArea, String Subjects) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.yearLevel = yearLevel;
        this.hoursAvailable = hoursAvailable;
        this.formFilled = formFilled;
        this.interestArea = interestArea;
        this.Subjects = Subjects;
    }

    public User(String uid, String displayName, String email, String yearLevel, String password, double hoursAvailable, String arts) {
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

    public boolean isFormFilled() {
        return formFilled;
    }

    public void setFormFilled(boolean formFilled) {
        this.formFilled = formFilled;
    }

    public String getInterestArea() {
        return interestArea;
    }

    public void setInterestArea(String interestArea) {
        this.interestArea = interestArea;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getSubjects() {
        return Subjects;
    }

    public void setSubjects(String subjects) {
        Subjects = subjects;
    }
}
