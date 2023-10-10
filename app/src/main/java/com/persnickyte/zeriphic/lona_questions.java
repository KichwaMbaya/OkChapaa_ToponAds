package com.persnickyte.zeriphic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class lona_questions extends AppCompatActivity {

    RadioButton crbyes, crbno, maried, single, notsay, primary, secondary, college, undergraduate, postgraduate, optional, notoptional;
    Spinner spinnerquestions;
    Button next_questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lona_questions);
        AdsManager.LoadInterstitialAd(this);
        AdsManager.LoadBannerAd(this);

        next_questions = findViewById(R.id.next_questions);
        spinnerquestions = findViewById(R.id.spinnerquestions);
        crbyes = findViewById(R.id.crbyes);
        crbno = findViewById(R.id.crbno);
        maried = findViewById(R.id.maried);
        single = findViewById(R.id.single);
        notsay = findViewById(R.id.notsay);
        primary = findViewById(R.id.primary);
        secondary = findViewById(R.id.secondary);
        college = findViewById(R.id.college);
        undergraduate = findViewById(R.id.undergraduate);
        postgraduate = findViewById(R.id.postgraduate);
        optional = findViewById(R.id.optional);
        notoptional = findViewById(R.id.notoptional);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.counties, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerquestions.setAdapter(arrayAdapter);

        next_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!lona_questions.this.crbyes.isChecked() && !lona_questions.this.crbno.isChecked()) {
                    Toast.makeText(lona_questions.this, "Check all the Questions", Toast.LENGTH_SHORT).show();
                    return;
                } if (!lona_questions.this.maried.isChecked() && !lona_questions.this.single.isChecked() && !lona_questions.this.notsay.isChecked()) {
                    Toast.makeText(lona_questions.this, "Check all the Questions", Toast.LENGTH_SHORT).show();
                    return;
                } if (!lona_questions.this.primary.isChecked() && !lona_questions.this.secondary.isChecked() && !lona_questions.this.college.isChecked() && !lona_questions.this.undergraduate.isChecked() && !lona_questions.this.postgraduate.isChecked()){
                    Toast.makeText(lona_questions.this, "Check all the Questions", Toast.LENGTH_SHORT).show();
                    return;
                } if (!lona_questions.this.optional.isChecked() && !lona_questions.this.notoptional.isChecked()){
                    Toast.makeText(lona_questions.this, "Check all the Questions", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    final ProgressDialog progressDialog = new ProgressDialog(lona_questions.this);
                    progressDialog.setTitle("Wait a minute");
                    progressDialog.setMessage("Saving...");
                    progressDialog.setProgressStyle(0);
                    progressDialog.setMax(100);
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(7000);//9000
                                AdsManager.ShowInterstitialAd(lona_questions.this, lona_limit.class);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    }).start();
                }
            }
        });
    }
}