package com.example.schoolstressreliever.kevin;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.R;

public class InfoViewHolder extends RecyclerView.ViewHolder {
    protected TextView idText;
    protected TextView nameText;
    protected TextView emailText;
    protected TextView yearText;
    protected TextView passwordText;
    //protected TextView selectedSubjects;

    public InfoViewHolder(@NonNull View itemView) {
        super(itemView);
        idText = itemView.findViewById(R.id.IDText);
        nameText = itemView.findViewById(R.id.NameText);
        emailText = itemView.findViewById(R.id.EmailText);
        yearText = itemView.findViewById(R.id.YearLevelText);
        passwordText = itemView.findViewById(R.id.PasswordText);
        //selectedSubjects = itemView.findViewById(R.id.SelectSubject);
    }
}
