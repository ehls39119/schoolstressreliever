package com.example.schoolstressreliever.vico;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolstressreliever.R;

import java.util.ArrayList;

public class ServiceRecyclerViewAdapter extends RecyclerView.Adapter <ServiceViewHolder>{

    ArrayList<String> nameData;
    ArrayList<String> statusData;
    Context screen;
    String emailString;

    public ServiceRecyclerViewAdapter(ArrayList<String> itemNames,
                                      ArrayList<String> statusOutput,
                                      Context screen,
                                      String myUserEmail) {

        nameData = itemNames;
        statusData = statusOutput;

        this.screen = screen;

        emailString = myUserEmail;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_service_row_view,
                parent, false);

        ServiceViewHolder holder = new ServiceViewHolder(myView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position)
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

    public void updateUI(String serviceString)
    {
        Intent intent = new Intent(this.screen, BookServiceActivity.class);
        intent.putExtra("currService", serviceString);
        intent.putExtra("currUser", emailString);

        this.screen.startActivity(intent);
    }

}
