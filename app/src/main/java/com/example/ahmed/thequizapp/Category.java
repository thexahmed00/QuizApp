package com.example.ahmed.thequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Category extends AppCompatActivity {
    private String diff=StartingScreenActivity.EXTRA_DIFFICULTY;
    private static final int REQUEST_CODE_QUIZ = 1;
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
  private   ImageView de;
    private ImageView mp;
   private ImageView co;
   private ImageView ds;
   private CardView decard;
    Tag h;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
       /* de=findViewById(R.id.De);
        mp=findViewById(R.id.mp);
        co=findViewById(R.id.co);
        ds=findViewById(R.id.cds);
        */
      de=findViewById(R.id.imageView1);
         decard = findViewById(R.id.one);
       de.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Log.d(String.valueOf(h), "onClick:card one ");
               Intent intent =new Intent(Category.this,QuizActivity.class);
               startActivityForResult(intent, REQUEST_CODE_QUIZ);
           }
       });
    }
}
