package com.grimlin31.buddywalkowner.Login;

import android.view.View;

import com.google.firebase.auth.FirebaseUser;
import com.grimlin31.buddywalkowner.Model.Token.TokenModel;
import com.grimlin31.buddywalkowner.Model.UserOwner.UserOwnerModel;

public class LoginContracts {

    interface ActivityController {
        void registerUser(View view);
        void loginUser(View view);
        void showError(Throwable err);
        void loading();
        void loaded();
        String getStringValue(int idString);
    }

    interface Router {
        void goHome();
        void goRegister();
        void unRegister();
    }

    interface Presenter {
        void onDestroy();
        void loginButtonClicked(String userName, String pass);
        void registerButtonClicked();
    }

    interface Interactor {
        void loginUser(String user, String pass);
        void unRegister();
    }

    interface InteractorOutput {
        void loginResultSuccess();
        void loginResultFail(Throwable error);
    }

    interface DataManager {
        void unRegister();
        void loginUser(TokenModel token);
        void setTokenDB(TokenModel tokenModel);
    }

    interface DataManagerOutput {
        void successfulLoadedData(UserOwnerModel userOwnerModel);
        void failureLoadedData(Throwable error);
    }

}
