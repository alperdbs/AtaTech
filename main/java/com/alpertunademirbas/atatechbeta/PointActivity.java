package com.alpertunademirbas.atatechbeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.firestore.FirebaseFirestore;




import java.util.HashMap;
import java.util.Map;


public class PointActivity extends AppCompatActivity {


    FirebaseFirestore db;
    public Button button;
    TextView textViewSelectedUser,textViewRate;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();



        Intent intent = getIntent();
        users SelectedUser =(users) intent.getSerializableExtra("Userr");

        textViewSelectedUser = findViewById(R.id.textView11);
        textViewRate = findViewById(R.id.textView13);
        button = findViewById(R.id.button54);
        textViewSelectedUser.setText(SelectedUser.name);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rating = "Rating: " + ratingBar.getRating();
                String logratepoint = ""+ratingBar.getRating();
                textViewRate.setText(rating);
                //Toast.makeText(PointActivity.this,"Thanks For "+rating,Toast.LENGTH_LONG).show();

                String SelectedUserForDB = SelectedUser.name;
                String RatingForDB = rating;

                FirebaseUser firebaseUser = auth.getCurrentUser();
                String UsedRateUserEmail = firebaseUser.getEmail();

                Map<String, Object> logRateUser = new HashMap<>();
                logRateUser.put("Used Rate This User Email",UsedRateUserEmail);
                logRateUser.put("Rated This User",SelectedUserForDB);
                logRateUser.put("Rate Point",RatingForDB);


                db.collection("UsedRates")
                        .add(logRateUser)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(PointActivity.this,"Rate "+ logratepoint +" logged data base. Thanks.",Toast.LENGTH_LONG).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PointActivity.this,"Failed! Please contact alpertunademirbas@gmail.com",Toast.LENGTH_LONG).show();

                    }
                });



                button.setEnabled(false);
                ratingBar.setEnabled(false);



            }
        });


    }












}