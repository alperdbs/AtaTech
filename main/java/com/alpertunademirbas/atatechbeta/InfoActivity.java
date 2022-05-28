package com.alpertunademirbas.atatechbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    Button btn;
    TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
        setContentView(R.layout.activity_info);
        btn=findViewById(R.id.button90);
        textView=findViewById(R.id.textView9);
        textView2=findViewById(R.id.textView10);


    }
    public void gohomeclicked (View view){
        Intent goMainScreen = new Intent(InfoActivity.this,ProfileActivity.class);
        startActivity(goMainScreen);
    }

}