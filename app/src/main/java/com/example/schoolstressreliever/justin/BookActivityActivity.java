package com.example.schoolstressreliever.justin;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolstressreliever.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookActivityActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;
    private FirebaseUser mUser;
    private String points;

    public String activityHours;
    public String userHours;
    public String activityInterest;
    public String userInterest;
    public int compatibilityRating;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_activity);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        Intent intent = getIntent();
        String currUser = intent.getExtras().getString("currUser");
        String currActivity = intent.getExtras().getString("currActivity");

        TextView nameView = (TextView)findViewById(R.id.meetingNameTextView);
        TextView interestView = (TextView)findViewById(R.id.dateTextView);
        TextView hoursView = (TextView)findViewById(R.id.timeTextView);
        TextView emailView = (TextView)findViewById(R.id.emailTextView);
        TextView priceView = (TextView)findViewById(R.id.descriptionView);

        firestore.collection("everything").document("all activities")
                .collection("activities").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            List<DocumentSnapshot> ds = task.getResult().getDocuments();

                            for(DocumentSnapshot doc : ds)
                            {
                                Map<String, Object> docData = doc.getData();

                                String thisName = (String) docData.get("name");

                                if (thisName.equals(currActivity))
                                {
                                    String activityInterest = (String) docData.get("interestArea");
                                    String activityHours = (String) docData.get("hours");
                                    String thisEmail = (String) docData.get("email");
                                    String thisPrice = (String) docData.get("price");
                                    nameView.setText(thisName);
                                    interestView.setText(activityInterest);
                                    hoursView.setText("Hours demanded: " + activityHours);
                                    emailView.setText("Person to contact for more information: \n"
                                            + thisEmail);
                                    priceView.setText("Price is: \n" + thisPrice);

                                }
                            }
                        }
                    }
                });
        Intent i = getIntent();
        String userObject = (String)i.getSerializableExtra("userObject");

        firestore.collection("Users").document(mUser.getDisplayName()).collection(userObject).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {
                    List<DocumentSnapshot> ds = task.getResult().getDocuments();

                    for(DocumentSnapshot doc : ds)
                    {
                        Map<String, Object> docData = doc.getData();

                        String thisName = (String) docData.get("name");

                        if (thisName.equals(currActivity))
                        {
                            userInterest = (String) docData.get("interestArea");
                            userHours = (String) docData.get("hours");


//                            String thisEmail = (String) docData.get("email");
//                            String thisPrice = (String) docData.get("price");
//                            nameView.setText(thisName);
//                            interestView.setText(thisInterest);
//                            hoursView.setText("Hours demanded: " + thisHours);
//                            emailView.setText("Person to contact for more information: \n"
//                                    + thisEmail);
//                            priceView.setText("Price is: \n" + thisPrice);
                        }
                    }
                }

            }
        });

//                    User userObject = new User(mUser.getUid(), mUser.getDisplayName(), mUser.getEmail(), "", "", 0.5, "Arts");
//                firestore.collection("Users").document(mUser.getDisplayName()).set(userObject);
    }

    public void getCompatibility(View v)
    {

        double activityHoursConverted = 0.0;
        activityHoursConverted = Double.parseDouble(activityHours);
        double userHoursConverted = 0.0;
        userHoursConverted = Double.parseDouble(userHours);

        if(activityInterest.equals(userInterest))
        {
            compatibilityRating = 1;

            if(activityHoursConverted == userHoursConverted)
            {
                compatibilityRating += 1;
                Toast.makeText(this, "Highly recommended!", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "You might not be interested!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Sorry mate - you don't have time!", Toast.LENGTH_LONG).show();
        }

        Intent i = new Intent(this, ActivityOverviewActivity.class);
        i.putExtra("compatibility", (Serializable) compatibilityRating );
        startActivity(i);

    }

    public void addActivity(View v)
    {

        firestore = FirebaseFirestore.getInstance();

        firestore.collection("everything").document("all activities")
                .collection("activities").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            List<DocumentSnapshot> ds = task.getResult().getDocuments();

                            Intent intent = getIntent();
                            String myUserEmail = intent.getExtras().getString("currUser");
                            String currActivity = intent.getExtras().getString("currActivity");

                            for(DocumentSnapshot doc : ds)
                            {
                                Map<String, Object> docData = doc.getData();

                                String thisService = (String) docData.get("name");

                                ArrayList<String> allParticipants = new ArrayList<>();

                                for (java.lang.String currID : (ArrayList<String>) docData
                                        .get("participants"))
                                {
                                    String IDString = currID.toString();

                                    allParticipants.add(IDString);
                                }

                                if(thisService.equals(currActivity))
                                {
                                    allParticipants.add(myUserEmail);

                                    firestore.collection("everything")
                                            .document("all activities")
                                            .collection("activities").document(currActivity)
                                            .update("participants", allParticipants);

                                }
                            }
                        }
                    }
                });

        Toast.makeText(this, "Activity Signed Up", Toast.LENGTH_SHORT).show();
    }


}