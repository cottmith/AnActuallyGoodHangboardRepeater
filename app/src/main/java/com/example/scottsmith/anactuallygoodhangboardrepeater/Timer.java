package com.example.scottsmith.anactuallygoodhangboardrepeater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Timer extends AppCompatActivity {

    public final int ON = 0;
    public final int OFF = 1;
    public final int REST = 2;

    private final int DEFAULTTIME = 10;
    private final int ONESECOND = 1000;

    private Button start;
    private Button pause;
    private TextView timer;
    private TextView state;
    private CountDownTimer countDownTimer;

    private boolean paused = false;
    private boolean started = false;

    private int setTime = DEFAULTTIME;
    private int timeLeft = DEFAULTTIME;

    private Notifications notif;


    public String dtag = "---DEBUG OUT---";

    private Workout w;
    private boolean onTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        Intent intent = getIntent();
        this.w = intent.getParcelableExtra("workout");
        initUI();


    }

    private void initUI(){
        timeLeft = w.getOnTime();

        //Create button instances
        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);
        timer = (TextView) findViewById(R.id.timer);
        state = (TextView) findViewById(R.id.state);

        timer.setText(getFormatedTime());
        state.setText("State");
        onTime = true;

        //Create listener
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paused = !paused;
                if (paused){
                    pause.setText("UNPAUSE");
                } else {
                    pause.setText("PAUSE");
                }
            }
        });

        //Init Media
        notif = new Notifications(getApplicationContext());
    }



    private void startTimer(){
        if (!started) {
            resetTimer();
            countDownTimer.start();
            started = true;
        }
    }

    private String getFormatedTime(){
        String minute = String.format("%02d", timeLeft/60);
        String second = String.format("%02d", timeLeft%60);
        return minute+":"+second;
    }

    private void resetTimer(){
        //System.out.print(timeLeft);
        timer.setText(getFormatedTime());

        countDownTimer = new CountDownTimer(ONESECOND, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (!paused) {
                    timeLeft -= 1;
                    if (timeLeft == 0) {
                        if (w.isFinished()) {
                            timer.setText("DONE!");
                            countDownTimer.cancel();
                            countDownTimer = null;
                            started = false;
                            state.setText("DONE!");
                        } else {
                            timeLeft = w.updateState();
                            Log.d("State:", Integer.toString(w.getState()));
                            updateStateTV();
                            started = false;
                            startTimer();
                            w.printWorkout();
                        }
                    } else if (timeLeft == w.getWarnTime() && w.getState() == REST) {
                        notif.playWarn();
                        resetTimer();
                        countDownTimer.start();
                    } else {
                        resetTimer();
                        countDownTimer.start();
                    }
                } else {
                    resetTimer();
                    countDownTimer.start();
                }
            }
        };
    }

    private void updateStateTV(){
        if (w.getState() == ON) {
            notif.playOn();
            state.setText("ON Time");
        } else if (w.getState() == OFF) {
            notif.playOff();
            state.setText("OFF Time");
        } else {
            notif.playRest();
            state.setText("REST");
        }
    }
}
