package com.grimlin31.buddywalkowner.Login;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.grimlin31.buddywalkowner.DataManager.Firebase.Auth.FAuthDelegate;
import com.grimlin31.buddywalkowner.DataManager.Firebase.Auth.FireAuthHelper;
import com.grimlin31.buddywalkowner.DataManager.Local.TokenSQLHelper.TokenSQL;
import com.grimlin31.buddywalkowner.Model.Token.Specifications.FindByIdSpecification;
import com.grimlin31.buddywalkowner.Model.Token.TokenModel;
import com.grimlin31.buddywalkowner.Model.Token.TokenRepository;
import com.grimlin31.buddywalkowner.Model.UserOwner.Specifications.FindByEmailSpecification;
import com.grimlin31.buddywalkowner.Model.UserOwner.UserOwnerModel;

public class LoginInteractor
        implements LoginContracts.Interactor,
        LoginContracts.DataManagerOutput,
        FAuthDelegate {

    private String TAG = "LOGIN_Interactor";

    private LoginContracts.InteractorOutput outputPresenter;
    private LoginContracts.DataManager dataManager;

    private FireAuthHelper mAuth;


    LoginInteractor(
            LoginContracts.InteractorOutput presenter,
            Context context
    ) {

        this.outputPresenter = presenter;
        this.dataManager = new LoginDataManager(this, context);

        this.mAuth = new FireAuthHelper();
        this.mAuth.initializeAuthDelegate(this);
    }

    @Override
    public void loginUser(String user, String pass) {

        this.mAuth.authenticate(user, pass);

    }

    @Override
    public void unRegister() {
        this.outputPresenter = null;
    }

    //Firebase authentication delegate functions
    @Override
    public void successAuth(TokenModel tokenModel) {
        this.dataManager.setTokenDB(tokenModel);
        this.dataManager.loginUser(tokenModel);
    }

    @Override
    public void failureAuth(Exception err) {
        Log.i(TAG, "Delegate have worked");
        this.outputPresenter.loginResultFail(err);
    }

    // DataManagerOutput functions.
    @Override
    public void successfulLoadedData(UserOwnerModel userOwnerModel) {
        this.outputPresenter.loginResultSuccess();
    }

    @Override
    public void failureLoadedData(Throwable error) {
        this.outputPresenter.loginResultFail(error);
    }
}
