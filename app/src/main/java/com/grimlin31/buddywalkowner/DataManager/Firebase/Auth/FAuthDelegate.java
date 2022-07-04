package com.grimlin31.buddywalkowner.DataManager.Firebase.Auth;

import com.grimlin31.buddywalkowner.Model.Token.TokenModel;

public interface FAuthDelegate {
    void successAuth(TokenModel tokenModel);
    void failureAuth(Exception err);
}
