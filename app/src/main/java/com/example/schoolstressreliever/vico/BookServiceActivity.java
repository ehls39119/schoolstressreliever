package com.example.schoolstressreliever.vico;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolstressreliever.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookServiceActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_service);

        Intent intent = getIntent();
        String currUser = intent.getExtras().getString("currUser");
        String currService = intent.getExtras().getString("currService");

        TextView nameView = (TextView)findViewById(R.id.serviceNameTextView);
        TextView interestView = (TextView)findViewById(R.id.interestAreaTextView);
        TextView hoursView = (TextView)findViewById(R.id.hoursTextView);
        TextView emailView = (TextView)findViewById(R.id.emailTextView);
        TextView descriptionView = (TextView)findViewById(R.id.descriptionTextView);

        firestore = FirebaseFirestore.getInstance();

        firestore.collection("everything").document("all services")
                .collection("services").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            List<DocumentSnapshot> ds = task.getResult().getDocuments();

                            for(DocumentSnapshot doc : ds)
                            {
                                Map<String, Object> docData = doc.getData();

                                String thisName = (String) docData.get("name");

                                if (thisName.equals(currService))
                                {
                                    String thisInterest = (String) docData.get("intrestArea");
                                    String thisHours = (String) docData.get("hours");
                                    String thisEmail = (String) docData.get("email");
                                    String thisDescription = (String) docData.get("description");

                                    nameView.setText(thisName);
                                    interestView.setText(thisInterest);
                                    hoursView.setText("Hours demanded: " + thisHours);
                                    emailView.setText("Person to contact for more information: \n"
                                            + thisEmail);
                                    descriptionView.setText(thisDescription);

                                }
                            }
                        }
                    }
                });
    }

    public void addService(View v)
    {
        firestore = FirebaseFirestore.getInstance();

        firestore.collection("everything").document("all services")
                .collection("services").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            List<DocumentSnapshot> ds = task.getResult().getDocuments();

                            Intent intent = getIntent();
                            String myUserEmail = intent.getExtras().getString("currUser");
                            String currService = intent.getExtras().getString("currService");

                            for(DocumentSnapshot doc : ds)
                            {
                                Map<String, Object> docData = doc.getData();

                                String thisService = (String) docData.get("name");

                                ArrayList<String> allParticipants = new ArrayList<>();

                                for (java.lang.String currID : (ArrayList<String>) docData
                                        .get("participants"))
                                {
                                    String IDString = currID.toString();

                                    allParticipants.add(IDString);
                                }

                                if(thisService.equals(currService))
                                {
                                    allParticipants.add(myUserEmail);

                                    firestore.collection("everything")
                                            .document("all services")
                                            .collection("services")
                                            .document(currService)
                                            .update("participants", allParticipants);

                                }
                            }
                        }
                    }
                });

        Toast.makeText(this, "Service Signed Up", Toast.LENGTH_SHORT).show();
    }

}