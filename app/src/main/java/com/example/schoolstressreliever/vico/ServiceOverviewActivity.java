package com.example.schoolstressreliever.vico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolstressreliever.R;
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

public class ServiceOverviewActivity extends AppCompatActivity {

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

//        Intent intent = getIntent();
//        String myUserEmail = intent.getExtras().getString("currUser");

        myUserEmail = "justinIsGay@student.cis.edu.hk";

        ServiceRecyclerViewAdapter myAdapter = new ServiceRecyclerViewAdapter(nameInfo, statusInfo
                , this, myUserEmail);

        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

        updateRecView();
    }

    ArrayList <Map> serviceList = new ArrayList<>();
    String currInterest;
    String currHours;

    public void updateRecView()
    {
        firestore.collection("Services").get()
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


                                /*temp check*/
                                String currName = (String) serviceData.get("name");

                                nameInfo.add(currName);

                                String currInterestArea = (String) serviceData.get("intrestArea");
                                String currHours = (String) serviceData.get("hours");

                                statusInfo.add("Interest Area: " + currInterestArea + "     Hours: "
                                        + currHours);

                            }

                            System.out.println(statusInfo);

                            ServiceRecyclerViewAdapter a = (ServiceRecyclerViewAdapter)
                                    recView.getAdapter();
                            a.changeInfo(nameInfo, statusInfo);
                            a.notifyDataSetChanged();
                        }
                    }
                });



        /*uncomment when theres a user*/

//        firestore.collection("Users").get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
//                {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task)
//                    {
//                        if (task.isSuccessful())
//                        {
//                            List<DocumentSnapshot> ds = task.getResult().getDocuments();
//
//                            for(DocumentSnapshot doc : ds)
//                            {
//                                Map<String, Object> userData = doc.getData();
//
//                                if(userData.get("email").equals(mUser.getEmail()))
//                                {
//                                    currInterest = (String) userData.get("interestArea");
//                                    currHours = (String) userData.get("hours");
//                                }
//                            }
//                        }
//                    }
//                });
//
//        ArrayList <Map> doubleMatch = new ArrayList<>();
//        ArrayList <Map> singleMatch = new ArrayList<>();
//        ArrayList <Map> noMatch = new ArrayList<>();
//
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


        ServiceRecyclerViewAdapter a = (ServiceRecyclerViewAdapter)
                recView.getAdapter();
        a.changeInfo(nameInfo, statusInfo);
        a.notifyDataSetChanged();

    }


    public void goToAddService(View v){
        Intent newIntent = new Intent(this, AddServiceActivity.class);
        startActivity(newIntent);
    }

    public void goToServiceMeetingActivity(View v){

        Intent newIntent = new Intent(this, ServiceMeetingOverviewActivity.class);
        newIntent.putExtra("currUser", myUserEmail);
        startActivity(newIntent);
    }
}