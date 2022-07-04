package com.grimlin31.buddywalkowner.Login;

import com.grimlin31.buddywalkowner.Home.Home;
import com.grimlin31.buddywalkowner.Main.BuddyWalkerAppCompactActivity;
import com.grimlin31.buddywalkowner.FormRegister.FormRegisterActivityController;

public class LoginRouter implements LoginContracts.Router {

    private BuddyWalkerAppCompactActivity viewController;

    LoginRouter(BuddyWalkerAppCompactActivity activity){
        this.viewController = activity;
    }

    @Override
    public void goHome() {
        this.viewController.nextActivity(Home.class);
    }

    @Override
    public void goRegister() {
        this.viewController.nextActivity(FormRegisterActivityController.class);
    }

    @Override
    public void unRegister() {
        this.viewController = null;
    }
}
