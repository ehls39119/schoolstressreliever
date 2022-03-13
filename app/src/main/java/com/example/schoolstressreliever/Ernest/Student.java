package com.example.schoolstressreliever.Ernest;

import java.util.ArrayList;
import java.util.Map;

public class Student {

    Integer myProgressGrade;
    Double myProgressPercent;
    Integer myTranscriptGrade;

    public ArrayList<Map<String, Integer>> myHLBoundaries;
    public ArrayList<Map<String, Integer>> mySlBoundaries;
    public ArrayList<String> subjectNameList;
    public String myName;

    public Student(ArrayList<Map<String, Integer>> hlMap, ArrayList<Map<String, Integer>> slMap, String userName){
        myHLBoundaries = hlMap;
        mySlBoundaries = slMap;
        myName = userName;
        myProgressGrade = 0;
        myProgressPercent = 0.0;
        myTranscriptGrade = 0;
        subjectNameList = new ArrayList<String>();
    }

    public int getProgressGrade() {
        return myProgressGrade ;
    }

    public int getTranscriptGrade() {
        return myTranscriptGrade ;
    }

    public ArrayList<String> getSubjects(){
        subjectNameList.add("sub1");
        subjectNameList.add("sub2");
        subjectNameList.add("sub3");
        subjectNameList.add("sub4");
        subjectNameList.add("sub5");
        subjectNameList.add("sub6");

//        for (Map<String, Integer>  x: myHLBoundaries){
//            Map.Entry<String,Integer> entry = x.entrySet().iterator().next();
//            String key = entry.getKey();
//            subjectNameList.add(key);
//        }
//        for (Map<String, Integer>  x: mySlBoundaries){
//            Map.Entry<String,Integer> entry = x.entrySet().iterator().next();
//            String key = entry.getKey();
//            subjectNameList.add(key);
//        }

        return subjectNameList;
    }

    public ArrayList<Map<String, Integer>> getHLBoundaries(){
        return myHLBoundaries;
    }

    public ArrayList<Map<String, Integer>> getMySlBoundaries(){
        return mySlBoundaries;
    }

}
