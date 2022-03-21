package com.example.schoolstressreliever.kevin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.schoolstressreliever.MainActivity;
import com.example.schoolstressreliever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ViewInfoActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore data;
    ArrayList<User> recyclerInfoList;
    private InfoAdapter adapter;
    RecyclerView infoRecycler;

    private ArrayList<String> idData;
    private ArrayList<String> nameData;
    private ArrayList<String> emailData;
    private ArrayList<String> yearData;
    private ArrayList<String> passwordData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);
        infoRecycler = findViewById(R.id.recyclerView);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        data = FirebaseFirestore.getInstance();
        idData = new ArrayList<>();
        nameData = new ArrayList<>();
        emailData = new ArrayList<>();
        yearData = new ArrayList<>();
        passwordData = new ArrayList<>();

        adapter = new InfoAdapter(idData, nameData, emailData, yearData, passwordData);
        infoRecycler.setAdapter(adapter);
        infoRecycler.setLayoutManager(new LinearLayoutManager(this));

    }



    public void GoChangeInfo(View v){
        Intent startPage = new Intent(this, SignUpActivity.class);
        startActivity(startPage);
    }

    public void ConfirmAndGoMain(View v){
        Intent startPage = new Intent(this, MainActivity.class);
        startActivity(startPage);
    }
}
