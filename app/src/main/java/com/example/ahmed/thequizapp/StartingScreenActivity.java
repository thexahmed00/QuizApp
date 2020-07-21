package com.example.ahmed.thequizapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.os.CountDownTimer;
//import android.support.annotation.NonNull;

import android.os.Bundle;
//import androidx.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class StartingScreenActivity extends AppCompatActivity {
    private static final String TAG = "Main screen";
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_DIFFICULTY="extraDifficulty";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
 private    MediaPlayer mp;
    private TextView textViewHighscore;
    private Spinner spinnerDifficulty;
    private ImageView imageView3;
    private ImageView line;

    private int highscore;
//    String difficulty=spinnerDifficulty.getSelectedItem().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       mp=MediaPlayer.create(this,R.raw.intuition);

        textViewHighscore = findViewById(R.id.text_view_highscore);
        imageView3=findViewById(R.id.imageView3);
        line=findViewById(R.id.line);
        loadHighscore();
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        imageView3.startAnimation(animFadeIn);
        spinnerDifficulty=findViewById(R.id.spinner_Difficulty);
        //String difficulty=spinnerDifficulty.getSelectedItem().toString();
        String[] difficultyLevels=Question.getAllDifficultyLevels();
        ArrayAdapter<String> adapterDifficulty=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,difficultyLevels) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return  true;
                }

            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view =super.getDropDownView(position, convertView, parent);
                TextView tv= (TextView) view;
                if(position==0){
                    tv.setTextColor(Color.GRAY);

                }else{
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
      buttonStartQuiz.startAnimation(animFadeIn);

        spinnerDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String name=spinnerDifficulty.getSelectedItem().toString();
                if(i>0)
                Toast.makeText(StartingScreenActivity.this,"Selected "+name,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           if (spinnerDifficulty.getSelectedItem().equals("Select Difficulty")){
               Toast.makeText(StartingScreenActivity.this,"Please select Difficulty",Toast.LENGTH_LONG).show();
           }
           // }
                mp.start();
              //  startQuiz();
                Intent intent=new Intent(StartingScreenActivity.this,Category.class);
                startActivity(intent);
            }
        });

    }

  /*  public void startQuiz() {
            //QuizActivity t =new QuizActivity();
            String difficulty=spinnerDifficulty.getSelectedItem().toString();
             Intent intent = new Intent(StartingScreenActivity.this, Category.class);



            intent.putExtra(EXTRA_DIFFICULTY,difficulty);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }

    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about) {
            Intent intent = new Intent(this, about.class);
            startActivity(intent);
        }

        return true;
    }

}
