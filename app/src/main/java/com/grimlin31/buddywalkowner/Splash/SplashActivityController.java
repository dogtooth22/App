package com.grimlin31.buddywalkowner.Splash;

import android.os.Bundle;

import com.grimlin31.buddywalkowner.R;
import com.grimlin31.buddywalkowner.Main.BuddyWalkerAppCompactActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivityController
        extends BuddyWalkerAppCompactActivity
        implements SplashContract.ActivityController {

    private SplashContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenter(this);

        loadAnimation();

    }

    public void loadAnimation() {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                presenter.onFinishAnimation();
                finish();
            }
        };

        Timer time = new Timer();
        time.schedule(task, 5000);
    }
}
