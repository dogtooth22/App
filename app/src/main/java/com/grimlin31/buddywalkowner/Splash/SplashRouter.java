package com.grimlin31.buddywalkowner.Splash;

import com.grimlin31.buddywalkowner.Login.LoginActivityController;
import com.grimlin31.buddywalkowner.Main.BuddyWalkerAppCompactActivity;

public class SplashRouter implements SplashContract.Router {

    BuddyWalkerAppCompactActivity activity;

    SplashRouter(BuddyWalkerAppCompactActivity activity){
        this.activity = activity;
    }

    @Override
    public void goLogin() {
        this.activity.nextActivity(LoginActivityController.class);
    }
}
