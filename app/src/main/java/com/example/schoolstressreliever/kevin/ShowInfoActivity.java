package com.example.schoolstressreliever.kevin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.schoolstressreliever.Ernest.Student;
import com.example.schoolstressreliever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowInfoActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore data;
    ArrayList<String> recyclerInfoList;
    private InfoAdapter adapter;
    RecyclerView subjectRecycler;

    private ArrayList<String> idData;
    private ArrayList<String> nameData;
    private ArrayList<String> emailData;
    private ArrayList<String> yearData;
    private ArrayList<String> passwordData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);
        subjectRecycler = findViewById(R.id.recyclerView);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        data = FirebaseFirestore.getInstance();
        idData = new ArrayList<>();
        nameData = new ArrayList<>();
        emailData = new ArrayList<>();
        yearData = new ArrayList<>();
        passwordData = new ArrayList<>();
        idData.add(mUser.getUid());
        nameData.add(mUser.getDisplayName());
        emailData.add(mUser.getEmail());

        adapter = new InfoAdapter(idData, nameData, emailData, yearData, passwordData);
        subjectRecycler.setAdapter(adapter);
        subjectRecycler.setLayoutManager(new LinearLayoutManager(this));

        getAndPopulateData();
    }
    public void getAndPopulateData(){

    }
}
