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

import java.util.ArrayList;
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

    RecyclerView recView;
    ArrayList<Map<String, Map<String, Double>>> grades;
    int count=0;



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





    }

    public void addGrade(View v){
        String nameCheck = name.getText().toString();
        String subjectCheck = subject.getText().toString();
        System.out.println("got name " +  nameCheck);

        System.out.println("sub " +  subjectCheck);
        String ob = obtained.getText().toString();
        String to = total.getText().toString();

        System.out.println("obtained " +  ob);
        System.out.println("total " +  to);


        db.collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override

            public void onEvent(@Nullable QuerySnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                count++;
                String updatedCount = String.valueOf(count);

                if (documentSnapshot != null && !documentSnapshot.getDocuments().isEmpty()) {
                    List<DocumentSnapshot> documents = documentSnapshot.getDocuments();
                    for (DocumentSnapshot value : documents) {
                        Student info = value.toObject(Student.class);
                        if (info.getMyName().equals(nameCheck)){
                            ArrayList<Map<String, Map<String, Double>>> allInfo = info.getGradeInfo();
                            ArrayList<Map<String, Integer>> myHLBoundaries = info.getHLBoundaries();
                            ArrayList<Map<String, Integer>> mySlBoundaries = info.getMySlBoundaries();

                            double obtainedMarks = Double.parseDouble(ob);
                            double totalMarks = Double.parseDouble(to);
                            double exact = (double) Math.round((obtainedMarks/totalMarks*100) * 100d) / 100d; //3dp


                            Map<String, Map<String, Double>> bigMap = new HashMap<>();
                            for (int i=0; i<allInfo.size(); i++) {
                                if (allInfo.get(i).containsKey(subjectCheck)) {
                                    bigMap = allInfo.get(i);
                                    for (Map.Entry<String, Map<String, Double>> entry : bigMap.entrySet()) {
                                        if (entry.getKey().equals("Grades")) {
                                            Map<String, Double> allGrades = new HashMap<>();
                                            allGrades = entry.getValue();
                                            if (allGrades.containsKey("Test")) {
                                                allGrades.remove("Test", 11.0);
                                            }
                                            allGrades.put(updatedCount, exact);
                                            Student student = new Student(myHLBoundaries, mySlBoundaries, nameCheck, allInfo);
                                            db.collection("Students").document(nameCheck).set(student);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                        break;
                    }
                }
            }

        });
        Intent z = new Intent(this, GradeOverview.class);
        startActivity(z);
    }




}