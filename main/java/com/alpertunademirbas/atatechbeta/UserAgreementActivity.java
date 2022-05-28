package com.alpertunademirbas.atatechbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class UserAgreementActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);

        setContentView(R.layout.activity_user_agreement);
        btn=findViewById(R.id.button2);
        btn=findViewById(R.id.button5);
    }
    public void agreementClicked (View view){

        Intent intent = new Intent(UserAgreementActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
    public void iDontAgreeClicked (View view){

        Intent intent = new Intent(UserAgreementActivity.this,MainActivity.class);
        startActivity(intent);
    }
}