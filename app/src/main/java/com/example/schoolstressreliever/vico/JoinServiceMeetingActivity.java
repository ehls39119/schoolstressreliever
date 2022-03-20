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

public class JoinServiceMeetingActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    String currServiceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_service_meeting);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        mUser = mAuth.getCurrentUser();

        Intent intent = getIntent();
        String currServiceMeeting = intent.getExtras().getString("currServiceMeeting");

        TextView nameView = (TextView)findViewById(R.id.meetingNameTextView);
        TextView dateView = (TextView)findViewById(R.id.dateTextView);
        TextView hoursView = (TextView)findViewById(R.id.timeTextView);
        TextView emailView = (TextView)findViewById(R.id.emailTextView);
        TextView descriptionView = (TextView)findViewById(R.id.descriptionView);

        firestore.collection("Service Meetings").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            List<DocumentSnapshot> ds = task.getResult().getDocuments();

                            for(DocumentSnapshot doc : ds)
                            {

                                System.out.println(currServiceMeeting);

                                Map<String, Object> docData = doc.getData();

                                currServiceName = (String) docData.get("service");

                                if (currServiceName.equals(currServiceMeeting))
                                {
                                    String thisDate = (String) docData.get("date");
                                    String thisEmail = (String) docData.get("email");
                                    String thisDescription = (String) docData.get("description");
                                    String thisTime = (String) docData.get("time");

                                    nameView.setText(currServiceName);
                                    dateView.setText(thisDate);
                                    hoursView.setText(thisTime);
                                    emailView.setText("Person to contact for more information: \n"
                                            + thisEmail);
                                    descriptionView.setText(thisDescription);

                                }
                            }
                        }
                    }
                });
    }

    boolean inService = false;

    public void joinServiceMeeting(View v)
    {
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

                                String thisService = (String) docData.get("name");

                                ArrayList<String> allParticipants = new ArrayList<>();

                                for (java.lang.String currID : (ArrayList<String>) docData
                                        .get("participants"))
                                {
                                    String IDString = currID.toString();

                                    allParticipants.add(IDString);
                                }

                                Intent intent = getIntent();
                                String currServiceMeeting = intent.getExtras().getString("currServiceMeeting");

                                if(currServiceMeeting.equals(thisService))
                                {
                                    for(String i : allParticipants)
                                    {
                                        if(i.equals(mUser.getEmail()))
                                        {
                                            inService = true;
                                        }
                                    }

                                    if(inService)
                                    {
                                        firestore.collection("Service Meetings").get()
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

                                                                String fianlServiceCheck = (String) docData.get("service");

                                                                ArrayList<String> allParticipants = new ArrayList<>();

                                                                for (java.lang.String currID : (ArrayList<String>) docData
                                                                        .get("participants"))
                                                                {
                                                                    String IDString = currID;

                                                                    allParticipants.add(IDString);
                                                                }

                                                                allParticipants.add(mUser.getEmail());

                                                                if(currServiceMeeting.equals(fianlServiceCheck))
                                                                {
                                                                    firestore.collection("Service Meetings")
                                                                            .document(currServiceMeeting)
                                                                            .update("participants", allParticipants);
                                                                }
                                                            }
                                                        }
                                                    }
                                                });
                                    }
                                }
                            }
                        }
                    }
                });

        if(!inService)
        {
            Toast.makeText(this, "You are not in this service", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Service Signed Up", Toast.LENGTH_SHORT).show();
        }
    }

}