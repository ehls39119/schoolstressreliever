package com.example.schoolstressreliever.justin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolstressreliever.R;
import com.example.schoolstressreliever.kevin.ViewInfoActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class defaultActivity extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        mUser = mAuth.getCurrentUser();


    }

    public void goToAcademics(View v)
    {
        Intent startPage = new Intent(this, ViewInfoActivity.class);
        startActivity(startPage);
    }

    public void goToExtracurriculars(View v)
    {

        System.out.println(mUser.getEmail());


        firestore.collection("Users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            System.out.println("reach here");

                            List<DocumentSnapshot> ds = task.getResult().getDocuments();

                            System.out.println("reach here2");

                            for(DocumentSnapshot doc : ds)
                            {
                                Map<String, Object> docData = doc.getData();

                                String currUser = (String) docData.get("email");

                                System.out.println(currUser);

                                if(currUser.equals(mUser.getEmail()))
                                {
                                    System.out.println("reach here4");

                                    if(!(boolean)docData.get("formFilled"))
                                    {
                                        System.out.println("went to form");
                                        goToForm(mUser);
                                    }
                                    else
                                    {
                                        goToHome(mUser);
                                    }

                                }
                            }
                        }
                    }
                });
    }

    public void goToForm(FirebaseUser user)
    {
        Intent startPage = new Intent(this, ECInfoActivity.class);
        startActivity(startPage);
    }

    public void goToHome(FirebaseUser user)
    {
        Intent startPage = new Intent(this, ECHomeActivity.class);
        startActivity(startPage);
    }

}