package com.example.schoolstressreliever.kevin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.schoolstressreliever.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import javax.security.auth.Subject;

public class ViewSubjectActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore data;
    ArrayList<Subject> recyclerSubjectList;
    private SubjectViewAdapter adapter;
    RecyclerView subjectRecycler;

    private ArrayList<String> subjectData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subject);

        subjectRecycler = findViewById(R.id.SubjectRecyclerView);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        data = FirebaseFirestore.getInstance();
        subjectData = new ArrayList<>();

        adapter = new SubjectViewAdapter(subjectData);
        subjectRecycler.setAdapter(adapter);
        subjectRecycler.setLayoutManager(new LinearLayoutManager(this));

        getAndPopulateData();
    }

    public void getAndPopulateData(){

    }
}