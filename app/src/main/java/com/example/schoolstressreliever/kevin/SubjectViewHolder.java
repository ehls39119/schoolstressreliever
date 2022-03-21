package com.example.schoolstressreliever.kevin;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.R;

public class SubjectViewHolder extends RecyclerView.ViewHolder {
    protected TextView subjectText;

    public SubjectViewHolder(@NonNull View itemView) {
        super(itemView);
        subjectText = itemView.findViewById(R.id.SubjectText);
    }
}
