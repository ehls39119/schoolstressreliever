package com.example.schoolstressreliever.Ernest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.schoolstressreliever.kevin.AuthActivity;
import com.example.schoolstressreliever.vico.ServiceOverviewActivity;
import com.example.schoolstressreliever.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Navigation extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    public void signOut(View v) {
        mAuth.signOut();
        Log.d("SIGN OUT", "SUCCESS");
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();

    }

    public void navigateToAcademic(View v) {
        if (mUser != null){
            Intent intent = new Intent(this, SubjectOverview.class);
            startActivity(intent);
            finish();
        }

    }

    public void navigateToService(View v) {
        if (mUser != null){
            Intent intent = new Intent(this, ServiceOverviewActivity.class);
            startActivity(intent);
            finish();
        }

    }




}