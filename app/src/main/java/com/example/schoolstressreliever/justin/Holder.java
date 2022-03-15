package com.example.schoolstressreliever.justin;


import android.content.ClipData;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.R;

import java.text.BreakIterator;

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    // Holder for my recyclerViewer//
    public TextView capacityText;
    public TextView makerText;
    public TextView typeText;
    private receiveReqAdapter.ItemClickListener mClickListener;

    public Holder(@NonNull View itemView, receiveReqAdapter.ItemClickListener mClickListener)
    {
        super(itemView);
        this.mClickListener = mClickListener;
        capacityText = itemView.findViewById(R.id.capacityTextView);
        makerText = itemView.findViewById(R.id.makerText);
        typeText = itemView.findViewById(R.id.typeText);
    }

    private void removeItem(int position)
    {
    }

    public ConstraintLayout returnLayout()
    {
        return itemView.findViewById(R.id.constraint);
    }

    @Override
    public void onClick(View view)
    {
        if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
    }
}
