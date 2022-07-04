package com.grimlin31.buddywalkowner.FormRegister;

import com.grimlin31.buddywalkowner.Main.BuddyWalkerAppCompactActivity;

public class FormRegisterRouter
        implements FormRegisterContracts.Router{

    private BuddyWalkerAppCompactActivity activityController;

    FormRegisterRouter(BuddyWalkerAppCompactActivity viewController) {
        this.activityController = viewController;
    }

    @Override
    public void goTo(Class cls) {
        this.activityController.nextActivity(cls);
    }

    @Override
    public void unRegister() {
        this.activityController = null;
    }
}
