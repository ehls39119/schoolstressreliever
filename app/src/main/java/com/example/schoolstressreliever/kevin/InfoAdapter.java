package com.example.schoolstressreliever.kevin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.R;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoViewHolder> {
    ArrayList<String> mData;
    String subjectInfo = "IBDP subjects selected:";

    public InfoAdapter(ArrayList nameData){
        mData = nameData;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View reView = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_recycler_layout, parent, false);
        InfoViewHolder holder = new InfoViewHolder(reView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        holder.subjectText.setText(mData.get(position));
        //holder.selectedSubjects.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
