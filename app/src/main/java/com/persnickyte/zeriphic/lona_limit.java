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

public class lona_limit extends AppCompatActivity {

    TextView limit_limit;
    Button limitapply;
    EditText loan_amount;
    String limit = "0";
    int chec = 0;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lona_limit);
        AdsManager.LoadInterstitialAd(this);
        AdsManager.LoadBannerAd(this);

        limit_limit = findViewById(R.id.limit_limit);
        limitapply = findViewById(R.id.limitapply);
        loan_amount = findViewById(R.id.loan_amount);

        checking();

        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        limitapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String limit = limit_limit.getText().toString();
                String amount = loan_amount.getText().toString();

                if (limit.isEmpty()){
                    limit_limit.setError("Check your limit");
                    Toast.makeText(lona_limit.this, "Click CHECK LIMIT", Toast.LENGTH_LONG).show();
                    return;
                }
                if (amount.isEmpty()) {
                    loan_amount.setError("Enter an amount");
                    Toast.makeText(lona_limit.this, "Please enter the amount you want to apply", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!amount.isEmpty()){

                    int ienteramount = Integer.parseInt(amount.toString());

                    if (ienteramount > 6500) {
                        loan_amount.setError("Your limit is KSH. 6500");
                        Toast.makeText(lona_limit.this, "Your limit is KSH 6500", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (ienteramount < 500) {
                        loan_amount.setError("Least amount is KSH. 500");
                        Toast.makeText(lona_limit.this, "You cannot apply less than KSH 500", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                final String amountapplies = String.valueOf(lona_limit.this.loan_amount.getText());
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("amountapplied", amountapplies);
                edit.commit();

                final ProgressDialog progressDialog = new ProgressDialog(lona_limit.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setProgressStyle(0);
                progressDialog.setMax(100);
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(5000);//7000
                            AdsManager.ShowInterstitialAd(lona_limit.this, tenure_period.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();
            }
        });
    }
    private void checking(){
        final ProgressDialog progressDialog = new ProgressDialog(lona_limit.this);
        progressDialog.setTitle("Checking Servers");
        progressDialog.setMessage("Calculating Your Limit...");
        progressDialog.setProgressStyle(0);
        progressDialog.setMax(100);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(7000);//7000
                    lona_limit.this.limit = "KSH: 6,500";
                    chec = 1;
                    limit_limit.setText(lona_limit.this.limit);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }
}