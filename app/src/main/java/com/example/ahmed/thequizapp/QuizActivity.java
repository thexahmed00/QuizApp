package com.example.ahmed.thequizapp;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Ahmed
 */
public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final String TAG = "second activity";
   public   long COUNTDOWN_IN_MILLIS;
   private long time=0;
    private String diff=StartingScreenActivity.EXTRA_DIFFICULTY;
    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewQuestion;
    private MediaPlayer mp1,mp2,mp3;
    private TextView textViewScore;
    private TextView textDifficulty;
    private TextView textViewQuestionCount;

    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;


    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getSupportActionBar().hide();
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textDifficulty=findViewById(R.id.textDifficulty);
      mp3=MediaPlayer.create(this,R.raw.tick);
      mp1=MediaPlayer.create(this,R.raw.accomplished);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        Intent intent=getIntent();

        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4=findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();



       // Intent intent=getIntent();
        String difficulty=intent.getStringExtra(diff);
        textDifficulty.setText("Difficulty :"+difficulty);

        if (savedInstanceState == null) {

            QuizDbHelper dbHelper = new QuizDbHelper(this);
            questionList = dbHelper.getQuestions(difficulty,10,true);
            questionCountTotal =questionList.size();
            Collections.shuffle(questionList);
            //questionList.subList(0,10);

            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            time = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered) {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {

        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

            startCountDown();
        } else {
            finishQuiz();
        }
    }

    public int startCountDown() {
        Log.d(TAG, "startCountDown: ");
        Intent intent=getIntent();
        String difficulty=intent.getStringExtra(diff);
        if(difficulty.equals("Hard"))

        {

            timeLeftInMillis = 10900;
        }
        else if (difficulty.equals("Medium")){
            timeLeftInMillis=20000;

        }
        else {
            timeLeftInMillis=30000;
        }
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1500) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                time=0;

                updateCountDownText();
                rbGroup.setVisibility(View.INVISIBLE);
                textViewQuestion.setText("Game Over");

                buttonConfirmNext.setVisibility(View.INVISIBLE);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mp3.stop();
                        finishQuiz();
                    }
                },2000);

               //
            }
        }.start();
        //checkAnswer();
        return 0;
    }

    public void updateCountDownText() {
        //mp3=MediaPlayer.create(this,R.raw.tick);
       // int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d", seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 7000) {
            mp3.start();
            textViewCountDown.setTextColor(Color.RED);

        }else
         {

            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    private void checkAnswer() {
        answered = true;
        mp3.pause();
        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText("Score: " + score);
        }

        showSolution();
    }

    private void showSolution() {
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        mp1=MediaPlayer.create(this,R.raw.accomplished);
        mp2=MediaPlayer.create(this,R.raw.wrong);
        int ans=currentQuestion.getAnswerNr();
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                if(answerNr == currentQuestion.getAnswerNr()) {
                    mp1.start();
                    rb1.setTextColor(Color.GREEN);
                    textViewQuestion.setText("correct");
                }
                else{
                    rb1.setTextColor(Color.GREEN);
                    mp2.start();
                    textViewQuestion.setText("Wrong \nCorrect is: option "+ans);
                }
                break;
            case 2:
                if(answerNr == currentQuestion.getAnswerNr()){
                    mp1.start();
                    rb2.setTextColor(Color.GREEN);
                    textViewQuestion.setText("correct");
                    }
                else{
                    rb2.setTextColor(Color.GREEN);
                    mp2.start();
                    textViewQuestion.setText("Wrong \nCorrect is:option "+ans);
                }
                break;
            case 3:
                if(answerNr == currentQuestion.getAnswerNr()){
                    mp1.start();
                    rb3.setTextColor(Color.GREEN);
                    textViewQuestion.setText("correct");

                }
                else{
                    rb3.setTextColor(Color.GREEN);
                    mp2.start();
                    textViewQuestion.setText("Wrong \n,Correct is: option "+ans);
                }
                break;
            case 4:
                if(answerNr == currentQuestion.getAnswerNr()){
                    mp1.start();
                    rb4.setTextColor(Color.GREEN);
                    textViewQuestion.setText("correct");
                   }
                else{
                    rb4.setTextColor(Color.RED);
                    mp2.start();
                    textViewQuestion.setText("Wrong \nCorrect is: option "+ans);
                }
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void finishQuiz() {
        mp3.stop();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if(mp3.isPlaying()){
            mp3.stop();
            mp3.release();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, time);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}
