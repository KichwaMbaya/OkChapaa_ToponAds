package com.persnickyte.zeriphic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText lgnemail, lgnpin;
    Button lgnlogin;
    TextView lgncreate;
    SharedPreferences sharedPreferences;
    String emaillo, passworddo;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AdsManager.LoadBannerAd(this);
        AdsManager.LoadInterstitialAd(this);
        lgnemail = findViewById(R.id.lgnemail);
        lgnpin = findViewById(R.id.lgnpin);
        lgnlogin = findViewById(R.id.lgnlogin);
        lgncreate = findViewById(R.id.lgncreate);


        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Integer status = sharedPreferences.getInt("logged", 0);


        if (status == 2) {

            Intent intent = new Intent(login.this, finish.class);
            startActivity(intent);
            finish();
        }

        lgnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginacc();
            }
        });
        lgncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsManager.ShowInterstitialAd(login.this, register.class);
            }
        });
    }
    private void loginacc(){
        String emaillog = lgnemail.getText().toString();
        String lgnpinlog = lgnpin.getText().toString();

        emaillo = sharedPreferences.getString("phone_number", "0739152352");
        passworddo = sharedPreferences.getString("PIN", "0410");

        if (emaillog.isEmpty()){
            lgnemail.setError("Enter email");
            return;
        }
        if (emaillog.length() < 10){
            lgnemail.setError("Too Short");
            return;
        }
        if (emaillog.length() > 13){
            lgnemail.setError("Too Long");
            return;
        }
        if (lgnpinlog.isEmpty()){
            lgnpin.setError("Enter PIN");
            return;
        }
        if (lgnpinlog.length() < 4){
            lgnpin.setError("Too short");
            return;
        }
        if (!emaillog.equals(emaillo)){
            lgnemail.setError("Wrong Phone Number");
            Toast.makeText(login.this, "Phone number cannot be found", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!lgnpinlog.equals(passworddo)){
            lgnpin.setError("Wrong PIN");
            Toast.makeText(login.this, "Wrong PIN", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Integer logged_in = 2;
        editor.putInt("logged", logged_in);
        editor.commit();

        progressDialog = new ProgressDialog(login.this);
        progressDialog.setMessage("Logging in...");
        progressDialog.setProgressStyle(0);
        progressDialog.setMax(100);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                    AdsManager.ShowInterstitialAd(login.this, finish.class);
                   // finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }
}