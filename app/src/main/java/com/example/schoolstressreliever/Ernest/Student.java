package com.example.schoolstressreliever.Ernest;

import java.util.ArrayList;
import java.util.Map;

public class Student {

    Integer myProgressGrade;
    Double myProgressPercent;
    Integer myTranscriptGrade;

    private ArrayList<Map<String, Integer>> myHLBoundaries;
    private ArrayList<Map<String, Integer>> mySlBoundaries;
    public String myName;

    public Student(ArrayList<Map<String, Integer>> hlMap, ArrayList<Map<String, Integer>> slMap, String userName){
        myHLBoundaries = hlMap;
        mySlBoundaries = slMap;
        myName = userName;
        myProgressGrade = null;
        myProgressPercent = null;
        myTranscriptGrade = null;
    }
}
