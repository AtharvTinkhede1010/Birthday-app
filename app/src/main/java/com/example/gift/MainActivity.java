package com.example.gift;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gift.data.giftdbhelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    static int sd = 0;
    int ho = 0, mi = 0, S = 0;
    TextView T1;
    giftdbhelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbHandler = new giftdbhelper(this, null, null, 1);
        dbHandler.addHandler();
        Loadingscreen();
        Timeholder();
    }

    void Loadingscreen() {
        WebView myWebView = (WebView) findViewById(R.id.loading);
        myWebView.loadUrl("file:///android_asset/loadingscreen.html");
    }

    void Timeholder() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                T1 = (TextView) findViewById(R.id.CurrentTime);
                Date instant = new Date();
                final Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                @SuppressLint("SimpleDateFormat") String HH = new SimpleDateFormat("HH").format(instant);
                @SuppressLint("SimpleDateFormat") String MM = new SimpleDateFormat("mm").format(instant);
                @SuppressLint("SimpleDateFormat") String SS = new SimpleDateFormat("ss").format(instant);
                @SuppressLint("SimpleDateFormat") String D = new SimpleDateFormat("dd").format(instant);
                @SuppressLint("SimpleDateFormat") String M = new SimpleDateFormat("M").format(instant);
                @SuppressLint("SimpleDateFormat") String Y = new SimpleDateFormat("yyyy").format(instant);
                int H = Integer.parseInt(HH);
                int Min = Integer.parseInt(MM);
                int s = Integer.parseInt(SS);
                int Mon = Integer.parseInt(M);
                final int Day = Integer.parseInt(D);
                int Year = Integer.parseInt(Y);
                if (6 >= Day) {                              //6
                    ho = 23 - H;                            //23
                    mi = 59 - Min;                          //59
                    S = 59 - s;                             //59
                } else {
                    ho = 0;
                    mi = 0;
                    S = 0;
                }
                if (sd == 1 || dbHandler.loadhander()) {
                    ho = 0;
                    mi = 0;
                    S = 0;
                }
                int time = (ho * 60 * 60 + mi * 60 + S) * 1000;
                /* if (day > 18 && Mon > 4 && year > 2020) {
                    ho = 19 - H;
                    mi = 01 - Min;
                    S = 00 - s;
                } else {
                    if (day == 18 && Mon == 4 && year == 2020) {
                        ho = 19 - H;
                        mi = 01 - Min;
                        S =  00 - s;
                    } else {
                        ho = 0;
                        mi = 0;
                        S = 0;
                    }
                }*/
                //timeleft(ho * 60 * 60 + mi * 60 + S);
                WebView webView = (WebView) findViewById(R.id.loading);
                webView.setVisibility(View.INVISIBLE);
                final TextView _tv = (TextView) findViewById(R.id.textView1);
                new CountDownTimer(time, 1000) {
                    @SuppressLint("SetTextI18n")
                    public void onTick(long millisInFuture) {
                        //String a=new SimpleDateFormat("HH:mm:ss").format(new Date( millisInFuture));
                        int Hour = (int) (millisInFuture / 1000) / 60 / 60 % 24;
                        int minutes = (int) (millisInFuture / 1000) / 60 % 60;
                        int seconds = (int) (millisInFuture / 1000) % 60;

                        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", Hour, minutes, seconds);
                        _tv.setText("Time remaining: " + timeLeftFormatted);
                    }

                    @SuppressLint("SetTextI18n")
                    public void onFinish() {
                        dbHandler.updateHandler();
                        sd = 1;
                        startActivity(homeIntent);
                        finish();
                    }
                }.start();
            }
        }, SPLASH_TIME_OUT);
    }
}
    /*void timeleft(int sec) {
        if (year >= 2020 || sy == 1) {
            sy = 1;
            T1.setText("year");
            if (Mon >= 04 || sm == 1) {
                sm = 1;
                T1.setText("Mon");
                if (day >= 17 || sd == 1) {
                    sd = 1;
                    T1.setText("day");
                    if (H >= 00 || sh == 1 || day > 16) {
                        sh = 1;
                        T1.setText("hour");
                        startActivity(homeIntent);
                        finish();
                    }
                }
            }
        }

    }*/