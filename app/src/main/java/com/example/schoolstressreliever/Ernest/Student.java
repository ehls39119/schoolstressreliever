package com.example.schoolstressreliever.Ernest;

import java.util.ArrayList;
import java.util.Map;

public class Student {

    Integer myProgressGrade;
    Double myProgressPercent;
    Integer myTranscriptGrade;

    private ArrayList<Map<String, Integer>> myHLBoundaries;
    private ArrayList<Map<String, Integer>> mySlBoundaries;
    public ArrayList<String> subjectNameList;
    public String myName;

    public Student(ArrayList<Map<String, Integer>> hlMap, ArrayList<Map<String, Integer>> slMap, String userName){
        myHLBoundaries = hlMap;
        mySlBoundaries = slMap;
        myName = userName;
        myProgressGrade = 0;
        myProgressPercent = 0.0;
        myTranscriptGrade = 0;
    }

    public int getProgressGrade() {
        return myProgressGrade ;
    }

    public int getTranscriptGrade() {
        return myTranscriptGrade ;
    }

    public ArrayList<String> getSubjects(){
        for (Map<String, Integer>  x: myHLBoundaries){
            Map.Entry<String,Integer> entry = x.entrySet().iterator().next();
            String key = entry.getKey();
            subjectNameList.add(key);
        }
        for (Map<String, Integer>  x: mySlBoundaries){
            Map.Entry<String,Integer> entry = x.entrySet().iterator().next();
            String key = entry.getKey();
            subjectNameList.add(key);
        }

        return subjectNameList;
    }


}
