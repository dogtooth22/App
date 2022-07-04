package com.grimlin31.buddywalkowner.FormRegister;

import android.view.View;

import com.grimlin31.buddywalkowner.Model.UserOwner.UserOwnerModel;

import java.util.Map;

public class FormRegisterContracts {

    interface ActivityController {
        void nextRegisterStep(View view);
        void backRegisterStep(View view);
        void showFragment(int number);
        String getStringValue(int id);
        void showError(Throwable err);
        void loaded();
        void loading();
    }

    interface Presenter {
        void onDestroy();
        void pressContinuesButton(Map<String, String> data);
        void pressCancelButton();
        int getShowFragment();
    }

    interface Interactor {
        void saveRegisterData(Map<String, String> data, int numberFragment);
        void unRegister();
    }

    interface InteractorOutput {
        void firebaseRegisterSuccess();
        void firebaseRegisterFailure(Throwable err);
        void saveUserSuccess();
        void saveUserFailure(Throwable e);
    }

    interface Router {
        void goTo(Class cls);
        void unRegister();
    }

    interface DataManager {
        void saveUser(Map<String, String> data);
        void unRegister();
    }

    interface DataManagerOutput {
        void saveUserSuccess();
        void saveUserFailure(Throwable e);
    }

}
