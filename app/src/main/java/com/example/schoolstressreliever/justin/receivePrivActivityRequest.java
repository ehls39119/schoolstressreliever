package com.example.schoolstressreliever.justin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.schoolstressreliever.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class receivePrivActivityRequest extends AppCompatActivity {

    int day;
    int month;
    int year;
    String amOrPm;
    String prematureDay;
    String prematureMonth;
    String prematureYear;
    String time;
    String category;
    String owner;
    String prematureCapacity;
    private FirebaseUser currUser;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_priv_request);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currUser = mAuth.getCurrentUser();


        Intent intent = getIntent();

        if(intent != null){
            prematureDay = intent.getStringExtra("Day");
            prematureMonth = intent.getStringExtra("Month");
            prematureYear = intent.getStringExtra("Year");
            amOrPm = intent.getStringExtra("AM or PM");
            time = intent.getStringExtra("Time");
            category = intent.getStringExtra("Category");
            owner = intent.getStringExtra("Owner");
            prematureCapacity = intent.getStringExtra("Capacity");

            day = Integer.parseInt(prematureDay);
            month = Integer.parseInt(prematureMonth);
            year = Integer.parseInt(prematureYear);

            Calendar calendar = Calendar.getInstance();
            calendar.set(year,month,day);


            SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
            dateEditText.setText(format.format(calendar.getTime()));

        }

    }
}