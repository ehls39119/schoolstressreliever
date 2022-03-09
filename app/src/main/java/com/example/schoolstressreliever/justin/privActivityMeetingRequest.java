//package com.example.schoolstressreliever.justin;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.Spinner;
//
//import com.example.schoolstressreliever.R;
//
//import java.util.Locale;
//
//public class privActivityMeetingRequest extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//
//    EditText categoryField;
//    EditText ownerField;
//    EditText capacityField;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_priv_meeting_request);
//        categoryField = findViewById(R.id.meetingCategory);
//        ownerField = findViewById(R.id.ownerName);
//        capacityField =findViewById(R.id.meetingCapacity);
//
//                DatePicker datePicker = (DatePicker) findViewById(R.id.calendar);
//        int mDay = datePicker.getDayOfMonth();
//        int mMonth = datePicker.getMonth() + 1;
//        int mYear = datePicker.getYear();
//
//        String categoryRequest = categoryField.getText().toString();
//        String ownerRequest = categoryField.getText().toString();
//        String capacityRequest = capacityField.getText().toString();
//
//        Spinner spinnerAMorPM = findViewById(R.id.amtothePM);
//        ArrayAdapter<CharSequence> adapterAMorPM = ArrayAdapter.createFromResource(this,
//                R.array.meetingTimes, android.R.layout.simple_spinner_item);
//        adapterAMorPM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerAMorPM.setAdapter(adapterAMorPM);
//        spinnerAMorPM.setOnItemSelectedListener(this);
//        String amPmStatus = spinnerAMorPM.getSelectedItem().toString();
//
//        Spinner spinnerTime= findViewById(R.id.spinnerTime);
//        ArrayAdapter<CharSequence> adapterTime = ArrayAdapter.createFromResource(this,
//                R.array.meetingTimes, android.R.layout.simple_spinner_item);
//        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerAMorPM.setAdapter(adapterTime);
//        spinnerAMorPM.setOnItemSelectedListener(this);
//        String timeStatus = spinnerTime.getSelectedItem().toString();
//
//        Intent intent = new Intent(privActivityMeetingRequest.this,receivePrivActivityRequest.class);
//        // Put the date values to the intent
//        intent.putExtra("Day",mDay);
//        intent.putExtra("Month",mMonth);
//        intent.putExtra("Year",mYear);
//        intent.putExtra("AM or PM",amPmStatus);
//        intent.putExtra("Time", timeStatus);
//        intent.putExtra("Category", categoryRequest);
//        intent.putExtra("Owner", ownerRequest);
//        intent.putExtra("Capacity", capacityRequest);
//        startActivity(intent);
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
//    {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
//}