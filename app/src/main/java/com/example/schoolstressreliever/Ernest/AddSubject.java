package com.example.schoolstressreliever.Ernest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.schoolstressreliever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddSubject extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
        setContentView(R.layout.add_subject);

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

    public ArrayList<ArrayList<Map<String, Integer>>> getSubjects() throws FileNotFoundException {

        try{
            System.out.println("in path");
            ArrayList<ArrayList<Map<String, Integer>>> combined = new ArrayList<>();

            ArrayList<Map<String, Integer>> hlSubjects = new ArrayList<Map<String, Integer>>();
            ArrayList<Map<String, Integer>> sLSubjects = new ArrayList<Map<String, Integer>>();
            Map<String, Integer> currentSubject = new HashMap<>();

            System.out.println("checkpoint1");


            File file1 = new File("/Users/Ernest/Desktop/hl.txt");
            File file2 = new File("/Users/Ernest/Desktop/sl.txt");

            System.out.println("checkpoint2");

            Scanner sc1 = new Scanner(file1);
            Scanner sc2 = new Scanner(file2);


            while (sc1.hasNextLine()) {

                String[] dataHl = sc1.nextLine().split(":");
                currentSubject.put(dataHl[0], null);
                currentSubject.put("7", Integer.parseInt(dataHl[1]));
                currentSubject.put("6", Integer.parseInt(dataHl[2]));
                currentSubject.put("5", Integer.parseInt(dataHl[3]));
//                currentSubject.put("4", Integer.parseInt(dataHl[4]));
//                currentSubject.put("3", Integer.parseInt(dataHl[5]));
//                currentSubject.put("2", Integer.parseInt(dataHl[6]));
//                currentSubject.put("1", Integer.parseInt(dataHl[7]));
                hlSubjects.add(currentSubject);
            }

            while (sc2.hasNextLine()){
                String[] dataSl = sc2.nextLine().split(":");
                currentSubject.put(dataSl[0], null);
                currentSubject.put("7", Integer.parseInt(dataSl[1]));
                currentSubject.put("6", Integer.parseInt(dataSl[2]));
                currentSubject.put("5", Integer.parseInt(dataSl[3]));
//                currentSubject.put("4", Integer.parseInt(dataSl[4]));
//                currentSubject.put("3", Integer.parseInt(dataSl[5]));
//                currentSubject.put("2", Integer.parseInt(dataSl[6]));
//                currentSubject.put("1", Integer.parseInt(dataSl[7]));
                sLSubjects.add(currentSubject);
            }
            combined.add(hlSubjects);
            combined.add(sLSubjects);

            System.out.println("combined" + combined);
            return combined;


        }
        catch(FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("NO FILE");
        }
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

    public void addSubject(View v) throws FileNotFoundException {
        try {
            String hl1 = hlSubject1.getSelectedItem().toString();
            String hl2 = hlSubject2.getSelectedItem().toString();
            String hl3 = hlSubject3.getSelectedItem().toString();

            String sl1 = slSubject1.getSelectedItem().toString();
            String sl2 = slSubject2.getSelectedItem().toString();
            String sl3 = slSubject3.getSelectedItem().toString();

            String name = userName.getText().toString();

            if ((formValid(hl1, hl2, hl3, sl1, sl2, sl3))) {
                ArrayList<ArrayList<Map<String, Integer>>> subjectData = getSubjects();

                ArrayList<Map<String, Integer>> hlMap = new ArrayList<Map<String, Integer>>();
                ArrayList<Map<String, Integer>> slMap = new ArrayList<Map<String, Integer>>();

                ArrayList<Map<String, Integer>> data1 = subjectData.get(0);
                ArrayList<Map<String, Integer>> data2 = subjectData.get(1);

                for (int hlArray = 0; hlArray < data1.size(); hlArray++) {
                    //e.g [{x:1, y:2}]
                    Map<String, Integer> test1 = (HashMap<String, Integer>) data1.get(hlArray);
                    //get the map from the arraylist
                    for (String key : test1.keySet()) {
                        if (hl1.equals(key) || (hl2.equals(key) || (hl3.equals(key)))) {
                            hlMap.add(test1);
                        }
                    }
                }

                for (int slArray = 0; slArray < data2.size(); slArray++) {
                    //e.g [{x:1, y:2}]
                    Map<String, Integer> test2 = (HashMap<String, Integer>) data1.get(slArray);
                    //get the map from the arraylist
                    for (String key : test2.keySet()) {
                        if (sl1.equals(key) || (sl2.equals(key) || (sl3.equals(key)))) {
                            slMap.add(test2);
                        }
                    }
                }

                Student newStudent = new Student(hlMap, slMap, name);
                db.collection("Students").add(newStudent).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddSubject.this, "Subjects Added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddSubject.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

            Intent intent = new Intent(this, SubjectOverview.class);
            startActivity(intent);
        }
        catch(FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("add class failed");
        }
    }
}







