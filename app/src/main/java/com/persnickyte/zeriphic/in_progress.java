package com.persnickyte.zeriphic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class in_progress extends AppCompatActivity {

    Button okayprogress;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_progress);

        okayprogress = findViewById(R.id.okayprogress);

        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String occupied2 = sharedPreferences.getString("last_name", "");

        okayprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (occupied2.isEmpty()){
                    Intent intent = new Intent(in_progress.this, history.class);
                    startActivity(intent);
                }else {
                    onBackPressed();
                }
            }
        });
    }
}