package com.example.schoolstressreliever.kevin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolstressreliever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class SignUpActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore fireStore;

    private String userID;
    private EditText userName;
    private EditText userEmail;
    private EditText userYearLevel;
    private EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        fireStore = FirebaseFirestore.getInstance();

        userID = UUID.randomUUID().toString();
        userName = findViewById(R.id.UserName);
        userEmail = findViewById(R.id.UserEmail);
        userYearLevel = findViewById(R.id.UserYearLevel);
        userPassword = findViewById(R.id.UserPassword);
    }

    public void Signup(View v)
    {
        System.out.println("Sign up!");
        String userNameInput = userName.getText().toString();
        String userEmailInput = userEmail.getText().toString();
        String userYearLevelInput = userYearLevel.getText().toString();
        String userPasswordInput = userPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(userEmailInput,userPasswordInput).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Log.d("Sign up.", "SignUpWithEmail: success!");
                    FirebaseUser currUser = mAuth.getCurrentUser();
                    updateUI(currUser);
                    Toast.makeText(getApplicationContext(), "Successfully signed up by new user! Welcome" + userNameInput, Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.w("Sign up.", "SignUpWithEmail: failed.");
                    updateUI(null);
                    Toast.makeText(getApplicationContext(), "Signing up by new user failed, maybe you have already signed up with this account", Toast.LENGTH_SHORT).show();
                }
            }
        });

        User currentUser = new User(userID,userNameInput,userEmailInput,userYearLevelInput,userPasswordInput);
        fireStore.collection("User").document(userID).set(currentUser);
    }

    public void updateUI(FirebaseUser currUser)
    {
        if(currUser != null){
            System.out.println("It worked!");
        }
    }
}