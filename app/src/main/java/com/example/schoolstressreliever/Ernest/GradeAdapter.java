package com.example.schoolstressreliever.Ernest;

import android.content.Context;

import com.example.schoolstressreliever.R;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;

public class GradeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList studentData;
    Context currentContext;

    public GradeAdapter(ArrayList<Student> studentInfoInput, Context context) {
        studentData = studentInfoInput;
        this.currentContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_holder, parent, false);
        return new GradeHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Student newStudent = (Student) studentData.get(position);

        ArrayList<Map<String, Map<String, Double>>> gradeInfo = new ArrayList<Map<String, Map<String, Double>>>();
        gradeInfo = newStudent.getGradeInfo();
        System.out.println("retrieved info part 2 " + gradeInfo);


        Map<String, Map<String, Double>> bigMap = new HashMap<>();
        bigMap = gradeInfo.get(position);
        for (Map.Entry<String, Map<String, Double>> entry : bigMap.entrySet()) {
            if (entry.getKey().equals("Grades")) {
                Map<String, Double> allGrades = new HashMap<>();
                allGrades = entry.getValue();
                for (Map.Entry<String, Double> testEntry : allGrades.entrySet()) {
                    Double testProgressGrade = testEntry.getValue();
                    String testProgressGradeToShow = Double.toString(testProgressGrade);

                    ((GradeHolder) holder).progressPercentView.setText(testProgressGradeToShow);
                    ((GradeHolder) holder).getLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(view.getContext(), GradeOverview.class);
                            myIntent.putExtra("Progress Percent", testProgressGradeToShow);
                            currentContext.startActivity(myIntent);
                        }
                    });
                }

            }
        }
    }







    @Override
    public int getItemCount() {
        return studentData.size();
    }

    public void setGradeData(ArrayList<Student> students)
    {
        this.studentData = students;
    }


}
