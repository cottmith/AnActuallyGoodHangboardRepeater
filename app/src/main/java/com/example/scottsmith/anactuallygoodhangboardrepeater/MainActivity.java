package com.example.scottsmith.anactuallygoodhangboardrepeater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText sets;
    EditText reps;
    EditText onTime;
    EditText offTime;
    EditText restTime;
    int warningTime = 15;
    EditText workoutName;

    Button start;

    public String dtag = "---DEBUG OUT---";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(dtag, "Whattttt");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initUI();
    }

    private void initUI(){
        setContentView(R.layout.main_activity);

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
                //w.printWorkout();

                startTimer(w);
            }
        });

        workoutName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (workoutName.getText().toString().equals("Workout Name")) {
                    workoutName.getText().clear();
                }
            }
        });

    }

    public void startTimer(Workout w){
        Intent i = new Intent(this, Timer.class);
        i.putExtra("workout", w);
        startActivity(i);
    }

}
