package com.example.schoolstressreliever.vico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolstressreliever.R;
import com.example.schoolstressreliever.vico.Service;
import com.example.schoolstressreliever.vico.ServiceMeeting;
import com.example.schoolstressreliever.vico.ServiceRecyclerViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AddServiceMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private String date;
    private EditText serviceField;
    private EditText timeField;
    private EditText descriptionField;

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_meeting);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        mUser = mAuth.getCurrentUser();

        serviceField = findViewById(R.id.editTextService);
        timeField = findViewById(R.id.editTextTime);
        descriptionField = findViewById(R.id.editTextDescription);

        findViewById(R.id.showCalendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

    }

    public void addServiceMeeting(View v)
    {
        String serviceString = serviceField.getText().toString();
        String timeString = timeField.getText().toString();
        String descriptionString = descriptionField.getText().toString();

        boolean parametersFilled = false;

        if(!serviceString.isEmpty() && !timeString.isEmpty() && !descriptionString.isEmpty())
        {
            parametersFilled = true;

            System.out.println("changed parameter");
        }
        else
        {
            Toast.makeText(this, "Parameters Missing", Toast.LENGTH_SHORT).show();
        }

        Boolean[] correctEmail = {false};

        System.out.println("curr email is" + mUser.getEmail());

        firestore.collection("Services").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            List<DocumentSnapshot> ds = task.getResult().getDocuments();

                            for(DocumentSnapshot doc : ds)
                            {
                                Map<String, Object> docData = doc.getData();

                                if (docData.get("name").equals(serviceString))
                                {
                                    if (docData.get("email").equals(mUser.getEmail()))
                                    {
                                        correctEmail[0] = true;

                                        System.out.println("status changed");

                                    }
                                }
                            }
                        }
                    }
                });

//        if(!correctEmail[0])
//        {
//            Toast.makeText(this, "You are not the leader of the service"
//                    , Toast.LENGTH_SHORT).show();
//        }

        if(parametersFilled)
        {
            ServiceMeeting currMeeting = new ServiceMeeting(date, serviceString, timeString
                    , descriptionString, mUser.getEmail());

            firestore.collection("Service Meetings").document(serviceString
                    + timeString).set(currMeeting);

            Toast.makeText(this, "Meeting Added", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDatePicker()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        date = month + "/" + dayOfMonth + "/" + year;
    }
}