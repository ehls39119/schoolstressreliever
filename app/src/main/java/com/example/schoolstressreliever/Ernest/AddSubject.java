package com.example.schoolstressreliever.Ernest;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;

import com.example.schoolstressreliever.R;
import com.example.schoolstressreliever.kevin.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.io.InputStream;
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
        String email = mUser.getEmail();

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

    public void addSubjects(View v) {

        try{
            System.out.println("in path");
//            ArrayList<ArrayList<Map<String, Integer>>> combined = new ArrayList<>();
            ArrayList<Map<String, Integer>> hlSubjects = new ArrayList<>();
            ArrayList<Map<String, Integer>> sLSubjects = new ArrayList<>();


//            System.out.println("checkpoint1");

            AssetManager am = this.getAssets();
            InputStream file1 = am.open("hl.txt");
            InputStream file2 = am.open("sl.txt");

            System.out.println("checkpoint2");

            Scanner sc1 = new Scanner(file1);
            Scanner sc2 = new Scanner(file2);


            while (sc1.hasNextLine()) {
//                hlMap.clear();
                ArrayList<String> hlAL = new ArrayList<>();
                String[] dataHl = sc1.nextLine().split(":");

                for (String x:dataHl){
                    hlAL.add(x);
                }

                System.out.println(hlAL);

                Map<String, Integer> hlMap = new HashMap<>();
                hlMap.put(hlAL.get(0), 0);
                hlMap.put("7", Integer.parseInt(hlAL.get(1)));
                hlMap.put("6", Integer.parseInt(hlAL.get(2)));
                hlMap.put("5", Integer.parseInt(hlAL.get(3)));
                System.out.println("current map "+ hlMap);
                hlSubjects.add(hlMap);
            }

            System.out.println(hlSubjects);

            while (sc2.hasNextLine()){
                ArrayList<String> slAL = new ArrayList<String>();
                String[] dataSl = sc2.nextLine().split(":");
                for (String x:dataSl){
                    slAL.add(x);
                }
//                System.out.println(slAL);

                Map<String, Integer> slMap = new HashMap<>();
                slMap.put(slAL.get(0), 0);
                slMap.put("7", Integer.parseInt(slAL.get(1)));
                slMap.put("6", Integer.parseInt(slAL.get(2)));
                slMap.put("5", Integer.parseInt(slAL.get(3)));
//                System.out.println("current map "+ hlMap);
                sLSubjects.add(slMap);
            }

//            System.out.println(sLSubjects);


            sc1.close();
            sc2.close();

            String hl1 = hlSubject1.getSelectedItem().toString();
            String hl2 = hlSubject2.getSelectedItem().toString();
            String hl3 = hlSubject3.getSelectedItem().toString();

            String sl1 = slSubject1.getSelectedItem().toString();
            String sl2 = slSubject2.getSelectedItem().toString();
            String sl3 = slSubject3.getSelectedItem().toString();

            System.out.println(hl1 + " " + hl2 + " " + hl3 + " " + sl1 + " " + sl2 + " " + sl3);

            String name = userName.getText().toString();
            System.out.println(name);

            if ((formValid(hl1, hl2, hl3, sl1, sl2, sl3))) {
                ArrayList<Map<String, Integer>> myHL = new ArrayList<>();
                ArrayList<Map<String, Integer>> mySL = new ArrayList<>();

                for (int hlArray = 0; hlArray < hlSubjects.size(); hlArray++) {
                    //e.g [{x:1, y:2}]
                    Map<String, Integer> test1 = hlSubjects.get(hlArray);
                    //get the map from the arraylist
                    for (String key : test1.keySet()) {
                        if (hl1.equals(key) || (hl2.equals(key) || (hl3.equals(key)))) {
                            myHL.add(test1);
                        }
                    }
                }

                for (int slArray = 0; slArray < sLSubjects.size(); slArray++) {
                    Map<String, Integer> test2 = sLSubjects.get(slArray);
                    for (String key : test2.keySet()) {
                        if (sl1.equals(key) || (sl2.equals(key) || (sl3.equals(key)))) {
                            mySL.add(test2);
                        }
                    }
                }
//
//                System.out.println("HL MAP: " + myHL);
//                System.out.println("SL MAP: " + mySL);

                //                [
//                {History= {Progress = null, Transcript = Null}, Grades={test=99, test2=43}},
//                {Physics= {Progress = null, Transcript = Null}, Grades={test=53, test2=20}}
//                ]


                ArrayList<String> subjectNameList = new ArrayList<String>();
                subjectNameList.add(hl1);
                subjectNameList.add(hl2);
                subjectNameList.add(hl3);
                subjectNameList.add(sl1);
                subjectNameList.add(sl2);
                subjectNameList.add(sl3);

                ArrayList<Map<String, Map<String, Double>>> gradeInfo = new ArrayList<Map<String, Map<String, Double>>>();


                for (String x: subjectNameList){
                    Map<String, Double> studentGrade = new HashMap<>();
                    Map<String, Double> allGrades = new HashMap<>();
                    studentGrade.put("Progress", 0.0);
                    studentGrade.put("Transcript", 0.0);
                    allGrades.put("Test", 11.0);
                    Map<String, Map<String, Double>> z = new HashMap<>();
                    z.put(x, studentGrade);
                    z.put("Grades", allGrades);
                    gradeInfo.add(z);
//                    System.out.println("grade info " + gradeInfo);
                }

                System.out.println("Final grade info " + gradeInfo);

                User newStudent = new User(myHL, mySL, name, gradeInfo);

                System.out.println("1" +newStudent.getGradeInfo());
                System.out.println("2" +newStudent.getName());
                System.out.println("3" +newStudent.getMyHLBoundaries());
                System.out.println("4" +newStudent.getMySlBoundaries());

                db.collection("Users").document(mUser.getEmail()).set(newStudent);

            }

            Intent intent = new Intent(this, SubjectOverview.class);
            startActivity(intent);
        }
        catch(Exception err) {
            err.printStackTrace();
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


}







