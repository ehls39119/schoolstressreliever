package com.example.schoolstressreliever.vico;

import java.util.ArrayList;

public class ServiceMeeting {

    private String date;
    private String service;
    private String time;
    private String description;
    private String email;

    public ServiceMeeting(String date,
                          String service,
                          String time,
                          String description,
                          String email) {

        this.date = date;
        this.service = service;
        this.time = time;
        this.description = description;
        this.email = email;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
