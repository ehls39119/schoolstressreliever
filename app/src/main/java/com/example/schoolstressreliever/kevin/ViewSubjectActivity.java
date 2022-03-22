package com.example.schoolstressreliever.kevin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolstressreliever.Ernest.AddSubject;
import com.example.schoolstressreliever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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
        data.collection("User").whereEqualTo("myHLBoundaries", mUser.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot info : task.getResult().getDocuments()){

                }
            }
        });
    }

    public void Change(View v){
        Intent startPage = new Intent(this, AddSubject.class);
        startActivity(startPage);
    }


}