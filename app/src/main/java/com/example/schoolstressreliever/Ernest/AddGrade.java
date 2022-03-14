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
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddGrade extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;

    public EditText obtained;
    public EditText name;

    public EditText total;

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


        recView = (RecyclerView) findViewById(R.id.recycler2ID);
        GradeAdapter myAdapter = new GradeAdapter(grades, this);
        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addGrade(View v){
        String n = name.getText().toString();
        String ob = obtained.getText().toString();
        String to = total.getText().toString();

        double obtainedMarks = Integer.parseInt(ob);
        double totalMarks = Integer.parseInt(to);
        double exact = (double) Math.round((obtainedMarks/totalMarks*100) * 1000d) / 1000d; //3dp

//        db.collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onEvent(@Nullable QuerySnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                if (documentSnapshot != null && !documentSnapshot.getDocuments().isEmpty()) {
//                    List<DocumentSnapshot> documents = documentSnapshot.getDocuments();
//                    for (DocumentSnapshot value : documents) {
//                        Student info = value.toObject(Student.class);
//                        ArrayList<Map<String, Integer>> myHLBoundaries = info.getHLBoundaries();
//                        ArrayList<Map<String, Integer>> mySlBoundaries = info.getMySlBoundaries();
//
//                        if (info.getMyName().equals(n)){
//
//
//
//
//                        }
//
//
//                    }
//
//                }
//            }
//        });
//











    }


}