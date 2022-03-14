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
import java.util.Map;

public class GradeOverview extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;

    RecyclerView recView;
    ArrayList<Map<String, Map<String, Double>>> grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_overview);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        recView = (RecyclerView) findViewById(R.id.recycler2ID);
        GradeAdapter myAdapter = new GradeAdapter(grades, this);
        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
    }



    public void getAndPopulateData(View v) {
//        db.collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onEvent(@Nullable QuerySnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                if (documentSnapshot != null && !documentSnapshot.getDocuments().isEmpty()) {
//                    List<DocumentSnapshot> documents = documentSnapshot.getDocuments();
//
//                    for (DocumentSnapshot value : documents) {
//                        Student info = value.toObject(Student.class);
//                        studentInfo.add(info);
//                    }
//
//                    SubjectRecyclerViewAdapter recAdapter = (SubjectRecyclerViewAdapter) recView.getAdapter();
//                    assert recAdapter != null;
//                    recAdapter.setVehiclesData(studentInfo);
//                    recAdapter.notifyDataSetChanged();
//                }
//            }
//        });
    }


}