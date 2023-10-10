package com.persnickyte.zeriphic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class paid extends AppCompatActivity {

    Button paidrate, paidshare;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Give us a 5 star and a review").setCancelable(false).setPositiveButton("RATE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                paid.this.startActivity(new Intent("android.intent.action.VIEW",
                        Uri.parse("https://play.google.com/store/apps/details?id=com.persnickyte.zeriphic")));
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid);
        AdsManager.LoadBannerAd(this);

        paidrate = findViewById(R.id.paidrate);
        paidshare = findViewById(R.id.paidshare);

        paidrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paid.this.startActivity(new Intent("android.intent.action.VIEW",
                        Uri.parse("https://play.google.com/store/apps/details?id=com.persnickyte.zeriphic")));
            }
        });

        paidshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.TEXT", "For Quick and best Loan Terms. Follow the link below to download " +
                        "the app in play store and apply.Get Kes 500 when you share with a friend\n  " +
                        "https://play.google.com/store/apps/details?id=com.persnickyte.zeriphic");
                intent.setType("text/plain");
                paid.this.startActivity(Intent.createChooser(intent, (CharSequence) null));
            }
        });
    }
}