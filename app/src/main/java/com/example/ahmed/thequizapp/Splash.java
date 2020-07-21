package com.example.ahmed.thequizapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
//mport android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    private ImageView imageView;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        imageView=findViewById(R.id.imageView1);

        mediaPlayer = MediaPlayer.create(Splash.this,R.raw.intro);

        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        //imageView.startAnimation(animFadeIn);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.start();
                Intent intent=new Intent(Splash.this,StartingScreenActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}
