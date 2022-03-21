package com.example.schoolstressreliever.kevin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.schoolstressreliever.Ernest.SubjectOverview;
import com.example.schoolstressreliever.Ernest.SubjectOverview;
import com.example.schoolstressreliever.R;
import com.example.schoolstressreliever.justin.defaultActivity;
import com.example.schoolstressreliever.vico.ServiceOverviewActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class AuthActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
    }

    public void LogIn(View v) {

        System.out.println("Log in!");
        String emailInput = email.getText().toString();
        String passwordInput = password.getText().toString();

        mAuth.signInWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.w("Log in.", "Successfully log in with email and password.");
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    SignIn(currentUser);
                    Toast.makeText(getApplicationContext(), "Correct email and password, welcome!", Toast.LENGTH_SHORT).show();

                } else {
                    Log.w("Log in.", "Log in with email and password failed.");
                    updateUI(null);
                    Toast.makeText(getApplicationContext(), "Incorrect email or password, please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void SignIn(FirebaseUser currentUser) {
        Intent startPage = new Intent(this, defaultActivity.class);
        startActivity(startPage);
    }

    public void SignUp(View v) {
        //go to SignUpActivity
        Intent startPage = new Intent(this, SignUpActivity.class);
        startActivity(startPage);
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            //go to MainActivity
            Intent startPage = new Intent(this, ShowInfoActivity.class);
            startPage.putExtra("currUser", (Serializable) currentUser);
            startActivity(startPage);
        }
    }

    public void goToServiceOverview(View v){
        Intent newIntent = new Intent(this, ServiceOverviewActivity.class);
        startActivity(newIntent);
    }

    public void goToAcademicOverview(View v){
        Intent newIntent = new Intent(this, SubjectOverview.class);
        startActivity(newIntent);
    }
}