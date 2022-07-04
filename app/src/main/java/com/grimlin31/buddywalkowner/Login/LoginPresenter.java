package com.grimlin31.buddywalkowner.Login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.TableRow;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.grimlin31.buddywalkowner.Main.BuddyWalkerAppCompactActivity;
import com.grimlin31.buddywalkowner.R;
import com.grimlin31.buddywalkowner.Utils.InputString;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class LoginPresenter  implements LoginContracts.Presenter, LoginContracts.InteractorOutput {

    private LoginContracts.Router router;
    private LoginContracts.ActivityController activityController;
    private LoginContracts.Interactor interactor;

    LoginPresenter(LoginContracts.ActivityController activity){
        this.router = new LoginRouter((BuddyWalkerAppCompactActivity)  activity);
        this.interactor = new LoginInteractor(this, (Activity) activity);
        this.activityController = activity;
    };

    @Override
    public void onDestroy() {
        this.router.unRegister();
        this.router = null;
        this.interactor.unRegister();
        this.interactor = null;
    }

    @Override
    public void loginButtonClicked(String userName, String pass) {
        Log.i("LOGIN_Presenter", "login User func");
        if (!this.credentialsAreValid(userName, pass)) {
            String messages = this.activityController.getStringValue(R.string.credential_error);
            Error err = new Error(messages);
            activityController.showError(err);
            return;
        }
        interactor.loginUser(userName, pass);
        // Block login Button
        activityController.loading();
    }

    @Override
    public void registerButtonClicked() {
        this.router.goRegister();
    }

    @Override
    public void loginResultSuccess() {
        this.activityController.loaded();
    }

    @Override
    public void loginResultFail(Throwable error) {
        Log.w("FAILURE_LOGIN_FLOW", "Presenter: Sign in Failure");
        this.activityController.showError(error);
        this.activityController.loaded();
    }


    private boolean credentialsAreValid(String user, String pass) {
        ArrayList<Boolean> validate = new ArrayList();
        validate.add(InputString.isPassValid(pass));
        validate.add(InputString.isEmailValid(user));

        if (validate.contains(false)) return false;
        return true;
    }


}
