package com.example.schoolstressreliever.Ernest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoolstressreliever.R;


import com.example.schoolstressreliever.kevin.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class SubjectOverview extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;

    RecyclerView recView;
    ArrayList<User> studentInfo = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_overview);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        studentInfo = new ArrayList<User>();

        recView = (RecyclerView) findViewById(R.id.recycler1ID);
        SubjectAdapter myAdapter = new SubjectAdapter(studentInfo, this);
        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void getAndPopulateData(View v) {
        studentInfo.clear();
        db.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null && !documentSnapshot.getDocuments().isEmpty()) {
                    List<DocumentSnapshot> documents = documentSnapshot.getDocuments();

                    for (DocumentSnapshot value : documents) {
                        User info = value.toObject(User.class);
                        if (info.getGradeInfo()!=null){
                            studentInfo.add(info);
                        }
                    }

                    for (User z: studentInfo){
                        System.out.println("has grade info " + z.getName() + "\n");
                    }

                    SubjectAdapter recAdapter = (SubjectAdapter) recView.getAdapter();
                    assert recAdapter != null;
                    recAdapter.setSubjectData(studentInfo);
                    recAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void goToAdd(View v){
        Intent z = new Intent(this, AddSubject.class);
        startActivity(z);
    }

}