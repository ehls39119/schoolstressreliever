package com.example.schoolstressreliever.Ernest;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoolstressreliever.R;

public class SubjectHolder extends RecyclerView.ViewHolder {
    protected TextView nameText;
    protected TextView statusText1;


    public SubjectHolder(@NonNull View itemView) {
        super(itemView);
        nameText = (itemView).findViewById(R.id.nameTextView);
        statusText1 = (itemView).findViewById(R.id.progressTextView);
    }

    public ConstraintLayout getLayout(){
        return itemView.findViewById(R.id.rowLayout);
    }
}
