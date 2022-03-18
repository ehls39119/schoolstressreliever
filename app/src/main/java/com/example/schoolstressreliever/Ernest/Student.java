package com.example.schoolstressreliever.Ernest;

import java.util.ArrayList;
import java.util.Map;

public class Student {
    private ArrayList<Map<String, Integer>> myHLBoundaries;
    private ArrayList<Map<String, Integer>> mySlBoundaries;
    public ArrayList<String> subjectNameList;


    public ArrayList<Map<String, Map<String, Double>>> gradeInfo;

    public String myName;



    public Student(ArrayList<Map<String, Integer>> hlMap, ArrayList<Map<String, Integer>> slMap, String userName, ArrayList<Map<String, Map<String, Double>>> bruhPlsWork){
        myHLBoundaries = hlMap;
        mySlBoundaries = slMap;
        myName = userName;
        subjectNameList = new ArrayList<String>();
        gradeInfo = bruhPlsWork;

    }

    public Student(){

    }

    public ArrayList<Map<String, Map<String, Double>>> getGradeInfo(){
        return gradeInfo;
    }



    public String getMyName(){
        return myName;
    }

    public ArrayList<String> getSubjects(){
        return subjectNameList;
    }

    public ArrayList<Map<String, Integer>> getHLBoundaries(){
        return myHLBoundaries;
    }

    public ArrayList<Map<String, Integer>> getMySlBoundaries(){
        return mySlBoundaries;
    }

}
