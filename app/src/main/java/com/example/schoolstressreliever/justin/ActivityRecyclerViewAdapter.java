package com.example.schoolstressreliever.justin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.MainActivity;
import com.example.schoolstressreliever.R;
import com.example.schoolstressreliever.vico.BookServiceActivity;
import com.example.schoolstressreliever.vico.ServiceViewHolder;

import java.util.ArrayList;

public class ActivityRecyclerViewAdapter extends RecyclerView.Adapter <ActivityViewHolder>{

    ArrayList<String> nameData;
    ArrayList<String> statusData;
    Context screen;
    String emailString;

    public ActivityRecyclerViewAdapter(ArrayList<String> itemNames,
                                      ArrayList<String> statusOutput,
                                      Context screen) {

        nameData = itemNames;
        statusData = statusOutput;

        this.screen = screen;

    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_view,
                parent, false);

        ActivityViewHolder holder = new ActivityViewHolder(myView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position)
    {
        /*set name and STATUS*/
        holder.nameText.setText(nameData.get(position));
        holder.statusText.setText(statusData.get(position));

        holder.returnLayout().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String nameString = holder.getNameText();

                updateUI(nameString);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nameData.size();
    }

    public void changeInfo(ArrayList<String> nameData, ArrayList<String> statusData)
    {
        this.nameData = nameData;
        this.statusData = statusData;
    }

    public void updateUI(String activityString)
    {
        Intent intent = new Intent(this.screen, BookActivityActivity.class);
        intent.putExtra("currActivity", activityString);

        this.screen.startActivity(intent);
    }

}
