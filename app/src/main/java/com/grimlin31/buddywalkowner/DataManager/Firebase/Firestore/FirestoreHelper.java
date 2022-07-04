package com.grimlin31.buddywalkowner.DataManager.Firebase.Firestore;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.grimlin31.buddywalkowner.Model.Specification;
import java.util.Map;

public class FirestoreHelper
        implements FStoreCreateDelegate, FStoreGetDelegate {

    String TAG = "FIREBASE_HELPER";

    private String nameCollection;
    private FirebaseFirestore firestore;

    private CollectionReference collection;
    private FStoreCreateDelegate createDelegate;
    private FStoreGetDelegate getDelegate;

    public FirestoreHelper(
            String collection
    ) {
        this.nameCollection = collection;
        this.firestore = FirebaseFirestore.getInstance();

        this.collection = firestore.collection(this.nameCollection);
    }

    public void getOneItem(Specification specification, Class cls){

        DocumentReference docRef = this.collection.document(specification.getValue().toString());

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        getSuccess(document.toObject(cls));
                    } else {
                        getFailure(new Error("User does not exist"));
                    }
                } else {
                    getFailure(new Error(task.getException()));
                }
            }
        });

    }

    public void createItem(String id, Map data){
        collection.document(id).set(data)
                .addOnSuccessListener(
                        documentReference -> {
                            Log.d(
                                    TAG,
                                    "DocumentSnapshot added with ID: " + documentReference
                            );
                            this.createSuccess();
                        }

                )
                .addOnFailureListener(
                        e -> {
                            Log.w(
                                    TAG,
                                    "Error adding document",
                                    e
                            );
                            this.createFailure(e);
                        }
                );
    }

    public void initializeGetDelegate(FStoreGetDelegate getDelegate) {
        this.getDelegate = getDelegate;
    }

    public void initializeCreateDelegate(FStoreCreateDelegate createDelegate) {
        this.createDelegate = createDelegate;
    }

    @Override
    public void createSuccess() {
        this.createDelegate.createSuccess();
    }

    @Override
    public void createFailure(Throwable e) {
        this.createDelegate.createFailure(e);
    }

    @Override
    public void getSuccess(Object document) {
        this.getDelegate.getSuccess(document);
    }

    @Override
    public void getFailure(Throwable e) {
        this.getDelegate.getFailure(e);
    }

}
