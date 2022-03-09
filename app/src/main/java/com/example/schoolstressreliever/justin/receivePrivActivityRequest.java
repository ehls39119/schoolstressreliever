package com.example.schoolstressreliever.justin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.schoolstressreliever.R;

public class receivePrivActivityRequest extends AppCompatActivity {

    int day;
    int month;
    int year;

    EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_priv_request);


        Intent intent = getIntent();

        intent.putExtra("Day",mDay);
        intent.putExtra("Month",mMonth);
        intent.putExtra("Year",mYear);
        intent.putExtra("AM or PM",amPmStatus);
        intent.putExtra("Time", timeStatus);
        intent.putExtra("Category", categoryRequest);
        intent.putExtra("Owner", ownerRequest);
        intent.putExtra("Capacity", capacityRequest);



        if(intent != null){
            day = intent.getIntExtra("Day",0);
            month = intent.getIntExtra("Month",0);
            year = intent.getIntExtra("Year",0);

            Calendar calendar = Calendar.getInstance();
            calendar.set(2013,5,23);


            SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
            dateEditText.setText(format.format(calendar.getTime()));

        }

    }
}