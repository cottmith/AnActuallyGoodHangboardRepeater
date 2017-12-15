package com.example.scottsmith.anactuallygoodhangboardrepeater;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class setWorkout extends AppCompatActivity {

    EditText sets;
    EditText reps;
    EditText onTime;
    EditText offTime;
    EditText restTime;
    int warningTime = 15;
    EditText workoutName;

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sets = (EditText)findViewById(R.id.sets);
        reps = (EditText)findViewById(R.id.reps);
        onTime = (EditText)findViewById(R.id.onTime);
        offTime = (EditText)findViewById(R.id.offTime);
        restTime = (EditText)findViewById(R.id.restTime);
        workoutName = (EditText)findViewById(R.id.workoutName);

        start = (Button)findViewById(R.id.startButton);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Workout w = new Workout(Integer.parseInt(sets.getText().toString()),
                        Integer.parseInt(reps.getText().toString()),
                        Integer.parseInt(onTime.getText().toString()),
                        Integer.parseInt(offTime.getText().toString()),
                        Integer.parseInt(restTime.getText().toString()),
                        warningTime, workoutName.getText().toString());

            }
        });

    }

}
