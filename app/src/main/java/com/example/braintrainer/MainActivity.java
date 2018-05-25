package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start;
    Button grid_btn_0;
    Button grid_btn_1;
    Button grid_btn_2;
    Button grid_btn_3;
    Button playAgain;
    TextView message;
    TextView sum;
    TextView score;
    TextView timer;
    ConstraintLayout layoutGame;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int tries = 0;
    int corrects = 0;
    boolean isGameActive = false;

    public void start(View view){
        start.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        layoutGame.setVisibility(View.VISIBLE);
        message.setText("");
        isGameActive = true;
        score.setText("0/0");
        tries = 0;
        corrects = 0;

        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long milisLeft){
                timer.setText(Integer.toString((int) milisLeft / 1000) + "s");
            }

            @Override
            public void onFinish(){
                isGameActive = false;
                message.setText("Done!");
                timer.setText("0s");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void checkAnswer(View view) {
        if (isGameActive) {
            if (locationOfCorrectAnswer == Integer.parseInt(view.getTag().toString())) {
                message.setText("Correct");
                corrects++;
            } else {
                message.setText("Wrong");
            }

            tries++;
            score.setText(Integer.toString(corrects) + "/" + Integer.toString(tries));
            newRound();
        }
    }

    private void newRound(){
        int a = genNum(21);
        int b = genNum(21);

        sum.setText(String.valueOf(a) + " + " + String.valueOf(b));

        locationOfCorrectAnswer = genNum(4);

        answers.clear();

        for(int i = 0; i < 4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a + b);
            } else {
                int wrongPosition = genNum(41);

                while(wrongPosition == a + b){
                    wrongPosition = genNum(4);
                }

                answers.add(wrongPosition);
            }
        }

        grid_btn_0.setText(Integer.toString(answers.get(0)));
        grid_btn_1.setText(Integer.toString(answers.get(1)));
        grid_btn_2.setText(Integer.toString(answers.get(2)));
        grid_btn_3.setText(Integer.toString(answers.get(3)));
    }

    private int genNum(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.btn_go);
        layoutGame = (ConstraintLayout) findViewById(R.id.layout_game);
        grid_btn_0 = (Button) findViewById(R.id.grid_btn_0);
        grid_btn_1 = (Button) findViewById(R.id.grid_btn_1);
        grid_btn_2 = (Button) findViewById(R.id.grid_btn_2);
        grid_btn_3 = (Button) findViewById(R.id.grid_btn_3);
        message = (TextView) findViewById(R.id.txt_message);
        sum = (TextView) findViewById(R.id.txt_sum);
        score = (TextView) findViewById(R.id.txt_score);
        timer = (TextView) findViewById(R.id.txt_timer);
        playAgain = (Button) findViewById(R.id.btn_playAgain);

        newRound();
    }
}