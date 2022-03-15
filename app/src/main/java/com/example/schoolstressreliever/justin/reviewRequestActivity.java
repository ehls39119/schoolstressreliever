package com.example.schoolstressreliever.justin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolstressreliever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class reviewRequestActivity extends AppCompatActivity {
    String capacity;
    int capacityConverted;
    FirebaseFirestore firestore;
    Intent intent = getIntent();
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    boolean open;
    private ArrayList<String> attendees = new ArrayList<>();
    EditText name;
    String attendee;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        name = findViewById(R.id.name);
        attendee = name.getText().toString();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_request);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }
    public void acceptMeeting(View v)
    {
        capacity = intent.getStringExtra("Capacity");
        capacityConverted = Integer.parseInt(capacity);

        if(capacityConverted >0)
        {
            firestore.collection("Meetings").document("Private").get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful())
                            {
                                open = task.getResult().toObject(privateMeeting.class).isStatus();
                                if(open == true)
                                {
                                    capacityConverted = capacityConverted - 1;
                                    firestore.collection("Meetings").document("Private").update("capacity", capacityConverted);
                                    attendees.add(attendee);
                                    for(String attendee: attendees)
                                    {
                                        firestore.collection("Meetings").document("Private").update("attendees", FieldValue.arrayUnion(attendee));
                                        Toast.makeText(reviewRequestActivity.this, "You're in!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
        }
        else
        {
            Toast.makeText(reviewRequestActivity.this, "You weren't invited!", Toast.LENGTH_SHORT).show();
            Intent intentPortal = new Intent(getApplicationContext(), receivePrivActivityRequest.class);
            startActivity(intent);
            finish();

        }


    }
}