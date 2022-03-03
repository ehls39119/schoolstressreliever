package com.example.schoolstressreliever.academic;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.schoolstressreliever.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
    private EditText userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_academic);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mUser = mAuth.getCurrentUser();

        userName = findViewById(R.id.userNameID);

        //////spinner
        hlSubject1 = findViewById(R.id.HLSpinner1);
        hlSubject2 = findViewById(R.id.HLSpinner2);
        hlSubject3 = findViewById(R.id.HLSpinner3);

        slSubject1 = findViewById(R.id.SLSpinner1);
        slSubject2 = findViewById(R.id.SLSpinner2);
        slSubject3 = findViewById(R.id.SLSpinner3);

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

    public ArrayList<ArrayList<Map<String, Integer>>> getSubjects() {
        ArrayList<ArrayList<Map<String, Integer>>> combined = new ArrayList<>();

        ArrayList<Map<String, Integer>> hlSubjects = new ArrayList<Map<String, Integer>>();
        ArrayList<Map<String, Integer>> sLSubjects = new ArrayList<Map<String, Integer>>();
        Map<String, Integer> currentSubject = new HashMap<>();

        File file1 = new File("hl.txt");
        File file2 = new File("sl.txt");
        Scanner sc1 = new Scanner(file1);
        Scanner sc2 = new Scanner(file2);
        while (sc1.hasNextLine() && sc2.hasNextLine()) {
            String[] dataHl = sc1.nextLine().split(":");
            String[] dataSl = sc2.nextLine().split(":");
            currentSubject.put(dataHl[0], null);
            currentSubject.put("7", Integer.parseInt(dataHl[1]));
            currentSubject.put("6", Integer.parseInt(dataHl[2]));
            currentSubject.put("5", Integer.parseInt(dataHl[3]));
            currentSubject.put("4", Integer.parseInt(dataHl[4]));
            currentSubject.put("3", Integer.parseInt(dataHl[5]));
            currentSubject.put("2", Integer.parseInt(dataHl[6]));
            currentSubject.put("1", Integer.parseInt(dataHl[7]));
            hlSubjects.add(currentSubject);

            currentSubject.put(dataSl[0], null);
            currentSubject.put("7", Integer.parseInt(dataSl[1]));
            currentSubject.put("6", Integer.parseInt(dataSl[2]));
            currentSubject.put("5", Integer.parseInt(dataHl[3]));
            currentSubject.put("4", Integer.parseInt(dataHl[4]));
            currentSubject.put("3", Integer.parseInt(dataHl[5]));
            currentSubject.put("2", Integer.parseInt(dataHl[6]));
            currentSubject.put("1", Integer.parseInt(dataHl[7]));
            sLSubjects.add(currentSubject);
        }
        combined.add(hlSubjects);
        combined.add(sLSubjects);
        return combined;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public boolean formValid(String str1, String str2, String str3, String str4, String str5, String str6) {
        return str1 != null && str2 != null && str3 != null && str4 != null && str5 != null && str6 != null;
    }

    public void filterSubjects() {

    }

    public void addSubject(View v) {
        String hl1 = hlSubject1.getSelectedItem().toString();
        String hl2 = hlSubject2.getSelectedItem().toString();
        String hl3 = hlSubject3.getSelectedItem().toString();
        String sl1 = slSubject1.getSelectedItem().toString();
        String sl2 = slSubject2.getSelectedItem().toString();
        String sl3 = slSubject3.getSelectedItem().toString();
        String name = userName.getText().toString();

        if ((formValid(hl1, hl2, hl3, sl1, sl2, sl3))) {
            ArrayList<ArrayList<Map<String, Integer>>> subjectData = getSubjects();

            Map<String, Integer> hlMap = new HashMap<String, Integer>();
            Map<String, Integer> slMap = new HashMap<String, Integer>();

            ArrayList<Map<String, Integer>> data1 = subjectData.get(0);
            ArrayList<Map<String, Integer>> data2 = subjectData.get(1);

            for (int a = 0; a < data1.size(); a++) {
                //e.g [{x:1, y:2}]
                Map<String, Integer> test1 = (HashMap<String, Integer>) data1.get(a);
                //get the map from the arraylist
                for (String key : test1.keySet()) {


                }
            }
        }
    }
}



//
//            Vehicle newCar = new Car(locationString, modelString, creator, rating, fakeCapacity, ownerString, txtSpinner2);
//            db.collection("Vehicle").document(ownerString).set(newCar);
//            db.collection("Vehicle").add(newCar).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentReference> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(AddVehicleActivity.this, "Subjects Added", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(AddVehicleActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            });
//        }
////

////        }
////        Intent intent = new Intent(this, SubjectInfoActivity.class);
////        startActivity(intent);
//
//}







