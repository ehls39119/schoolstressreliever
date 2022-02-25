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
        descriptionField = findViewById(R.id.editTextDescription);

        Spinner spinner = findViewById(R.id.serviceTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.serviceTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void addService(View v)
    {
        String nameString = nameField.getText().toString();
        String emailString = emailField.getText().toString();
        String descriptionString = descriptionField.getText().toString();

        Spinner spinner = (Spinner)findViewById(R.id.serviceTypeSpinner);
        String interestArea = spinner.getSelectedItem().toString();

        ArrayList<String> emptyList = new ArrayList<>();

        interestAreaList.add(interestArea);

        if(!nameString.isEmpty() || !emailString.isEmpty() || !descriptionString.isEmpty() ||
                !interestArea.isEmpty())
        {
            Toast.makeText(this, "Parameters Missing", Toast.LENGTH_SHORT).show();
        }
        if(!emailString.contains("cis.edu.hk"))
        {
            Toast.makeText(this, "Email Invalid", Toast.LENGTH_SHORT).show();
        }
        else
            {
                Service currService = new Service(nameString, emailString, interestArea,
                        emptyList, descriptionString);

                firestore.collection("everything").document("all services")
                        .collection("services").document(nameString).set(currService);

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