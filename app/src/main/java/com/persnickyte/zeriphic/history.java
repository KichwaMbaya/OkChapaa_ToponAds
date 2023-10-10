package com.persnickyte.zeriphic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class history extends AppCompatActivity {

    Button logout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        AdsManager.LoadBannerAd(this);
        AdsManager.LoadInterstitialAd(this);
        logout = findViewById(R.id.logout);


        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Integer status = sharedPreferences.getInt("logged", 0);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                Integer logged_in = 1;
                editor.putInt("logged", logged_in);
                editor.commit();

                Toast.makeText(history.this, "Logged Out!", Toast.LENGTH_SHORT).show();

                AdsManager.ShowInterstitialAd(history.this, MainActivity.class);

                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
}