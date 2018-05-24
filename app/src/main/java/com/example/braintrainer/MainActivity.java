package com.example.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView start;
    TextView time;
    TextView task;
    TextView rounds;
    android.support.v7.widget.GridLayout grid_results;

    public void play(View view){

        start.setVisibility(View.INVISIBLE);
        time.setVisibility(View.VISIBLE);
        task.setVisibility(View.VISIBLE);
        rounds.setVisibility(View.VISIBLE);
        grid_results.setVisibility(View.VISIBLE);

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
    }
}