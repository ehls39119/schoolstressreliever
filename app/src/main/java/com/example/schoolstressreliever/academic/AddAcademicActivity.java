package com.example.schoolstressreliever.academic;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.schoolstressreliever.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddAcademicActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseFirestore db;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    private Spinner hlSubject1;
    private Spinner hlSubject2;
    private Spinner hlSubject3;

    private Spinner slSubject1;
    private Spinner slSubject2;
    private Spinner slSubject3;

//
//    private EditText originLocation;
//    private EditText carModel;
//    private EditText capacityAvailable;
//    private EditText vehicleRating;
//    private EditText vehicleOwner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_academic);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mUser = mAuth.getCurrentUser();
//
//        originLocation = findViewById(R.id.originLocationID);
//        carModel = findViewById(R.id.vehicleModelID);
//        vehicleRating = findViewById(R.id.vehicleCapacityID);
//        capacityAvailable = findViewById(R.id.ratingID);
//        vehicleOwner = findViewById(R.id.vehicleOwnerID);
//


        //////spinner
//        hlSubject1 = findViewById(R.id.HLSpinner1);
//        hlSubject2 = findViewById(R.id.HLSpinner2);
//        hlSubject3 = findViewById(R.id.HLSpinner3);
//
//        slSubject1 = findViewById(R.id.SLSpinner1);
//        slSubject2 = findViewById(R.id.SLSpinner2);
//        slSubject3 = findViewById(R.id.SLSpinner3);

        ArrayAdapter<CharSequence> hlSubjectAdapter = ArrayAdapter.createFromResource(this,
                R.array.HigherLevelSubjects, android.R.layout.simple_spinner_item);
        hlSubjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hlSubject1.setAdapter(hlSubjectAdapter);
        hlSubject1.setOnItemSelectedListener(this);

        hlSubject2.setAdapter(hlSubjectAdapter);
        hlSubject2.setOnItemSelectedListener(this);

        hlSubject3.setAdapter(hlSubjectAdapter);
        hlSubject3.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> sLSubjectAdapter = ArrayAdapter.createFromResource(this,
                R.array.HigherLevelSubjects, android.R.layout.simple_spinner_item);
        hlSubjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        slSubject1.setAdapter(sLSubjectAdapter);
        slSubject1.setOnItemSelectedListener(this);

        slSubject2.setAdapter(sLSubjectAdapter);
        slSubject2.setOnItemSelectedListener(this);

        slSubject3.setAdapter(sLSubjectAdapter);
        slSubject3.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean formValid(String str1, String str2, int str3, String str4, String str5, String str6) {
        return str1 != null && str2 != null && str3  > 0 && str4 != null && str5 != null && str6!=null;
    }

    public void addNewVehicle(View v) {
//        String ownerString = vehicleOwner.getText().toString();
//        String locationString = originLocation.getText().toString();
//        String modelString = carModel.getText().toString();
//        String txtSpinner2 = sVehicleType.getSelectedItem().toString();
//        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//        int rating = Integer.parseInt((vehicleRating.getText().toString()));
//        int fakeCapacity = Integer.parseInt((capacityAvailable).getText().toString());
//        ArrayList<String> creator = new ArrayList<>();
//        creator.add(userID);
//
//
////        if ((formValid(locationString, modelString, rating, ratingString, ownerString, vehicleType))) {
//        if (txtSpinner2.equals("Car")) {
//            txtSpinner2 = "Car";
//            Vehicle newCar = new Car(locationString, modelString, creator, rating, fakeCapacity, ownerString, txtSpinner2);
//            db.collection("Vehicle").document(ownerString).set(newCar);
//
////            db.collection("Vehicle").add(newCar).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
////                @Override
////                public void onComplete(@NonNull Task<DocumentReference> task) {
////                    if (task.isSuccessful()) {
////                        Toast.makeText(AddVehicleActivity.this, "NEW CAR ADDED", Toast.LENGTH_SHORT).show();
////
////                    } else {
////                        Toast.makeText(AddVehicleActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();
////                    }
////
////                }
////            });
//        }
//
//        if (txtSpinner2.equals("ElectricCar")) {
//            txtSpinner2 = "ElectricCar";
//            Vehicle newElectric = new ElectricCar(locationString, modelString, creator, rating, fakeCapacity, ownerString, txtSpinner2);
//            db.collection("Vehicle").document(ownerString).set(newElectric);
////                @Override
////                public void onComplete(@NonNull Task<DocumentReference> task) {
////                    if (task.isSuccessful()) {
////                        Toast.makeText(AddVehicleActivity.this, "NEW ELECTRIC", Toast.LENGTH_SHORT).show();
////
////                    } else {
////                        Toast.makeText(AddVehicleActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();
////
////                    }
////
////                }
//
//        }

        Intent intent = new Intent(this, SubjectInfoActivity.class);
        startActivity(intent);

    }
}







