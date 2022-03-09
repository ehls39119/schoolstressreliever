package com.example.schoolstressreliever.Ernest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class AddGrade extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;

    RecyclerView recView;
    ArrayList<Double> grades = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_grade);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        recView = (RecyclerView) findViewById(R.id.recycler2ID);
        GradeAdapter myAdapter = new GradeAdapter(grades, this);
        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addGrade(View v){
        System.out.println("bruh");

    }


}