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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceOverviewActivity extends AppCompatActivity {

    RecyclerView recView;
    private FirebaseFirestore firestore;
    ArrayList<String> nameInfo = new ArrayList<>();
    ArrayList<String> statusInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_overview);

        recView = findViewById(R.id.recView);
        firestore = FirebaseFirestore.getInstance();

//        Intent intent = getIntent();
//        String myUserEmail = intent.getExtras().getString("currUser");

        String myUserEmail = "justinIsGay@student.cis.edu.hk";

        ServiceRecyclerViewAdapter myAdapter = new ServiceRecyclerViewAdapter(nameInfo, statusInfo
                , this, myUserEmail);

        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

        updateRecView();
    }

    public void updateRecView()
    {
        firestore.collection("everything").document("all services")
                .collection("services").get()
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

                                String currName = (String) docData.get("name");
                                nameInfo.add(currName);

                                String currInterestArea = (String) docData.get("intrestArea");
                                String currHours = (String) docData.get("hours");

                                statusInfo.add("Interest Area: " + currInterestArea + " Hours: "
                                            + currHours);

                                System.out.println(statusInfo);
                            }

                            ServiceRecyclerViewAdapter a = (ServiceRecyclerViewAdapter)
                                    recView.getAdapter();
                            a.changeInfo(nameInfo, statusInfo);
                            a.notifyDataSetChanged();
                        }
                    }
                });

    }


    public void goToAddService(View v){
        Intent newIntent = new Intent(this, AddServiceActivity.class);
        startActivity(newIntent);
    }
}