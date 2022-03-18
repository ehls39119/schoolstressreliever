package com.example.schoolstressreliever.justin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolstressreliever.R;
import com.example.schoolstressreliever.kevin.ShowInfoActivity;

public class defaultActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);


    }

    public void goToAcademics(View v)
    {
        Intent startPage = new Intent(this, ShowInfoActivity.class);
        startActivity(startPage);
    }

    public void goToExtracurriculars(View v)
    {
        Intent startPage = new Intent(this, ECHomeActivity.class);
        startActivity(startPage);

    }
}