package com.grimlin31.buddywalkowner;

import android.content.Intent;
import android.os.Bundle;

import com.grimlin31.buddywalkowner.parentClass.BuddyWalkerAppCompactActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashView extends BuddyWalkerAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashView.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 5000);

    }
}
