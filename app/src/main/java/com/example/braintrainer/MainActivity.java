package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView start;
    TextView time;
    TextView task;
    TextView rounds;
    TextView output;
    Button playAgain;
    android.support.v7.widget.GridLayout grid_results;
    CountDownTimer cntDwn;
    boolean isGameActive = false;

    public void play(View view){

        start.setVisibility(View.INVISIBLE);
        time.setVisibility(View.VISIBLE);
        task.setVisibility(View.VISIBLE);
        rounds.setVisibility(View.VISIBLE);
        grid_results.setVisibility(View.VISIBLE);
        isGameActive = true;

        cntDwn = new CountDownTimer(30000 + 100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(String.valueOf(millisUntilFinished /1000) + "s");
            }

            @Override
            public void onFinish(){
                isGameActive = false;
                time.setText("0s");
                playAgain.setVisibility(View.VISIBLE);
                output.setText("Done!");
                output.setVisibility(View.VISIBLE);
            }
        }.start();

        if(isGameActive){
            int operand1 = generateNumber(20);
            int operand2 = generateNumber(20);
            int result = operand1 + operand2;
        }
    }

    private int generateNumber(int max){
        Random num = new Random();
        return num.nextInt(max);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (TextView) findViewById(R.id.txt_start);
        time = (TextView) findViewById(R.id.txt_time);
        task = (TextView) findViewById(R.id.txt_task);
        rounds = (TextView) findViewById(R.id.txt_triesNrounds);
        grid_results = (android.support.v7.widget.GridLayout) findViewById(R.id.grid_results);
        output = (TextView) findViewById(R.id.txt_output);
        playAgain = (Button) findViewById(R.id.btn_playAgain);
    }
}