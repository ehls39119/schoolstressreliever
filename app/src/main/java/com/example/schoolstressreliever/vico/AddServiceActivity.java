package com.example.schoolstressreliever.vico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.schoolstressreliever.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddServiceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText nameField;
    private EditText emailField;
    private ArrayList<String> interestAreaList = new ArrayList<>();
    private EditText descriptionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        nameField = findViewById(R.id.editTextName);
        emailField = findViewById(R.id.editTextEmail);
        descriptionField = findViewById(R.id.editTextPrice);

        Spinner spinner = findViewById(R.id.activityTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.serviceTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.activityHoursSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.hours, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

    }

    public void addService(View v)
    {
        String nameString = nameField.getText().toString();
        String emailString = emailField.getText().toString();
        String descriptionString = descriptionField.getText().toString();

        Spinner spinner = (Spinner)findViewById(R.id.activityTypeSpinner);
        String interestArea = spinner.getSelectedItem().toString();

        Spinner spinner2 = (Spinner)findViewById(R.id.activityHoursSpinner);
        String hours = spinner2.getSelectedItem().toString();

        ArrayList<String> emptyList = new ArrayList<>();

        interestAreaList.add(interestArea);

        boolean parametersFilled = false;
        boolean validEmail = false;

        if(!nameString.isEmpty() && !emailString.isEmpty() && !descriptionString.isEmpty() &&
                !interestArea.isEmpty() && !hours.isEmpty())
        {
            parametersFilled = true;
        }
        else
            {
                Toast.makeText(this, "Parameters Missing", Toast.LENGTH_SHORT).show();
            }

        if(emailString.contains("cis.edu.hk"))
        {
            validEmail = true;
        }
        else
            {
                Toast.makeText(this, "Email Invalid", Toast.LENGTH_SHORT).show();
            }

        if(parametersFilled && validEmail)
            {
                Service currService = new Service(nameString, emailString, interestArea,
                        emptyList, descriptionString, hours);

                System.out.println("got service");

                firestore.collection("everything").document("all services")
                        .collection("services").document(nameString).set(currService);

                System.out.println("added service");

                Toast.makeText(this, "Service Added", Toast.LENGTH_SHORT).show();
            }


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView,
                               View view,
                               int i,
                               long l) {

        String text = adapterView.getItemAtPosition(i).toString();
//        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}