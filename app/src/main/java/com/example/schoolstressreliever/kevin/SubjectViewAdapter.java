package com.example.schoolstressreliever.kevin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.R;

import java.util.ArrayList;

public class SubjectViewAdapter extends RecyclerView.Adapter<SubjectViewHolder> {
    ArrayList<String> sData;

    public SubjectViewAdapter(ArrayList subjectData){
        sData = subjectData;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View reView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_recycler_layout,parent, false);
        SubjectViewHolder holder = new SubjectViewHolder(reView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        holder.subjectText.setText(sData.get(position));
    }

    public void newSubject(ArrayList subjectData){
        sData =  subjectData;
    }

    @Override
    public int getItemCount() {
        return sData.size();
    }
}
