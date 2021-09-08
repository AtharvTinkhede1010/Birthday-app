package com.example.gift;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class HomeActivity extends AppCompatActivity {
    private static int que = 0;
    private int page = 1;
    Button b1, b2;
    MenuItem m1;
    MediaPlayer mediaplayer;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        WebView myWebView = (WebView) findViewById(R.id.web);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebChromeClient(new WebChromeClient());
        change();
        setTitle("");
        play();
    }

    private void play() {
        mediaplayer = MediaPlayer.create(HomeActivity.this, R.raw.birthday);
        mediaplayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaplayer != null) {
            mediaplayer.release();
            mediaplayer = null;
        }
    }

    public void next(View view) {
        next();
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    public void next() {
        if (page < 2) {
            page++;
            change();
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.INVISIBLE);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "NO more pages", Toast.LENGTH_SHORT);
            toast.show();
        }
        change();
    }

    public void pre(View view) {
        pre();
    }

    public void pre() {
        if (page > 1) {
            page--;
            change();
            b2.setVisibility(View.VISIBLE);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "This is The first page", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void change() {
        WebView myWebView = (WebView) findViewById(R.id.web);
        if (page == 1) {
            myWebView.loadUrl("file:///android_asset/sample.html");
            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.VISIBLE);
        } else if (page == 2)
            myWebView.loadUrl("file:///android_asset/sample2.html");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_1, menu);
        m1=menu.findItem(R.id.action_play);
        DrawableCompat.setTint(DrawableCompat.wrap(m1.getIcon()), ContextCompat.getColor(this,R.color.textColorPrimary));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_play:
                if(mediaplayer==null)
                    play();
                if (mediaplayer.isPlaying()) {
                    mediaplayer.pause();
                    m1.setIcon(R.drawable.play);
                    m1.setTitle("Play");
                    DrawableCompat.setTint(DrawableCompat.wrap(m1.getIcon()), ContextCompat.getColor(this,R.color.textColorPrimary));
                } else {
                    mediaplayer.start();
                    m1.setIcon(R.drawable.pause);
                    m1.setTitle("Pause");
                    DrawableCompat.setTint(DrawableCompat.wrap(m1.getIcon()), ContextCompat.getColor(this,R.color.textColorPrimary));
                }
                //m1.setIcon(getResources().getDrawable(R.drawable.pause));
                //m1.setIcon(ContextCompat.getDrawable(this, R.drawable.play));
                return true;
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}