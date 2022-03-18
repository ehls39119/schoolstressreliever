package com.example.schoolstressreliever.justin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolstressreliever.R;
import com.example.schoolstressreliever.vico.ServiceOverviewActivity;
import com.google.firebase.auth.FirebaseUser;

public class ECHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echome);
    }

//    public void goToActivity(View v)
//    {
//        Intent startPage = new Intent(this, ECHomeActivity.class);
//        startActivity(startPage);
//    }

    public void goToService(View v)
    {
        Intent startPage = new Intent(this, ServiceOverviewActivity.class);
        startActivity(startPage);
    }
}