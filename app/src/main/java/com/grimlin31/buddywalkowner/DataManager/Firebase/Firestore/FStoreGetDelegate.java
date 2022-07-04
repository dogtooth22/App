package com.grimlin31.buddywalkowner.DataManager.Firebase.Firestore;

public interface FStoreGetDelegate<Model> {
    void getSuccess(Model document);
    void getFailure(Throwable e);
}
