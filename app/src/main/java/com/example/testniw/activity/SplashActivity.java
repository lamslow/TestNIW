package com.example.testniw.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.testniw.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private TextView tvProgress;
    private ProgressBar progressBar;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                count++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvProgress.setText(count+"%");
                    }
                });
                progressBar.setProgress(count);
                if (count==100){
                    timer.cancel();
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
            }
        };
        timer.schedule(timerTask,0,100);
    }

    private void init(){
        tvProgress = (TextView) findViewById(R.id.tvProgress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }
}