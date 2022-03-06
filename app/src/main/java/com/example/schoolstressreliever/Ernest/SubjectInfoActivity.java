package com.example.schoolstressreliever.Ernest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


import android.os.Bundle;

import java.util.ArrayList;

public class SubjectInfoActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;

    RecyclerView recView2;
    ArrayList<Double> grades = new ArrayList<Double>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_info);
        recView2 = (RecyclerView) findViewById(R.id.recycler2ID);

        GradeRecyclerViewAdapter myAdapter = new GradeRecyclerViewAdapter(grades, this);
//        recView2.setAdapter(myAdapter);
//        recView2.setLayoutManager(new LinearLayoutManager(this));

    }


}