package com.grimlin31.buddywalkowner.FormRegister;

import android.app.Activity;
import android.util.Log;

import com.grimlin31.buddywalkowner.Home.Home;
import com.grimlin31.buddywalkowner.Login.LoginActivityController;
import com.grimlin31.buddywalkowner.Main.BuddyWalkerAppCompactActivity;
import com.grimlin31.buddywalkowner.R;
import com.grimlin31.buddywalkowner.Utils.InputString;

import java.util.Map;

public class FormRegisterPresenter
        implements FormRegisterContracts.Presenter,
        FormRegisterContracts.InteractorOutput {

    FormRegisterContracts.ActivityController activityController;
    FormRegisterContracts.Router router;
    FormRegisterContracts.Interactor interactor;

    private int MAX_FRAGMENT = 2;
    private int MIN_FRAGMENT = 1;
    private int showFragment;

    FormRegisterPresenter(FormRegisterContracts.ActivityController viewController){
        this.activityController = viewController;
        this.showFragment = 1;

        this.interactor = new FormRegisterInteractor(
                this,
                (Activity) activityController
        );
        this.router = new FormRegisterRouter(
                (BuddyWalkerAppCompactActivity) this.activityController
        );

    }

    @Override
    public void onDestroy() {
        this.router.unRegister();
        this.router = null;
        this.interactor.unRegister();
        this.interactor = null;

        this.activityController = null;
    }

    @Override
    public void pressContinuesButton(Map<String, String> data) {
        switch (this.showFragment){
            case 1:
                boolean isPassConfirmed = InputString.isPassConfirmed(
                        data.get("pass"),
                        data.get("confirmedPass")
                );

                boolean isValidMail = InputString.isEmailValid(data.get("email"));
                boolean isValidPass = InputString.isPassValid(data.get("pass"));

                if (!isPassConfirmed && isValidPass && isValidMail) {
                    Error err = new Error(this.activityController.getStringValue(R.string.incomplete_form));
                    this.activityController.showError(err);
                    return;
                }
                interactor.saveRegisterData(data, showFragment);
                this.showFragment += 1;
                break;
            case 2:
                interactor.saveRegisterData(data, showFragment);
                break;
            default:
        }
    }

    @Override
    public void pressCancelButton() {
        this.showFragment -= 1;
        if (this.showFragment < MIN_FRAGMENT){
            this.showFragment = MIN_FRAGMENT;
            // go Login;
            Log.i(
                    "FORM_REGISTER",
                    "Show Fragment Number: " + this.showFragment);
            this.router.goTo(LoginActivityController.class);
            return;
        }
        this.activityController.showFragment(this.showFragment);
    }

    public int getShowFragment() {
        return showFragment;
    }

    @Override
    public void firebaseRegisterSuccess() {
        this.activityController.showFragment(this.showFragment);
        // desactivar spinner
        this.activityController.loaded();
    }

    @Override
    public void firebaseRegisterFailure(Throwable err) {
        this.showFragment -= 1;
        this.activityController.showError(err);
        this.activityController.loaded();
    }

    @Override
    public void saveUserSuccess() {
        this.router.goTo(LoginActivityController.class);
    }

    @Override
    public void saveUserFailure(Throwable e) {
        this.showFragment -= 1;
        this.activityController.showError(e);
        this.activityController.loaded();
    }
}
