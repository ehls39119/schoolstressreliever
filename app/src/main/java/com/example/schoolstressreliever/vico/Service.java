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
}
