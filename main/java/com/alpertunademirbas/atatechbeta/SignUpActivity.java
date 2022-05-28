package com.alpertunademirbas.atatechbeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    Button btn1, btn2;
    EditText editText, editText2, editText3, editText4, editText5;

    private FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = FirebaseFirestore.getInstance();
        btn1=findViewById(R.id.button4);
        btn2=findViewById(R.id.button5);
        editText=findViewById(R.id.editTextTextEmailAddress2);
        editText2=findViewById(R.id.editTextTextPassword2);
        editText3=findViewById(R.id.editTextTextPersonName);
        editText4=findViewById(R.id.editTextTextPersonName2);
        editText5=findViewById(R.id.editTextNumber);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Firstname = editText3.getText().toString();
                String Lastname = editText4.getText().toString();
                String Number = editText5.getText().toString();
                String Email = editText.getText().toString();
                String Password = editText2.getText().toString();

                if (Firstname.equals("") || Lastname.equals("") || Number.equals("") || Email.equals("") || Password.equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please enter all lines!", Toast.LENGTH_LONG).show();
                }else {
                    mAuth.createUserWithEmailAndPassword(Email,Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        //SignUp Passed
                        public void onSuccess(AuthResult authResult) {
                            /////////////////////////////////////////
                            /////////////////////////////////////////
                            Map<String, Object> logUser = new HashMap<>();
                            logUser.put("First Name",Firstname);
                            logUser.put("Last Name",Lastname);
                            logUser.put("Number",Number);
                            logUser.put("Email",Email);

                            db.collection("logUser")
                                    .add(logUser)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(SignUpActivity.this,"User logged data base. Welcome the AtaTech.",Toast.LENGTH_LONG).show();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpActivity.this,"Failed! Please contact alpertunademirbas@gmail.com",Toast.LENGTH_LONG).show();

                                }
                            });


                            Intent intent = new Intent(SignUpActivity.this,ProfileActivity.class);


                            startActivity(intent);
                            finish();

                        }
                        //SignUp Failed
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(SignUpActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                        }
                    });
                }


            }
        });


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    public void agreementClicked (View view){

        Intent intent = new Intent(SignUpActivity.this,UserAgreementActivity.class);
        startActivity(intent);
    }



}