package com.grimlin31.buddywalkowner.Presentation;

import android.content.Intent;
import android.os.Bundle;

import com.grimlin31.buddywalkowner.R;
import com.grimlin31.buddywalkowner.parentClass.BuddyWalkerAppCompactActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashView extends BuddyWalkerAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashView.this, OwnerHomeActivity.class);
                startActivity(intent);
                finish();
            }

        };

        Timer time = new Timer();
        time.schedule(task, 5000);

    }
}
