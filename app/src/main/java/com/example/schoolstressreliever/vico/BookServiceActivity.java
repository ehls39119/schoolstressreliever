package com.example.schoolstressreliever.vico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolstressreliever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookServiceActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_service);

        firestore = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        mUser = mAuth.getCurrentUser();

        Intent intent = getIntent();
        String currService = intent.getExtras().getString("currService");

        TextView nameView = (TextView)findViewById(R.id.meetingNameTextView);
        TextView interestView = (TextView)findViewById(R.id.dateTextView);
        TextView hoursView = (TextView)findViewById(R.id.timeTextView);
        TextView emailView = (TextView)findViewById(R.id.emailTextView);
        TextView descriptionView = (TextView)findViewById(R.id.descriptionView);

        firestore.collection("Services").get()
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

        firestore.collection("Services").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            List<DocumentSnapshot> ds = task.getResult().getDocuments();

                            Intent intent = getIntent();
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

                                boolean inService = false;

                                if(thisService.equals(currService))
                                {
                                    for(String i : allParticipants)
                                    {
                                        if(i.equals(mUser.getEmail()))
                                        {
                                            inService = true;
                                        }
                                    }

                                    if(!inService)
                                    {
                                        allParticipants.add(mUser.getEmail());

                                        firestore.collection("Services")
                                                .document(currService)
                                                .update("participants", allParticipants);
                                    }
                                }
                            }
                        }
                    }
                });

        Toast.makeText(this, "Service Signed Up", Toast.LENGTH_SHORT).show();
    }

}