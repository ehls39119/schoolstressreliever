package com.example.schoolstressreliever.vico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolstressreliever.R;

public class ServiceOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_overview);
    }

    public void goToAddService(View v){
        Intent newIntent = new Intent(this, AddServiceActivity.class);
        startActivity(newIntent);
    }
}