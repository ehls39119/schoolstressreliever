package com.example.schoolstressreliever.justin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolstressreliever.R;

public class ActivityMeetingOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_overview);
    }

    public void goToSending(View v)
    {
        Intent newIntent = new Intent(this, privActivityMeetingRequest.class);
        startActivity(newIntent);
    }

    public void goToReceiving(View v)
    {
        Intent thisIntent = new Intent(this, receivePrivActivityRequest.class);
        startActivity(thisIntent);
    }
}