package com.alpertunademirbas.atatechbeta;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainScreenActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    ListView listView;
    ArrayList<users> usersArrayList = new ArrayList<>();
    //Data (User Names)
    users alper = new users("Alper Tuna Demirbaş");
    users gokay = new users("Gökay Bıçkıcı");
    users furkan = new users("Furkan Bayraktar");
    users fatihhan = new users("Fatihhan Karadeniz");
    users alperr = new users("Alper Kılıç");
    users nursena = new users("Nursena Usta");
    users nazif = new users("Nazif Mehmet Küçük");
    users pinar = new users("Pınar Alkan");
    users nida = new users("Nida Sevda");
    users selim = new users("Selim Batur Aşkın");
    users salih = new users("Muhammed Salih Saka");
    users emrullah = new users("Emrullah Öztürk");
    users emre = new users("Emre Enes Yenen");
    users yusuf = new users("Yusuf Güngör");
    users muratcan = new users("Muratcan Çiftçi");
    users sercan = new users("Sercan Can");


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        listView = findViewById(R.id.listView);
        listView.setSelector(android.R.color.black);

        auth = FirebaseAuth.getInstance();


        ////Add array list (users)
        usersArrayList.add(alper);
        usersArrayList.add(alperr);
        usersArrayList.add(emre);
        usersArrayList.add(emrullah);
        usersArrayList.add(fatihhan);
        usersArrayList.add(furkan);
        usersArrayList.add(gokay);
        usersArrayList.add(muratcan);
        usersArrayList.add(nazif);
        usersArrayList.add(nida);
        usersArrayList.add(nursena);
        usersArrayList.add(salih);
        usersArrayList.add(selim);
        usersArrayList.add(sercan);
        usersArrayList.add(pinar);
        usersArrayList.add(yusuf);


        findViewById(R.id.listView);

        //Adapter
        //ListView Adapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.list_user,
                usersArrayList.stream().map(users -> users.name).collect(Collectors.toList()));
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                users user = usersArrayList.get(i);
                if (user == null) {
                    // Make a Log here informing that the user object null
                    return; // Don't proceed
                }
                if (user.clicked){
                    Toast.makeText(MainScreenActivity.this,"You can only rate once!",Toast.LENGTH_LONG).show();
                    return; // This user is already clicked, don't proceed
                }
                // If the user is not clicked then set the clicked to true and proceed
                user.clicked = true;
                // You may want to save or update the modified user here


                Intent intent = new Intent(MainScreenActivity.this, PointActivity.class);
                intent.putExtra("Userr", usersArrayList.get(i));
                startActivity(intent);
            }
        });


    }


}