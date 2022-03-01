package com.example.schoolstressreliever.kevin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.schoolstressreliever.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddSubjectActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore firestore;
    public ArrayList<String> subjectList = new ArrayList<>();
    Button select;
    TextView showText;

    CharSequence[] subjects = {"\nEnglish Lan & Lit SL\n", "\nEnglish Lan & Lit HL\n", "\nEnglish Lit SL\n", "\nEnglish Lit HL\n",
            "\nChinese A Lan & Lit SL\n", "\nChinese A Lan & Lit HL\n", "\nChinese A Lit SL\n", "\nChinese A Lit HL\n", "\nChinese B SL\n", "\nChinese B HL\n",
            "\nSpanish B SL\n", "\nSpanish B HL\n", "\nFrench B SL\n", "\nFrench B HL\n",
            "\nEconomics SL\n", "\nEconomics HL\n", "\nGeography SL\n", "\nGeography HL\n", "\nHistory SL\n", "\nHistory HL\n", "\nPsychology SL\n", "\nPsychology HL\n",
            "\nBiology SL\n", "\nBiology HL\n", "\nChemistry SL\n", "\nChemistry HL\n", "\nPhysics SL\n", "\nPhysics HL\n", "\nSport Science SL\n", "\nSport Science HL\n", "\nComputer Science SL\n", "\nComputer Science HL\n", "\nDesign Technology SL\n", "\nDesign Technology HL\n",
            "\nMathematics AA SL\n", "\nMathematics AA HL\n", "\nMathematics AI SL\n", "\nMathematics AI HL\n",
            "\nVisual Arts SL\n", "\nVisual Arts HL\n", "\nMusic SL\n", "\nMusic HL\n", "\nTheatre SL\n", "\nTheatre HL\n", "\nFilm SL\n", "\nFilm HL\n"};
    boolean[] selectedSubjects = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();

        select = findViewById(R.id.SelectSubject);
        showText = findViewById(R.id.SelectedTextView);
        showText.setText(itemsString());

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder newBuilder = new AlertDialog.Builder(AddSubjectActivity.this);
                newBuilder.setCancelable(true);
                newBuilder.setTitle("Make sure you select correct course and level.");
                newBuilder.setMultiChoiceItems(subjects, selectedSubjects, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        selectedSubjects[i] = b;
                    }
                });

                newBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println("Show the subjects.");
                        showText.setText(itemsString());
                        System.out.println(showText);
                        dialogInterface.dismiss();
                    }
                });

                newBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println("Leave the selection page.");
                    }
                });

                AlertDialog list = newBuilder.create();
                list.setCanceledOnTouchOutside(true);
                list.show();
            }
        });
    }

    private String itemsString() {
        String text = " ";
        for (int i = 0; i < selectedSubjects.length; i++) {
            if (selectedSubjects[i]) {
                text = text + subjects[i] + " ";
            }
        }
        return text.trim();
    }

    public void Store(View v) {
        System.out.println("Store the subjects into the Firebase Database.");
        //String subjectInput = showText.getText().toString();
        //firestore.collection("User").document(mUser.getUid()).set(subjectInput);
    }
}