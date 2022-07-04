package com.grimlin31.buddywalkowner.DataManager.Firebase.Firestore;

import com.google.firebase.firestore.DocumentReference;

public interface FStoreCreateDelegate {
    void createSuccess();
    void createFailure(Throwable e);
}