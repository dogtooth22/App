package com.grimlin31.buddywalkowner.DataManager.Firebase.Auth;

import com.google.firebase.auth.FirebaseUser;
import com.grimlin31.buddywalkowner.Model.Token.TokenModel;

public interface FAuthRegisterDelegate {
    void successRegister(TokenModel tokenModel);
    void failureRegister(Exception err);
}
