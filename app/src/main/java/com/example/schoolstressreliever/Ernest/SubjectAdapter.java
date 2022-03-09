package com.example.schoolstressreliever.Ernest;

import android.content.Context;
import android.content.Intent;
import com.example.schoolstressreliever.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList studentData;
    Context currentContext;

    public SubjectAdapter(ArrayList<Student> studentInfoInput, Context context){
        studentData = studentInfoInput;
        this.currentContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_holder, parent, false );
        return new SubjectHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int pos = position;
        Student newStudent = (Student) studentData.get(position);

        int transcript_grade = newStudent.getTranscriptGrade();
        int progress_grade = newStudent.getProgressGrade();

        ArrayList<String> x = newStudent.getSubjects();
        String currSubject = x.get(position);

//        String str = "";
//        for (String fruit : newVehicle.getRiderIDs()) {
//            str += fruit;
//            str += "\n";
//        }

        String displayTranscript = Integer.toString(transcript_grade);
        String displayProgress = Integer.toString(progress_grade);

        ((SubjectHolder) holder).nameText.setText(currSubject);
        ((SubjectHolder) holder).statusText1.setText(displayProgress);
        ((SubjectHolder) holder).statusText2.setText(displayTranscript);

        ((SubjectHolder) holder).getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GradeOverview.class);
                myIntent.putExtra("Subject", currSubject);
                myIntent.putExtra("Transcript", transcript_grade);
                myIntent.putExtra("Progress", progress_grade);
                currentContext.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentData.size();
    }

    public void setVehiclesData(ArrayList<Student> students)
    {
        this.studentData = students;
    }



}
