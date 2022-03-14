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

    String day;
    String month;
    String year;
    int mDay;
    int mMonth;
    int mYear;
    String amOrPm;
    String time;
    String category;
    String owner;
    String capacity;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser mUser;


    EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_priv_request);



        Intent intent = getIntent();
        if(intent != null){
            day = intent.getStringExtra("Day");
            month = intent.getStringExtra("Month");
            year = intent.getStringExtra("Year");
            amOrPm = intent.getStringExtra("AM or PM");
            time = intent.getStringExtra("Time");
            category = intent.getStringExtra("Category");
            owner = intent.getStringExtra("Owner");
            capacity = intent.getStringExtra("Capacity");
            Calendar calendar = Calendar.getInstance();

            mDay = Integer.parseInt(day);
            mMonth = Integer.parseInt(month);
            mYear = Integer.parseInt(year);
            calendar.set(mYear,mMonth,mDay);


            SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
            dateEditText.setText(format.format(calendar.getTime()));


            /*
            1. Make everything show in a RecyclerViewer
            2. Format calendar
            3. Check firebase data i.e are you part of the category
            4. Allow rejection (similar to open close mechanism)
            5. Create a way to communicate
            */

        }

    }
}