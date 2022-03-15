package com.example.schoolstressreliever.justin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.schoolstressreliever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class receivePrivActivityRequest extends AppCompatActivity implements receiveReqAdapter.ItemClickListener {

    String day;
    String month;
    String year;
    int mDay;
    int mMonth;
    int mYear;
    String amOrPm;
    String time;
    String category;
    String owner;
    String capacity;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser mUser;
    receiveReqAdapter adapter;
    String userCategory;
    EditText dateEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_priv_request);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        ArrayList<String> capacityData = new ArrayList<>();
        ArrayList<String> senderData = new ArrayList<>();
        ArrayList<String>typeData = new ArrayList<>();
        firestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Intent intent = getIntent();
        if(intent != null){
            day = intent.getStringExtra("Day");
            month = intent.getStringExtra("Month");
            year = intent.getStringExtra("Year");
            amOrPm = intent.getStringExtra("AM or PM");
            time = intent.getStringExtra("Time");
            category = intent.getStringExtra("Category");
            owner = intent.getStringExtra("Owner");
            capacity = intent.getStringExtra("Capacity");
            Calendar calendar = Calendar.getInstance();
            mDay = Integer.parseInt(day);
            mMonth = Integer.parseInt(month);
            mYear = Integer.parseInt(year);
            calendar.set(mYear,mMonth,mDay);
            dateEditText = (EditText)findViewById(R.id.dateEditText);
            SimpleDateFormat format = new SimpleDateFormat(day + month+ year + amOrPm + time);
            dateEditText.setText(format.format(calendar.getTime()));

            receivePrivActivityRequest context = this;
            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClicker(context, recyclerView ,new RecyclerItemClicker.OnItemClickListener()
                    {
                        @Override public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getApplicationContext(), reviewRequestActivity.class);
                            final String meetingCapacity = capacityData.get(position);
                            intent.putExtra("capacity", meetingCapacity);
                            final String meetingMaker = senderData.get(position);
                            intent.putExtra("maker", meetingMaker);
                            final String meetingCategory = typeData.get(position);
                            intent.putExtra("type", meetingCategory);
                            startActivity(intent);
                            finish();
                        }
                            @Override
                            public void onLongItemClick (View view,int position)
                            {
                                capacityData.remove(position);
                                senderData.remove(position);
                                typeData.remove(position);
                                adapter.notifyItemRemoved(position);
                                /*do any other changes need to be made? */
                            }
                        })
                            );

            firestore.collection("Meetings").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful())
                    {
                        List<DocumentSnapshot> ds = task.getResult().getDocuments();
                        for(DocumentSnapshot obj: ds) {
                            Map<String, Object> objData = obj.getData();
                            String type = (String)objData.get("type");
                            long capacity = (long) objData.get("capacity");
                            String maker = (String)objData.get("maker");
                            String capacityConverted = String.valueOf(capacity);

                            Intent i = getIntent();
                            String userObject = (String)i.getSerializableExtra("userObject");
                            firestore.collection("Users").document(mUser.getDisplayName()).collection(userObject).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                    if (task.isSuccessful()) {
                                        List<DocumentSnapshot> ds = task.getResult().getDocuments();

                                        for (DocumentSnapshot doc : ds) {
                                            Map<String, Object> docData = doc.getData();
                                            userCategory = (String) docData.get("interestArea");

                                            if(userCategory.equals(type))
                                            {
                                                capacityData.add(capacityConverted);
                                                senderData.add(maker);
                                                typeData.add(type);

                                                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                                                adapter = new receiveReqAdapter(context, senderData);
                                                adapter.updateInfo(capacityData, senderData, typeData);
                                                adapter.setClickListener(context);
                                                recyclerView.setAdapter(adapter);
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            });


        }

    }



    @Override
    public void onItemClick(View view, int position) {

    }
}