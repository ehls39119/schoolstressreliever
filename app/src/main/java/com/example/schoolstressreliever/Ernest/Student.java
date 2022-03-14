package com.example.schoolstressreliever.Ernest;

import java.util.ArrayList;
import java.util.Map;

public class Student {

    int myProgressGrade;
    Double myProgressPercent;
    int myTranscriptGrade;

    public ArrayList<Map<String, Integer>> myHLBoundaries;
    public ArrayList<Map<String, Integer>> mySlBoundaries;

    public ArrayList<String> subjectNameList;
    public String myName;

    public ArrayList<Map<String, Map<String, Double>>> gradeInfo;

//    [{"History": {"Progress": 7, "Transcript": 7, Grade}


    public Student(ArrayList<Map<String, Integer>> hlMap, ArrayList<Map<String, Integer>> slMap, String userName, ArrayList<Map<String, Map<String, Double>>> gradeInfo){
        myHLBoundaries = hlMap;
        mySlBoundaries = slMap;
        myName = userName;
        myProgressGrade = 0;
        myProgressPercent = 0.0;
        myTranscriptGrade = 0;
        subjectNameList = new ArrayList<String>();
        gradeInfo = new ArrayList<Map<String, Map<String, Double>>>();

    }

    public Student(){

    }

    public void updateGradeInfo(){

    }

    public ArrayList<Map<String, Map<String, Integer>>> defaultGradeInfo(){
        return null;
    }

    public int getProgressGrade() {
        return myProgressGrade ;
    }

    public int getTranscriptGrade() {
        return myTranscriptGrade;
    }

    public void updateList(){
        System.out.println("inside " + myHLBoundaries);
        System.out.println("inside " + mySlBoundaries);

        for (Map<String, Integer>  x: myHLBoundaries) {
            for (Map.Entry<String, Integer> entry : x.entrySet()) {
                String ke = entry.getKey();
                if (ke.length() > 4) {
                    System.out.println("hl ke "+ ke);
                    subjectNameList.add(ke);
                }
            }
        }

        for (Map<String, Integer>  y: mySlBoundaries) {
            for (Map.Entry<String, Integer> entry : y.entrySet()) {
                String ke = entry.getKey();
                if (ke.length() > 4) {
                    System.out.println("sl ke "+ ke);
                    subjectNameList.add(ke);
                }
            }
        }

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
