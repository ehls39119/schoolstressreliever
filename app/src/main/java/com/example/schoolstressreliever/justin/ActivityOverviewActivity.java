package com.example.schoolstressreliever.justin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolstressreliever.R;
import com.example.schoolstressreliever.vico.ServiceRecyclerViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityOverviewActivity extends AppCompatActivity {

    RecyclerView recView;
    ArrayList<String> nameInfo = new ArrayList<>();
    ArrayList<String> statusInfo = new ArrayList<>();
    String myUserEmail;

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_overview);

        recView = findViewById(R.id.recView);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        mUser = mAuth.getCurrentUser();

        ServiceRecyclerViewAdapter myAdapter = new ServiceRecyclerViewAdapter(nameInfo, statusInfo
                , this);

        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

        updateRecView();
    }

    ArrayList <Map> serviceList = new ArrayList<>();

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
                                Map<String, Object> serviceData = doc.getData();
                                serviceList.add(serviceData);

                                System.out.println("service added");

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
                                                        System.out.println(serviceList);

                                                        ArrayList <Map> doubleMatch = new ArrayList<>();
                                                        ArrayList <Map> singleMatch = new ArrayList<>();
                                                        ArrayList <Map> noMatch = new ArrayList<>();

                                                        String currUserInterest = (String) userData.get("interestAreaActivity");
                                                        String currUserHours = (String) userData.get("hoursAvailableActivity");

                                                        System.out.println(currUserInterest + currUserHours);

                                                        for(Map service : serviceList)
                                                        {
                                                            Boolean interestMatch = false;
                                                            Boolean hoursMatch = false;

                                                            String currServiceArea = (String) service.get("intrestArea");
                                                            String currServiceHours = (String) service.get("hours");

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
                                                                doubleMatch.add(service);
                                                            }
                                                            else if(hoursMatch || interestMatch)
                                                            {
                                                                singleMatch.add(service);
                                                            }
                                                            else
                                                            {
                                                                noMatch.add(service);
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


    public void goToAddService(View v)
    {
        Intent newIntent = new Intent(this, AddActivityActivity.class);
        startActivity(newIntent);
    }

    public void goToServiceMeetingActivity(View v)
    {
        Intent thisIntent = new Intent(this, ActivityMeetingOverviewActivity.class);
        startActivity(thisIntent);
    }

}