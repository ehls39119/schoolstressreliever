package com.example.schoolstressreliever.justin;

import java.util.ArrayList;

public class privateMeeting
{
    String category;
    String owner;
    String capacity;
    boolean status;
    ArrayList<String> attendees = new ArrayList<>();


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public privateMeeting(String category, String owner, String capacity, boolean status, ArrayList<String> attendees)
    {
        this.category = category;
        this.owner = owner;
        this.capacity = capacity;
        this.status = status;
        this.attendees = attendees;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
