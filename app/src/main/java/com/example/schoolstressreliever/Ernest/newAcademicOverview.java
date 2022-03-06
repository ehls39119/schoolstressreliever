package com.example.schoolstressreliever.Ernest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

import java.util.ArrayList;
import java.util.List;


public class newAcademicOverview extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;

    RecyclerView recView;
    ArrayList<Student> studentInfo = new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_academic_overview);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        recView = (RecyclerView) findViewById(R.id.recycler1ID);
        SubjectRecyclerViewAdapter myAdapter = new SubjectRecyclerViewAdapter(studentInfo, this);
        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void getAndPopulateData(View v) {
        db.collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null && !documentSnapshot.getDocuments().isEmpty()) {
                    List<DocumentSnapshot> documents = documentSnapshot.getDocuments();

                    for (DocumentSnapshot value : documents) {
                        Student info = value.toObject(Student.class);
                        studentInfo.add(info);
                    }

                    SubjectRecyclerViewAdapter recAdapter = (SubjectRecyclerViewAdapter) recView.getAdapter();
                    assert recAdapter != null;
                    recAdapter.setVehiclesData(studentInfo);
                    recAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void goToAcademicOverview (View v){
        Intent z = new Intent(this, AddAcademicActivity.class);
        startActivity(z);
    }

}