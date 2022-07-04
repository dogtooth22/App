package com.grimlin31.buddywalkowner.Login;

import android.content.Context;
import android.util.Log;

import com.grimlin31.buddywalkowner.DataManager.Firebase.Firestore.FStoreGetDelegate;
import com.grimlin31.buddywalkowner.DataManager.Firebase.Firestore.FirestoreHelper;
import com.grimlin31.buddywalkowner.Model.Token.TokenRepository;
import com.grimlin31.buddywalkowner.Model.UserOwner.Specifications.FindByEmailSpecification;
import com.grimlin31.buddywalkowner.Model.Token.TokenModel;
import com.grimlin31.buddywalkowner.Model.UserOwner.UserOwnerModel;
import com.grimlin31.buddywalkowner.Model.UserOwner.UserOwnerRepository;

public class LoginDataManager
        implements LoginContracts.DataManager,
        FStoreGetDelegate {

    private String TAG = "Login_Data_Manager".toUpperCase();

    private LoginContracts.DataManagerOutput outputInteractor;
    private UserOwnerModel userOwnerModel;
    private FirestoreHelper firestoreHelper;

    private UserOwnerRepository userOwnerRepository;

    LoginDataManager(
            LoginContracts.DataManagerOutput interactor,
            Context context
    ){
        this.outputInteractor = interactor;

        this.firestoreHelper = new FirestoreHelper(
                userOwnerModel.collectionDB
        );

        this.firestoreHelper.initializeGetDelegate(this);

        this.userOwnerRepository = new UserOwnerRepository(context);
    }

    @Override
    public void unRegister() {
        this.outputInteractor = null;
        this.userOwnerRepository.unRegister();
    }

    @Override
    public void loginUser(TokenModel token) {
        UserOwnerModel userOwnerModel = this.userOwnerRepository.findBySpecification(
                new FindByEmailSpecification(token.getUser())
        );

        if (userOwnerModel == null) {
            this.firestoreHelper.getOneItem(
                    new FindByEmailSpecification(token.getUidUser()), UserOwnerModel.class
            );
            return;
        }

        this.outputInteractor.successfulLoadedData(userOwnerModel);
    }

    @Override
    public void setTokenDB(TokenModel tokenModel) {
    }


    // Delegate FireStoreHelper
    @Override
    public void getSuccess(Object document) {
        if (document instanceof UserOwnerModel) {
            userOwnerRepository.deleteAll();
            this.userOwnerModel = (UserOwnerModel) document;
            long rowNumber = userOwnerRepository.addValue(this.userOwnerModel);
            this.outputInteractor.successfulLoadedData(this.userOwnerModel);
        }
    }

    @Override
    public void getFailure(Throwable e) {
        this.outputInteractor.failureLoadedData(e);
    }

}
