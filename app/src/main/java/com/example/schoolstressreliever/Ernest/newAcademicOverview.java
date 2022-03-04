package com.example.schoolstressreliever.Ernest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoolstressreliever.R;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class newAcademicOverview extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;

    RecyclerView recView;
    ArrayList<Integer> vehiclesList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button b;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_academic_overview);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        recView = (RecyclerView) findViewById(R.id.recycler1ID);
        b = findViewById(R.id.newAddID);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("add button");

            }
        });

//        SubjectRecyclerViewAdapter myAdapter = new SubjectRecyclerViewAdapter(vehiclesList, this);
//        SubjectRecyclerViewAdapter myAdapter = new SubjectRecyclerViewAdapter();

//        recView.setAdapter(myAdapter);
//        recView.setLayoutManager(new LinearLayoutManager(this));
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

    public void goToAcademicOverview (View v){
        Intent z = new Intent(this, AddAcademicActivity.class);
        startActivity(z);
    }









}