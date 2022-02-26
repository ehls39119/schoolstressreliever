package com.example.schoolstressreliever.Ernest;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoolstressreliever.R;

public class SubjectViewHolder extends RecyclerView.ViewHolder {
    protected TextView nameText;
    protected TextView statusText1;
    protected TextView statusText2;


    public SubjectViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText = (itemView).findViewById(R.id.nameTextView);
        statusText1 = (itemView).findViewById(R.id.statusTextView);
        statusText2 = (itemView).findViewById(R.id.statusTextView);
    }

    public ConstraintLayout getLayout(){
        return itemView.findViewById(R.id.rowLayout);
    }
}
