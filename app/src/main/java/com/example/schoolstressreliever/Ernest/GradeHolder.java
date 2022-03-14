package com.example.schoolstressreliever.Ernest;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoolstressreliever.R;

public class GradeHolder extends RecyclerView.ViewHolder {
    protected TextView progressPercentView;

    public GradeHolder(@NonNull View itemView) {
        super(itemView);
        progressPercentView = (itemView).findViewById(R.id.gradeTextView1);
    }

    public ConstraintLayout getLayout(){
        return itemView.findViewById(R.id.rowLayout);
    }
}
