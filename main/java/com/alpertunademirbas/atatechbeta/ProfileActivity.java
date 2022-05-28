package com.alpertunademirbas.atatechbeta;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    TextView textView;
    Button btn1, btn2;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView= findViewById(R.id.textView15);
        btn1=findViewById(R.id.button8);
        btn2=findViewById(R.id.button29);

        auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userEmaill = firebaseUser.getEmail();
        textView.setText(userEmaill);

    }

    ////////////PROFILE PAGE BUTTONS//////////////
    public void goToUsersClicked (View view){
        Intent intent = new Intent(ProfileActivity.this,MainScreenActivity.class);
        startActivity(intent);
    }
    public void appInfoClicked(View view){
        Intent intent = new Intent(ProfileActivity.this,InfoActivity.class);
        startActivity(intent);
    }
    public void signOutClicked (View view){
        auth.signOut();
        Intent outIntent = new Intent(ProfileActivity.this,MainActivity.class);
        startActivity(outIntent);
        finish();

    }
}