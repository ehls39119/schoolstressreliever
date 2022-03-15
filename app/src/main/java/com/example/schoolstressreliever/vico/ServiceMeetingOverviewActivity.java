package com.example.schoolstressreliever.vico;

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

public class ServiceMeetingOverviewActivity extends AppCompatActivity {

    RecyclerView recView;
    ArrayList<String> nameInfo = new ArrayList<>();
    ArrayList<String> statusInfo = new ArrayList<>();

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_meeting_overview);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        mUser = mAuth.getCurrentUser();

        recView = findViewById(R.id.recView);

        Intent intent = getIntent();
        String currUser = intent.getExtras().getString("currUser");

        ServiceMeetingRecyclerViewAdapter myAdapter = new ServiceMeetingRecyclerViewAdapter(nameInfo, statusInfo
                , this, currUser);

        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

        updateRecView();

    }

    public void updateRecView()
    {
        firestore.collection("Service Meetings").get()
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
                                Map<String, Object> docData = doc.getData();

                                String currService = (String) docData.get("service");
                                nameInfo.add(currService);

                                String currDate = (String) docData.get("date");
                                String currTime = (String) docData.get("time");

                                statusInfo.add("Date: " + currDate + "     Time: "
                                        + currTime);

                                System.out.println(statusInfo);
                            }

                            ServiceMeetingRecyclerViewAdapter a = (ServiceMeetingRecyclerViewAdapter)
                                    recView.getAdapter();
                            a.changeInfo(nameInfo, statusInfo);
                            a.notifyDataSetChanged();
                        }
                    }
                });
    }

    public void goToAddServiceMeeting(View v){

        Intent newIntent = new Intent(this, AddServiceMeetingActivity.class);

        startActivity(newIntent);
    }
}