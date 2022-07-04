package com.grimlin31.buddywalkowner.FormRegister;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.grimlin31.buddywalkowner.DataManager.Firebase.Auth.FAuthRegisterDelegate;
import com.grimlin31.buddywalkowner.DataManager.Firebase.Auth.FireAuthHelper;
import com.grimlin31.buddywalkowner.DataManager.Firebase.Firestore.FirestoreHelper;
import com.grimlin31.buddywalkowner.Model.Token.TokenModel;
import com.grimlin31.buddywalkowner.Model.UserOwner.UserOwnerModel;

import java.util.HashMap;
import java.util.Map;

public class FormRegisterInteractor
        implements FormRegisterContracts.Interactor,
        FAuthRegisterDelegate, FormRegisterContracts.DataManagerOutput {

    private Map<String, String> userOwnerModel;

    private boolean registerComplete;

    private FormRegisterContracts.InteractorOutput interactorOutput;
    private FormRegisterContracts.DataManager dataManager;

    // puede ir conexion con firebase
    FireAuthHelper fireAuthHelper;

    FormRegisterInteractor(FormRegisterContracts.InteractorOutput presenter, Context context) {
        // architecture implementation
        this.interactorOutput = presenter;
        this.dataManager = new FormRegisterDataManager(this, context);

        this.fireAuthHelper = new FireAuthHelper();
        this.fireAuthHelper.initializeRegisterDelegate(this);

        this.registerComplete = false;
        this.userOwnerModel = new HashMap();
    }

    @Override
    public void saveRegisterData(Map<String, String> data, int numberFragment) {
        this.userOwnerModel.putAll(data);
        switch (numberFragment) {
            case 1:
                this.fireAuthHelper.createUser(data.get("email"), data.get("pass"));
                break;
            case 2:
                this.dataManager.saveUser(this.userOwnerModel);
                break;
            default:
        }
    }

    @Override
    public void unRegister() {
        this.interactorOutput = null;
    }

    // Function FAuthRegisterDelegate
    @Override
    public void successRegister(TokenModel tokenModel) {
        this.userOwnerModel.put("id", tokenModel.getUidUser());
        this.interactorOutput.firebaseRegisterSuccess();
    }

    @Override
    public void failureRegister(Exception err) {
        this.interactorOutput.firebaseRegisterFailure(err);
    }

    @Override
    public void saveUserSuccess() {
        this.interactorOutput.saveUserSuccess();
    }

    @Override
    public void saveUserFailure(Throwable e) {
        this.interactorOutput.saveUserFailure(e);
    }
}
