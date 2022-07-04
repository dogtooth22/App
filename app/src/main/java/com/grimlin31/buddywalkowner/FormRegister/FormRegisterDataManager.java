package com.grimlin31.buddywalkowner.FormRegister;

import android.content.Context;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.grimlin31.buddywalkowner.DataManager.Firebase.Firestore.FStoreCreateDelegate;
import com.grimlin31.buddywalkowner.DataManager.Firebase.Firestore.FirestoreHelper;
import com.grimlin31.buddywalkowner.DataManager.Local.UserSQLHelper.UserContractsDataBase;
import com.grimlin31.buddywalkowner.Model.UserOwner.UserOwnerModel;
import com.grimlin31.buddywalkowner.Model.UserOwner.UserOwnerRepository;

import java.util.Map;

public class FormRegisterDataManager implements
        FormRegisterContracts.DataManager,
        FStoreCreateDelegate {

    FormRegisterContracts.DataManagerOutput outputInteractor;

    UserOwnerRepository userOwnerRepository;

    FirestoreHelper firestoreHelper;

    public FormRegisterDataManager(
            FormRegisterContracts.DataManagerOutput interactor,
            Context context
    ) {
        this.outputInteractor = interactor;

        this.userOwnerRepository = new UserOwnerRepository(context);

        this.firestoreHelper = new FirestoreHelper(
                UserContractsDataBase.tableName
        );

        this.firestoreHelper.initializeCreateDelegate(this);
    }

    @Override
    public void saveUser(Map<String, String> data) {
        UserOwnerModel user = this.userOwnerRepository.castMapToClass(
                data,
                new UserOwnerModel()
        );
        // local save
        this.userOwnerRepository.deleteAll();
        this.userOwnerRepository.addValue(user);
        // fb save
        this.firestoreHelper.createItem(
                user.id,
                UserOwnerModel.convertObjToMapReflection(user)
        );
    }

    @Override
    public void unRegister() {
        this.userOwnerRepository = null;
        this.outputInteractor = null;
    }

    @Override
    public void createSuccess() {
        this.outputInteractor.saveUserSuccess();
    }

    @Override
    public void createFailure(Throwable e) {
        this.outputInteractor.saveUserFailure(e);
    }
}
