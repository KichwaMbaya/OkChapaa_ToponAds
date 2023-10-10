package com.persnickyte.zeriphic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class tenure_period extends AppCompatActivity {

    static RadioButton months3, months4, months5, months6, months8, months10, months12;
    RadioGroup radioGroup1;
    TextView repayment, tit;
    Button applydonkey;
    String undeni;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenure_period);
        AdsManager.LoadInterstitialAd(this);
        AdsManager.LoadBannerAd(this);

        months3 = (RadioButton) findViewById(R.id.months3);
        months4 = (RadioButton) findViewById(R.id.months4);
        months5 = (RadioButton) findViewById(R.id.months5);
        months6 = (RadioButton) findViewById(R.id.months6);
        months8 = (RadioButton) findViewById(R.id.months8);
        months10 = (RadioButton) findViewById(R.id.months10);
        months12 = (RadioButton) findViewById(R.id.months12);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        repayment = (TextView) findViewById(R.id.repayment);
        applydonkey = findViewById(R.id.applydonkey);
        tit = (TextView) findViewById(R.id.tit);


        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String string = sharedPreferences.getString("amountapplied", "");

        tit.setText("KSH. " + string);


        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (!string.isEmpty()){

                    int ienteramount = Integer.parseInt(string);
                    //tit.setText(string);

                    if (tenure_period.this.months3.isChecked()) {

                        double rate = 0.12;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (tenure_period.this.months4.isChecked()) {

                        double rate = 0.16;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (tenure_period.this.months5.isChecked()) {

                        double rate = 0.2;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (tenure_period.this.months6.isChecked()) {

                        double rate = 0.24;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (tenure_period.this.months8.isChecked()) {

                        double rate = 0.32;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (tenure_period.this.months10.isChecked()) {

                        double rate = 0.37;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (tenure_period.this.months12.isChecked()) {

                        double rate = 0.45;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }

                    double vale = Double.parseDouble((undeni));
                    int deni = (int) (5 * Math.round(vale/5));
                    repayment.setText("Amount due repay : Ksh. " + deni);
                }
            }
        });
        applydonkey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (tenure_period.this.months3.isChecked() || tenure_period.this.months4.isChecked() || tenure_period.this.months5.isChecked()
                        || tenure_period.this.months6.isChecked() || tenure_period.this.months8.isChecked()
                        || tenure_period.this.months10.isChecked() || tenure_period.this.months12.isChecked()) {

                    final ProgressDialog progressDialog = new ProgressDialog(tenure_period.this);
                    progressDialog.setTitle("Please wait, it takes less than a minute");
                    progressDialog.setMessage("Submitting your loan request.");
                    progressDialog.setProgressStyle(0);
                    progressDialog.setMax(100);
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(11000);
                                AdsManager.ShowInterstitialAd(tenure_period.this, catch_page.class);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    }).start();
                    return;
                }
                Toast.makeText(tenure_period.this, "Select period", Toast.LENGTH_SHORT).show();
            }
        });
    }
}