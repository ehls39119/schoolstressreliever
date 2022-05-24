package com.example.schoolstressreliever.Ernest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.schoolstressreliever.justin.ECHomeActivity;
import com.example.schoolstressreliever.justin.ECInfoActivity;
import com.example.schoolstressreliever.kevin.AuthActivity;
import com.example.schoolstressreliever.vico.ServiceOverviewActivity;
import com.example.schoolstressreliever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Map;

public class Navigation extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
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
                                System.out.println("user email:" + mUser.getEmail());

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