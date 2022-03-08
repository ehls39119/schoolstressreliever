package com.example.schoolstressreliever.justin;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolstressreliever.R;
import com.example.schoolstressreliever.justin.AddActivityActivity;
import com.example.schoolstressreliever.justin.ActivityRecyclerViewAdapter;
import com.example.schoolstressreliever.vico.ServiceRecyclerViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ActivityOverviewActivity extends AppCompatActivity {


    RecyclerView recView;
    private FirebaseFirestore firestore;
    ArrayList<String> nameInfo = new ArrayList<>();
    ArrayList<String> statusInfo = new ArrayList<>();

    public int pointSystem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_overview);
        recView = findViewById(R.id.recView);
        firestore = FirebaseFirestore.getInstance();

//        Intent intent = getIntent();
//        String myUserEmail = intent.getExtras().getString("currUser");

        String myUserEmail = "justinIsStraight@student.cis.edu.hk";

        ActivityRecyclerViewAdapter myAdapter = new ActivityRecyclerViewAdapter(nameInfo, statusInfo
                , this, myUserEmail);

        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

        updateRecView();

        ActivityOverviewActivity context = this;

    }




    public void updateRecView() {
        firestore.collection("everything").document("all activities")
                .collection("activities").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> ds = task.getResult().getDocuments();

                            for (DocumentSnapshot doc : ds) {
                                Map<String, Object> docData = doc.getData();

                                String currName = (String) docData.get("name");
                                nameInfo.add(currName);

                                String currInterestArea = (String) docData.get("interestArea");
                                String currHours = (String) docData.get("hours");

                                String compatibilityRating = getIntent().getExtras().getString("compatibility");
                                statusInfo.add(compatibilityRating + "Interest Area: " + currInterestArea + "     Hours: "
                                        + currHours);

                                System.out.println(statusInfo);
                            }

                            ActivityRecyclerViewAdapter a = (ActivityRecyclerViewAdapter)
                                    recView.getAdapter();
                            a.changeInfo(nameInfo, statusInfo);
                            a.notifyDataSetChanged();
                        }
                    }
                });


//        ArrayList <Map> activityList = new ArrayList<>();
//        String currInterest;
//        String currHours;
//
//        ArrayList <Map> doubleMatch = new ArrayList<>();
//        ArrayList <Map> singleMatch = new ArrayList<>();
//        ArrayList <Map> noMatch = new ArrayList<>();



//        for(Map service : serviceList)
//        {
//            Boolean interestMatch = false;
//            Boolean hoursMatch = false;
//
//            if(service.get("interestArea").equals(currInterest))
//            {
//                interestMatch = true;
//            }
//
//            if(service.get("hours").equals(currHours))
//            {
//                hoursMatch = true;
//            }
//
//            if(hoursMatch && interestMatch)
//            {
//                doubleMatch.add(service);
//            }
//            else if(hoursMatch || interestMatch)
//            {
//                singleMatch.add(service);
//            }
//            else
//            {
//                noMatch.add(service);
//            }
//        }
//
//        for(Map currService : doubleMatch)
//        {
//            String currName = (String) currService.get("name");
//            nameInfo.add(currName);
//
//            String currInterestArea = (String) currService.get("intrestArea");
//            String currHours = (String) currService.get("hours");
//
//            statusInfo.add("Interest Area: " + currInterestArea + "     Hours: "
//                    + currHours);
//
//            System.out.println(statusInfo);
//        }
//
//        for(Map currService : singleMatch)
//        {
//            String currName = (String) currService.get("name");
//            nameInfo.add(currName);
//
//            String currInterestArea = (String) currService.get("intrestArea");
//            String currHours = (String) currService.get("hours");
//
//            statusInfo.add("Interest Area: " + currInterestArea + "     Hours: "
//                    + currHours);
//
//            System.out.println(statusInfo);
//        }
//
//        for(Map currService : noMatch)
//        {
//            String currName = (String) currService.get("name");
//            nameInfo.add(currName);
//
//            String currInterestArea = (String) currService.get("intrestArea");
//            String currHours = (String) currService.get("hours");
//
//            statusInfo.add("Interest Area: " + currInterestArea + "     Hours: "
//                    + currHours);
//
//            System.out.println(statusInfo);
//        }
//
//        ServiceRecyclerViewAdapter a = (ServiceRecyclerViewAdapter)
//                recView.getAdapter();
//        a.changeInfo(nameInfo, statusInfo);
//        a.notifyDataSetChanged();


    }
    public void goToAddActivity (View v)
    {
        Intent newIntent = new Intent(this, AddActivityActivity.class);
        startActivity(newIntent);
    }
}