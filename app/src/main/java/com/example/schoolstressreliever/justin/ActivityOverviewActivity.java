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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    public int pointSystem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_overview);
        recView = findViewById(R.id.recView);
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        mUser = mAuth.getCurrentUser();

//        Intent intent = getIntent();
//        String myUserEmail = intent.getExtras().getString("currUser");


        ActivityRecyclerViewAdapter myAdapter = new ActivityRecyclerViewAdapter(nameInfo, statusInfo
                , this, mUser.getEmail());

        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

        updateRecView();

        ActivityOverviewActivity context = this;

    }


    ArrayList <Map> activityList = new ArrayList<>();

    public void updateRecView()
    {
        firestore.collection("Activities").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            List<DocumentSnapshot> ds = task.getResult().getDocuments();

                            for(DocumentSnapshot doc : ds)
                            {
                                Map<String, Object> activityData = doc.getData();
                                activityList.add(activityData);

                                System.out.println("activity added");

                                /*temp check*/
//                                String currName = (String) serviceData.get("name");
//
//                                nameInfo.add(currName);
//
//                                String currInterestArea = (String) serviceData.get("intrestArea");
//                                String currHours = (String) serviceData.get("hours");
//
//                                statusInfo.add("Interest Area: " + currInterestArea + "     Hours: "
//                                        + currHours);

                            }

                            firestore.collection("Users").get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                                    {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task)
                                        {
                                            if (task.isSuccessful())
                                            {
                                                List<DocumentSnapshot> ds = task.getResult().getDocuments();

                                                for(DocumentSnapshot doc : ds)
                                                {
                                                    Map<String, Object> userData = doc.getData();

                                                    if(userData.get("email").equals(mUser.getEmail()))
                                                    {
                                                        System.out.println(activityList);

                                                        ArrayList <Map> doubleMatch = new ArrayList<>();
                                                        ArrayList <Map> singleMatch = new ArrayList<>();
                                                        ArrayList <Map> noMatch = new ArrayList<>();

                                                        String currUserInterest = (String) userData.get("interestAreaActivity");
                                                        String currUserHours = (String) userData.get("hoursAvailableActivity");

                                                        System.out.println(currUserInterest + currUserHours);

                                                        for(Map activity : activityList)
                                                        {
                                                            Boolean interestMatch = false;
                                                            Boolean hoursMatch = false;

                                                            String currServiceArea = (String) activity.get("intrestArea");
                                                            String currServiceHours = (String) activity.get("hours");

                                                            double currUserHoursInt = Double.parseDouble(currUserHours);
                                                            double currServiceHoursInt = Double.parseDouble(currServiceHours);

                                                            if(currServiceArea.equals(currUserInterest))
                                                            {
                                                                interestMatch = true;
                                                            }

                                                            if(currServiceHoursInt < currUserHoursInt)
                                                            {
                                                                hoursMatch = true;
                                                            }

                                                            if(hoursMatch && interestMatch)
                                                            {
                                                                doubleMatch.add(activity);
                                                            }
                                                            else if(hoursMatch || interestMatch)
                                                            {
                                                                singleMatch.add(activity);
                                                            }
                                                            else
                                                            {
                                                                noMatch.add(activity);
                                                            }
                                                        }

                                                        for(Map currService : doubleMatch)
                                                        {
                                                            String currName = (String) currService.get("name");
                                                            nameInfo.add(currName);

                                                            String currInterestArea = (String) currService.get("intrestArea");
                                                            String currHours = (String) currService.get("hours");

                                                            statusInfo.add("Interest Area: " + currInterestArea + "     Hours: "
                                                                    + currHours);

                                                            System.out.println(statusInfo);
                                                        }

                                                        for(Map currService : singleMatch)
                                                        {
                                                            String currName = (String) currService.get("name");
                                                            nameInfo.add(currName);

                                                            String currInterestArea = (String) currService.get("intrestArea");
                                                            String currHours = (String) currService.get("hours");

                                                            statusInfo.add("Interest Area: " + currInterestArea + "     Hours: "
                                                                    + currHours);

                                                        }

                                                        for(Map currService : noMatch)
                                                        {
                                                            String currName = (String) currService.get("name");
                                                            nameInfo.add(currName);

                                                            String currInterestArea = (String) currService.get("intrestArea");
                                                            String currHours = (String) currService.get("hours");

                                                            statusInfo.add("Interest Area: " + currInterestArea + "     Hours: "
                                                                    + currHours);

                                                        }

                                                        ServiceRecyclerViewAdapter a = (ServiceRecyclerViewAdapter)
                                                                recView.getAdapter();
                                                        a.changeInfo(nameInfo, statusInfo);
                                                        a.notifyDataSetChanged();
                                                    }
                                                }
                                            }
                                        }
                                    });
                        }
                    }
                });

    }

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



    public void goToAddActivity (View v)
    {
        Intent newIntent = new Intent(this, AddActivityActivity.class);
        startActivity(newIntent);
    }
}