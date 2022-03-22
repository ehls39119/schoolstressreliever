package com.example.schoolstressreliever.kevin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.schoolstressreliever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewInfoActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore data;
    public ArrayList<User> userList;
    private InfoAdapter adapter;
    RecyclerView infoRecycler;

    private ArrayList<String> idData;
    private ArrayList<String> nameData;
    private ArrayList<String> emailData;
    private ArrayList<String> yearData;
    private ArrayList<String> passwordData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);

        infoRecycler = findViewById(R.id.RecView);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        data = FirebaseFirestore.getInstance();
        idData = new ArrayList<>();
        nameData = new ArrayList<>();
        emailData = new ArrayList<>();
        yearData = new ArrayList<>();
        passwordData = new ArrayList<>();

        adapter = new InfoAdapter(idData, nameData, emailData, yearData, passwordData);
        infoRecycler.setAdapter(adapter);
        infoRecycler.setLayoutManager(new LinearLayoutManager(this));

        getAndPopulateData();
    }

    public void getAndPopulateData() {
        data.collection("Users").whereEqualTo(mUser.getEmail(), "email").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot info : task.getResult().getDocuments()){
                    User currUser = info.toObject(User.class);
                    System.out.println(info);//testing
                    userList.add(currUser);
                    System.out.println(userList);//testing
                }

                for(User user : userList){
                    //get data from userList
                    String idD = user.getID();
                    idData.add(idD);
                    String nameN = user.getName();
                    nameData.add(nameN);
                    String emailE = user.getEmail();
                    emailData.add(emailE);
                    String yearY = user.getYearLevel();
                    yearData.add(yearY);
                    String passwordP = user.getPassword();
                    passwordData.add(passwordP);
                }
                adapter.newData(idData,nameData,emailData,yearData,passwordData);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void GoChange(View v){
        //it can't link to the onclick in the activity_view_info.xml
        Intent goPage = new Intent(this, SignUpActivity.class);
        startActivity(goPage);
    }
}
