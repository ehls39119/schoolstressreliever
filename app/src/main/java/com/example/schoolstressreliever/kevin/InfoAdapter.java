package com.example.schoolstressreliever.kevin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.R;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoViewHolder> {
    ArrayList<String> iData;
    ArrayList<String> mData;
    ArrayList<String> eData;
    ArrayList<String> yData;
    ArrayList<String> pData;

    public InfoAdapter(ArrayList idData, ArrayList nameData, ArrayList emailData, ArrayList yearData, ArrayList passwordData) {
        iData = idData;
        mData = nameData;
        eData = emailData;
        yData = yearData;
        pData = passwordData;
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
        holder.idText.setText(mData.get(position));
        holder.nameText.setText(mData.get(position));
        holder.emailText.setText(mData.get(position));
        holder.yearText.setText(mData.get(position));
        holder.passwordText.setText(mData.get(position));
        //holder.selectedSubjects.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
