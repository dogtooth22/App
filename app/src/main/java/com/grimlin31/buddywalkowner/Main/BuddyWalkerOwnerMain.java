package com.grimlin31.buddywalkowner.Main;

import android.os.Bundle;

import com.grimlin31.buddywalkowner.Splash.SplashActivityController;

public class BuddyWalkerOwnerMain extends BuddyWalkerAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.nextActivity(SplashActivityController.class);
    }
}
