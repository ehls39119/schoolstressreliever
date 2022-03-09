package com.example.schoolstressreliever.kevin;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.R;

public class InfoViewHolder extends RecyclerView.ViewHolder {
    protected TextView nameText;

    public InfoViewHolder(@NonNull View itemView) {
        super(itemView);

        nameText = itemView.findViewById(R.id.TextName);
    }
}
