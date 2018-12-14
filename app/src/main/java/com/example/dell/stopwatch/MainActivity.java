package com.example.dell.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState!=null) {
           seconds = savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("state");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("state", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);



    }

    public void onStart(View view) {
        running = true;
        wasRunning =true;

    }

    public void onPause(View view) {
        running = false;

    }

    public void onReset(View view) {
        running = false;
        seconds = 0;

    }

    @Override
    protected void onPause(){
        wasRunning= running;
        running=false;
        super.onPause();
    }
    @Override
    protected void onResume() {
        if (wasRunning) {
            running = true;
        }

        super.onResume();
    }
    public void runTimer(){
        final TextView timer_view = findViewById(R.id.timer_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timer_view.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed( this, 1000 );
            }
        });




    }

}
