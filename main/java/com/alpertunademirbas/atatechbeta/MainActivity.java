package com.alpertunademirbas.atatechbeta;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    public EditText editTextTextEmailAddress, editTextTextPassword;
    private FirebaseAuth mAuth;
    Button btn;
    public SharedPreferences sharedPreferences;
    public String entereduser, getEntereduser;

    //FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView3);
        imageView = findViewById(R.id.imageView4);
        btn=findViewById(R.id.button90);
        btn=findViewById(R.id.button2);
        btn=findViewById(R.id.button3);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        EditText userr = (editTextTextEmailAddress);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //kullanıcı daha önce girdiyse giriş yap direkt

        String email = editTextTextEmailAddress.getText().toString();
        entereduser = email;

        FirebaseUser user = mAuth.getCurrentUser();
        sharedPreferences = this.getSharedPreferences("com.alpertunademirbas.atatechbeta", Context.MODE_PRIVATE);


        if (user != null){

            sharedPreferences.edit().putString("storedEmail", entereduser).apply();
            Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
            intent.putExtra("sa",entereduser);

            //intent.putExtra("girenEmail",userr.getText().toString());
            startActivity(intent);
            finish();
        }else {
        }


    }

    public void LogInClicked (View view){

        String email = editTextTextEmailAddress.getText().toString();
        String password = editTextTextPassword.getText().toString();


        if (email.equals("")||password.equals("")){
            Toast.makeText(MainActivity.this,"Enter E-Mail and Password!",Toast.LENGTH_LONG).show();

        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    entereduser = email;
                    sharedPreferences.edit().putString("storedEmail", entereduser).apply();


                    Toast.makeText(MainActivity.this,"Welcome to AtaTech",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                    intent.putExtra("sa",entereduser);
                    //intent.putExtra("girenEmail",editTextTextEmailAddress.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }
            });
        }


    }

    //kayıt ekranına yollama
    public void SignUpClicked (View view){

        Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);
    }



}