package com.example.schoolstressreliever.Ernest;

import android.content.Context;
import android.content.Intent;
import com.example.schoolstressreliever.R;
import com.example.schoolstressreliever.kevin.User;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList studentData;
    Context currentContext;

    public SubjectAdapter(ArrayList<User> studentInfoInput, Context context){
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

        User newStudent = (User) studentData.get(position);
        ArrayList<Map<String, Map<String, Double>>> gradeInfo = new ArrayList<Map<String, Map<String, Double>>>();
        gradeInfo = newStudent.getGradeInfo();
        System.out.println("retrieved info " + gradeInfo);


        Map<String, Map<String, Double>> bigMap = new HashMap<>();
        bigMap = gradeInfo.get(position);
        for (Map.Entry<String, Map<String, Double>> entry : bigMap.entrySet()) {
            if (entry.getKey().equals("Grades")){
                Map<String, Double> allGrades = new HashMap<>();
                allGrades = entry.getValue();
            }
            else{
                // map for progress and transcript
                String subjectToShow = entry.getKey();
                Map<String, Double> progressAndTranscript = entry.getValue();
                for (Map.Entry<String, Double> z : progressAndTranscript.entrySet()) {
                    if (z.getKey().equals("Progress")) {
                        Double pGrade = z.getValue();
                        String progressToShow = Double.toString(pGrade);


                        ((SubjectHolder) holder).nameText.setText(subjectToShow);
                        ((SubjectHolder) holder).statusText1.setText(progressToShow);

                        ((SubjectHolder) holder).getLayout().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent myIntent = new Intent(view.getContext(), GradeOverview.class);
                                myIntent.putExtra("Subject", subjectToShow);
                                myIntent.putExtra("Progress", progressToShow);

                                currentContext.startActivity(myIntent);
                            }
                        });
                    }

                }

            }


        }

    }



    @Override
    public int getItemCount() {
        return studentData.size();
    }

    public void setSubjectData(ArrayList<User> students)
    {
        this.studentData = students;
    }



}
