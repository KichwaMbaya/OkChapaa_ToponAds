package com.persnickyte.zeriphic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button mainlogin, mainregist;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdsManager.LoadInterstitialAd(this);
        mainlogin = findViewById(R.id.mainlogin);
        mainregist = findViewById(R.id.mainregist);


        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Integer status = sharedPreferences.getInt("logged", 0);



        if (status == 2){

            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
            finish();
        }

        mainlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsManager.ShowInterstitialAd(MainActivity.this, login.class);
            }
        });

        mainregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsManager.ShowInterstitialAd(MainActivity.this, register.class);
            }
        });
    }
}