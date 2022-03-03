package com.example.schoolstressreliever;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolstressreliever.academic.AcademicOverviewActivity;
import com.example.schoolstressreliever.kevin.AuthActivity;
import com.example.schoolstressreliever.vico.ServiceOverviewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

//    public void goToAuth(View v){
//        Intent newIntent = new Intent(this, AuthActivity.class);
//        startActivity(newIntent);
//    }

    public void goToServiceOverview(View v){
        Intent newIntent = new Intent(this, ServiceOverviewActivity.class);
        startActivity(newIntent);
    }

    public void goToAcademicOverview (View v){
        Intent newIntent = new Intent(this, AcademicOverviewActivity.class);
        startActivity(newIntent);
    }



}