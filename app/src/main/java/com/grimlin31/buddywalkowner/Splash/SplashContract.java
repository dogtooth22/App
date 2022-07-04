package com.grimlin31.buddywalkowner.Splash;

import android.app.Activity;

public class SplashContract {

    interface ActivityController {
        void loadAnimation();
    }

    interface Presenter {
        void onFinishAnimation();
    }

    interface Router {
        void goLogin();
    }

}
