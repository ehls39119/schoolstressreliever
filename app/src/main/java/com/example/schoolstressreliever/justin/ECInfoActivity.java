package com.example.schoolstressreliever.justin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.schoolstressreliever.R;
import com.example.schoolstressreliever.kevin.User;
import com.example.schoolstressreliever.vico.ServiceOverviewActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

public class ECInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Serializable {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser mUser;
    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;


    public String timeLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecinfo);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        // code for spinner - direct reference to spinner can be found in strings.xml in values folder//
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterServices = ArrayAdapter.createFromResource(this, R.array.serviceTypes, android.R.layout.simple_spinner_item);
        adapterServices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterServices);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapterHours = ArrayAdapter.createFromResource(this, R.array.hours, android.R.layout.simple_spinner_item);
        adapterHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterHours);
        spinner2.setOnItemSelectedListener(this);

        Spinner spinner3 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapterActOptions = ArrayAdapter.createFromResource(this, R.array.activityTypes, android.R.layout.simple_spinner_item);
        adapterHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapterActOptions);
        spinner3.setOnItemSelectedListener(this);

        Spinner spinner4 = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapterActHours = ArrayAdapter.createFromResource(this, R.array.actHours, android.R.layout.simple_spinner_item);
        adapterHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapterActHours);
        spinner4.setOnItemSelectedListener(this);


    }

    public void submitECInfo(View v)
    {
        Spinner serviceTime = (Spinner)findViewById(R.id.spinner1);
        String serviceTimeText = serviceTime.getSelectedItem().toString();
        Spinner serviceOptions = (Spinner)findViewById(R.id.spinner);
        String serviceText = serviceOptions.getSelectedItem().toString();
        double dummyServiceTime = 0;
        dummyServiceTime = Double.parseDouble(String.valueOf(serviceTimeText));
        Spinner activityOptions = (Spinner)findViewById(R.id.spinner2);
        String activityText = activityOptions.getSelectedItem().toString();
        Spinner activityTime = (Spinner)findViewById(R.id.spinner3);
        String activityTimeText = activityOptions.getSelectedItem().toString();
        System.out.println("Button Clicked");

//        this.ID = ID;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.yearLevel = yearLevel;
//        this.hoursAvailable = hoursAvailable;
//        this.areaOfInterest = areaOfInterest;
//
        if(dummyServiceTime == 0.5)
        {
            timeLeft = "0.5";

            if(serviceOptions.equals("Arts"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail(), "", "", 0.5, true, "Arts");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);

            }
            if(serviceOptions.equals("Sport"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  0.5,true,  "Sport");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Academic"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  0.5, true,  "Academic");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Fundraising"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  0.5, true, "Fundraising");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Social"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  0.5, true, "Social");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Sustainability"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  0.5, true,  "Sustainability");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);

                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }


        }

        if(dummyServiceTime == 1.0)
        {
            timeLeft = "1.0";
            if(serviceOptions.equals("Arts"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  1.0, true,  "Arts");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Sport"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  1.0, true, "Sport");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Academic"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  1.0, true, "Academic");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Fundraising"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  1.0, true, "Fundraising");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Social"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  1.0, true, "Social");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Sustainability"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  1.0, true,  "Sustainability");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }


        }

        if(dummyServiceTime == 2.0)
        {
            timeLeft = "2.0";

            if(serviceOptions.equals("Arts"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  2.0, true, "Arts");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Sport"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  2.0, true, "Sport");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Academic"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  2.0, true, "Academic");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Fundraising"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  2.0, true, "Fundraising");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Social"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  2.0, true,  "Social");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Sustainability"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  2.0, true, "Sustainability");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }

        }

        if(dummyServiceTime == 3.0)
        {
            timeLeft = "3.0";
            if(serviceOptions.equals("Arts"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  3.0, true, "Arts");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Sport"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  3.0, true, "Sport");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Academic"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  3.0, true,  "Academic");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Fundraising"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  3.0, true, "Fundraising");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Social"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  3.0, true, "Social");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }
            if(serviceOptions.equals("Sustainability"))
            {
                User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail() ,"","",  3.0, true, "Sustainability");
                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
                Toast.makeText(ECInfoActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServiceOverviewActivity.class);
                startActivity(intent);
                Intent i = new Intent(this, BookActivityActivity.class);
                i.putExtra("userObject", (Serializable) userObject);
                startActivity(i);
            }

        }





    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}