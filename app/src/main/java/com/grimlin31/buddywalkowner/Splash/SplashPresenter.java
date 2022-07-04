package com.grimlin31.buddywalkowner.Splash;

import com.grimlin31.buddywalkowner.Main.BuddyWalkerAppCompactActivity;

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.Router router;

    SplashPresenter(SplashContract.ActivityController activityController ){
        this.router = new SplashRouter((BuddyWalkerAppCompactActivity) activityController);
    }


    @Override
    public void onFinishAnimation() {
        this.router.goLogin();
    }
}
