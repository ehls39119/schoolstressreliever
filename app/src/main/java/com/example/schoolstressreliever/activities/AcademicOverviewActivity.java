package com.example.schoolstressreliever.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoolstressreliever.R;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class AcademicOverviewActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;

    RecyclerView recView;
    ArrayList<Integer> vehiclesList = new ArrayList<Integer>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_overview);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        recView = (RecyclerView) findViewById(R.id.recycler1ID);
//        SubjectRecyclerViewAdapter myAdapter = new SubjectRecyclerViewAdapter(vehiclesList, this);
        SubjectRecyclerViewAdapter myAdapter = new SubjectRecyclerViewAdapter();

        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void getAndPopulateData(View v) {


//        db.collection("Vehicle").addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @SuppressLint("NotifyDataSetChanged")
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//
//                        if (documentSnapshot != null && !documentSnapshot.getDocuments().isEmpty()) {
//                            List<DocumentSnapshot> documents = documentSnapshot.getDocuments();
//                            for (DocumentSnapshot value : documents) {
//                                Vehicle newV = value.toObject(Vehicle.class);
//                                vehiclesList.add(newV);
//                            }
//
//                            SubjectRecyclerViewAdapter recAdapter = (SubjectRecyclerViewAdapter) recView.getAdapter();
//                            assert recAdapter != null;
//                            recAdapter.setVehiclesData(vehiclesList);
//                            recAdapter.notifyDataSetChanged();
//
//                        }
//                    }
//                });
    }


    public void goToAddSubjects(View v){
        if (mUser != null){
            Intent intent = new Intent(this, ChooseSubjectActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, NavigationActivity.class);
            startActivity(intent);
        }
    }


}