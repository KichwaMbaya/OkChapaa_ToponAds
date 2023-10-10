package com.persnickyte.zeriphic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class application extends AppCompatActivity {

    Button continueapplic;
    Spinner spinnerapplic;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        AdsManager.LoadInterstitialAd(this);
        AdsManager.LoadBannerAd(this);
        continueapplic = findViewById(R.id.continueapplic);
        spinnerapplic = findViewById(R.id.spinnerapplic);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.purpose, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerapplic.setAdapter(arrayAdapter);

        continueapplic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(application.this);
                progressDialog.setMessage("Saving loan purpose...");
                progressDialog.setProgressStyle(0);
                progressDialog.setMax(100);
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(4000);
                            AdsManager.ShowInterstitialAd(application.this, lona_questions.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();
            }
        });
    }
}