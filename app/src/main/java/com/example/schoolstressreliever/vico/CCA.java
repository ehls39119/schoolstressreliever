package com.example.schoolstressreliever.vico;

import java.util.ArrayList;

public class CCA {

    private String name;
    private String teacher;
    private ArrayList<String> intrestArea;;
    private double price;
    private int capacity;
    private ArrayList<String> participants;
    private String info;

    public CCA(String name,
               String teacher,
               ArrayList<String> intrestArea,
               double price,
               ArrayList<String> participants,
               String info) {

        this.name = name;
        this.teacher = teacher;
        this.intrestArea = intrestArea;
        this.price = price;
        this.participants = participants;
        this.info = info;

    }

}
