package com.example.schoolstressreliever.kevin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.schoolstressreliever.Ernest.Subject;
import com.example.schoolstressreliever.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ShowInfoActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore data;
    private InfoRecyclerViewAdapterActivity adapter;
    private ArrayList<Subject> subjectList;
    RecyclerView subjectRecycler;
    public static Subject subjectS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);
    }
}