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
    Button btn_grid_0;
    Button btn_grid_1;
    Button btn_grid_2;
    Button btn_grid_3;
    TextView timer;
    TextView sum;
    TextView score;
    TextView output;
    Button playAgain;
    android.support.v7.widget.GridLayout grid_results;
    ConstraintLayout layoutGame;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    boolean isGameActive = false;
    int locationOfCorrectAnswer;
    int corrects = 0;
    int tries = 0;

    public void start(View view){
        start.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        output.setVisibility(View.INVISIBLE);
        layoutGame.setVisibility(View.VISIBLE);

        corrects = 0;
        tries = 0;
        score.setText("0/0");

        isGameActive = true;

        newRound();

        new CountDownTimer(30000 + 100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish(){
                isGameActive = false;
                timer.setText("0s");
                playAgain.setVisibility(View.VISIBLE);
                output.setText("Done!");
                output.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view){
        if(isGameActive) {
            if (locationOfCorrectAnswer == Integer.parseInt(view.getTag().toString())){
                output.setText("Correct");
                corrects++;
            } else {
                output.setText("Wrong :(");
            }
            tries++;
            score.setText(Integer.toString(corrects) + "/" + Integer.toString(tries));
            score.setVisibility(View.VISIBLE);
            output.setVisibility(View.VISIBLE);
            newRound();
        }
    }

    private void newRound(){
        int a = generateNumber(21);
        int b = generateNumber(21);

        sum.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = generateNumber(4);

        answers.clear();

        for(int i = 0; i < 4; i++){
            if(i == locationOfCorrectAnswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = generateNumber(41);

                // if the random number is the same as a+b, generate new random number
                while(wrongAnswer == a+b){
                    wrongAnswer = generateNumber(41);
                }

                answers.add(wrongAnswer);
            }
        }

        btn_grid_0.setText(Integer.toString(answers.get(0)));
        btn_grid_1.setText(Integer.toString(answers.get(1)));
        btn_grid_2.setText(Integer.toString(answers.get(2)));
        btn_grid_3.setText(Integer.toString(answers.get(3)));
    }

    private int generateNumber(int max){
        Random num = new Random();
        return num.nextInt(max);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.btn_start);
        btn_grid_0 = (Button) findViewById(R.id.grid_btn_0);
        btn_grid_1 = (Button) findViewById(R.id.grid_btn_1);
        btn_grid_2 = (Button) findViewById(R.id.grid_btn_2);
        btn_grid_3 = (Button) findViewById(R.id.grid_btn_3);
        timer = (TextView) findViewById(R.id.txt_timer);
        sum = (TextView) findViewById(R.id.txt_sum);
        score = (TextView) findViewById(R.id.txt_score);
        grid_results = (android.support.v7.widget.GridLayout) findViewById(R.id.grid_answers);
        output = (TextView) findViewById(R.id.txt_output);
        playAgain = (Button) findViewById(R.id.btn_playAgain);
        layoutGame = (ConstraintLayout) findViewById(R.id.layout_game);
    }
}