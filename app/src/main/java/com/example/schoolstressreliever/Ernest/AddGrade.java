package com.example.schoolstressreliever.Ernest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddGrade extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;

    public EditText obtained;
    public EditText name;
    public EditText subject;
    public EditText total;
    public EditText taskName;


    RecyclerView recView;
    ArrayList<Map<String, Map<String, Double>>> grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_grade);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        obtained = findViewById(R.id.obtainedMarksID);
        total = findViewById(R.id.totalMarksID);
        name = findViewById(R.id.name2ID);
        subject = findViewById(R.id.checkSubjectID);
        taskName = findViewById(R.id.taskNameID);

    }

    public void addGrade(View v){
        String nameCheck = name.getText().toString();
        String subjectCheck = subject.getText().toString();
//        System.out.println("got name " +  nameCheck);
        String task = taskName.getText().toString();
//        System.out.println("sub " +  subjectCheck);
//        System.out.println("sub " +  subjectCheck);

        String ob = obtained.getText().toString();
        String to = total.getText().toString();

//        System.out.println("obtained " +  ob);
//        System.out.println("total " +  to);


        db.collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override

            public void onEvent(@Nullable QuerySnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {


                if (documentSnapshot != null && !documentSnapshot.getDocuments().isEmpty()) {
                    List<DocumentSnapshot> documents = documentSnapshot.getDocuments();
                    for (DocumentSnapshot value : documents) {
                        Student info = value.toObject(Student.class);
                        if (info.getMyName().equals(nameCheck)) {
                            ArrayList<Map<String, Map<String, Double>>> allInfo = info.getGradeInfo();

                            ArrayList<Map<String, Integer>> myHLBoundaries = info.getMyHLBoundaries();
                            ArrayList<Map<String, Integer>> mySlBoundaries = info.getMySlBoundaries();

                            double obtainedMarks = Double.parseDouble(ob);
                            double totalMarks = Double.parseDouble(to);
                            double exact = (double) Math.round((obtainedMarks / totalMarks * 100) * 100d) / 100d; //3dp


                            Map<String, Map<String, Double>> bigMap = new HashMap<>();
                            for (int i = 0; i < allInfo.size(); i++) {
                                if (allInfo.get(i).containsKey(subjectCheck)) {

                                    bigMap = allInfo.get(i);


                                    for (Map.Entry<String, Map<String, Double>> entry : bigMap.entrySet()) {
                                        if (entry.getKey().equals("Grades")) {

                                            Map<String, Double> allGrades = new HashMap<>();
                                            allGrades = entry.getValue();
                                            if (allGrades.containsKey("Test")) {
                                                allGrades.remove("Test", 11.0);
                                            }

                                            allGrades.put(task, exact);

                                        }
                                        Student newStudent = new Student(myHLBoundaries, mySlBoundaries, nameCheck, allInfo);
                                        db.collection("Students").document(nameCheck).set(newStudent);
                                        break;
                                    }
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                        break;
                    }
                }
            }

        });

    }

    public void calculateIt (View v) {
        String nameCheck = name.getText().toString();
        String subjectCheck = subject.getText().toString();


        db.collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override

            public void onEvent(@Nullable QuerySnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot != null && !documentSnapshot.getDocuments().isEmpty()) {
                    List<DocumentSnapshot> documents = documentSnapshot.getDocuments();
                    for (DocumentSnapshot value : documents) {
                        Student info = value.toObject(Student.class);
                        if (info.getMyName().equals(nameCheck)) {
                            ArrayList<Map<String, Map<String, Double>>> allInfo = info.getGradeInfo();

                            ArrayList<Map<String, Integer>> myHLBoundaries = info.getMyHLBoundaries();
                            ArrayList<Map<String, Integer>> mySlBoundaries = info.getMySlBoundaries();
                            System.out.println("here");

//                            double obtainedMarks = Double.parseDouble(ob);
//                            double totalMarks = Double.parseDouble(to);
//                            double exact = (double) Math.round((obtainedMarks / totalMarks * 100) * 100d) / 100d; //3dp
//
                            Map<String, Map<String, Double>> bigMap = new HashMap<>();
                            for (int i = 0; i < allInfo.size(); i++) {
                                if (allInfo.get(i).containsKey(subjectCheck)) {

                                    bigMap = allInfo.get(i);
                                    System.out.println("big map should have new val" + bigMap);

                                    for (Map.Entry<String, Map<String, Double>> entry : bigMap.entrySet()) {
                                        System.out.println("ent" + entry);
                                        if (entry.getKey().equals(subjectCheck)) {
                                            Map<String, Double> progressGradeMap = new HashMap<>();
                                            progressGradeMap = entry.getValue();
                                            System.out.println("chosen map " + progressGradeMap);

                                            for (Map.Entry<String, Double> ent : progressGradeMap.entrySet()) {
                                                if (ent.getKey().equals("Progress")) {
                                                    progressGradeMap.remove("Progress", 0.0);
                                                    System.out.println("Removed Progress Map");
                                                }
                                            }

                                            System.out.println("after removing " + bigMap);
                                            for (Map.Entry<String, Map<String, Double>> newEntry : bigMap.entrySet()) {
                                                System.out.println("big entry " + newEntry);

                                                if (newEntry.getKey().equals("Grades")) {
//                                                            System.out.println("should work");
                                                    Map<String, Double> allGrades = new HashMap<>();
                                                    allGrades = newEntry.getValue();
                                                    System.out.println("all grades " + allGrades);


                                                    double rolling_grade = 0;
                                                    double n = allGrades.size();

                                                    for (Map.Entry<String, Double> gradeEntry : allGrades.entrySet()) {
                                                        rolling_grade += gradeEntry.getValue();
                                                    }
                                                    rolling_grade /= n;
                                                    System.out.println("rolling " + rolling_grade);

                                                    System.out.println("hll" + myHLBoundaries);
                                                    System.out.println("sll" + mySlBoundaries);


                                                    for (int a = 0; a < myHLBoundaries.size(); a++) {
                                                        if (myHLBoundaries.get(a).containsKey(subjectCheck)) {
                                                            ArrayList<Double> subjectBoundaries = new ArrayList<>();

                                                            Map<String, Integer> z = myHLBoundaries.get(a);
                                                            for (Map.Entry<String, Integer> boundary : z.entrySet()) {
                                                                String grade = boundary.getKey();
                                                                int intPercent = boundary.getValue();
                                                                Double doubleBoundary = Double.valueOf(intPercent);
                                                                subjectBoundaries.add(doubleBoundary);
                                                            }

                                                            Collections.sort(subjectBoundaries);
                                                            Collections.reverse(subjectBoundaries);
                                                            subjectBoundaries.remove(3);

                                                            System.out.println("list of boundaries to check in order " + subjectBoundaries);

                                                            if (rolling_grade >= subjectBoundaries.get(0)) {
                                                                progressGradeMap.put("Progress", 7.0);
                                                            } else if ((rolling_grade <= subjectBoundaries.get(0)) && (rolling_grade >= (subjectBoundaries.get(1)))) {
                                                                progressGradeMap.put("Progress", 6.0);
                                                            } else if (rolling_grade <= subjectBoundaries.get(1) && (rolling_grade >= subjectBoundaries.get(2))) {
                                                                progressGradeMap.put("Progress", 5.0);
                                                            }
                                                            else if (rolling_grade <= subjectBoundaries.get(2)) {
                                                                progressGradeMap.put("Progress", 4.0);
                                                            }

                                                            Student newStudent = new Student(myHLBoundaries, mySlBoundaries, nameCheck, allInfo);
                                                            db.collection("Students").document(nameCheck).set(newStudent);
                                                            break;
                                                        }
                                                        break;

                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            break;
                                        }
                                        break;
                                    }
                                    break;
                                }
                            }


                        }

                    }
                }
            }});
        Intent z = new Intent(this, GradeOverview.class);
        startActivity(z);
    }
}

