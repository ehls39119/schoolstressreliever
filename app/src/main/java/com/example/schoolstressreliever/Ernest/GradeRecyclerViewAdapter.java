package com.example.schoolstressreliever.Ernest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schoolstressreliever.R;

import java.util.ArrayList;


public class GradeRecyclerViewAdapter extends AppCompatActivity {
    ArrayList gradeData;
    Context currentContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_recycler_view_adapter);
    }

    public GradeRecyclerViewAdapter(ArrayList<Double> grades, Context context) {
        this.currentContext = context;
        gradeData = grades;
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_grade_vehicle_holder, parent, false);
        return new SubjectViewHolder(myView);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        int pos = position;
//        Student newStudent = (Student) studentData.get(position);
//
//        int transcript_grade = newStudent.getTranscriptGrade();
//        int progress_grade = newStudent.getProgressGrade();
//
//        ArrayList<String> x = newStudent.getSubjects();
//        String currSubject = x.get(position);
//
////        String str = "";
////        for (String fruit : newVehicle.getRiderIDs()) {
////            str += fruit;
////            str += "\n";
////        }
//
//        String displayTranscript = Integer.toString(transcript_grade);
//        String displayProgress = Integer.toString(progress_grade);
//
//        ((SubjectViewHolder) holder).nameText.setText(currSubject);
//        ((SubjectViewHolder) holder).statusText1.setText(displayProgress);
//        ((SubjectViewHolder) holder).statusText2.setText(displayTranscript);
//
//        ((SubjectViewHolder) holder).getLayout().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(view.getContext(), SubjectInfoActivity.class);
//                myIntent.putExtra("Subject", currSubject);
//                myIntent.putExtra("Transcript", transcript_grade);
//                myIntent.putExtra("Progress", progress_grade);
//                currentContext.startActivity(myIntent);
//            }
//        });
    }


    public int getItemCount() {
        return gradeData.size();
    }

    public void setGradeData(ArrayList<Double> grades)
    {
        this.gradeData = gradeData;
    }



}