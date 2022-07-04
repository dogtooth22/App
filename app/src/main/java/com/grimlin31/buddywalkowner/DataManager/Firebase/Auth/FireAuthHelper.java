package com.grimlin31.buddywalkowner.DataManager.Firebase.Auth;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.grimlin31.buddywalkowner.DataManager.Firebase.Firestore.FStoreCreateDelegate;
import com.grimlin31.buddywalkowner.DataManager.Firebase.Firestore.FStoreGetDelegate;
import com.grimlin31.buddywalkowner.Model.Token.TokenModel;

import java.util.concurrent.Executor;

public class FireAuthHelper implements FAuthDelegate, FAuthRegisterDelegate {

    private String TAG = "FIREBASE_AUTH";
    private FirebaseAuth firebaseAuth;

    private FAuthDelegate authDelegate;
    private FAuthRegisterDelegate registerDelegate;


    public FireAuthHelper(){
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public void initializeAuthDelegate(FAuthDelegate authDelegate) {
        this.authDelegate = authDelegate;
    }

    public void initializeRegisterDelegate(FAuthRegisterDelegate registerDelegate) {
        this.registerDelegate = registerDelegate;
    }

    public void authenticate(String user, String pass) {
        this.firebaseAuth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(task -> {
                    Log.i(TAG, "login User func");
                    if (task.isSuccessful()) {
                        Log.i(TAG, "Sign in Successful");
                        FirebaseUser firebaseUser = this.firebaseAuth.getCurrentUser();
                        TokenModel token = new TokenModel(user, pass, firebaseUser.getUid());
                        // Send to delegate
                        this.authDelegate.successAuth(token);
                    }
                    else {
                        Log.w(TAG, "Interactor: Sign in Failure");
                        // Send to delegate failure
                        this.authDelegate.failureAuth(task.getException());
                    }
                });
    }

    public void createUser(String email, String pass) {
        this.firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        // here we go
                        TokenModel tokenModel = new TokenModel(
                                email,
                                pass,
                                user.getUid()
                        );

                        successRegister(tokenModel);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        failureRegister(task.getException());
                    }
                });
    }

    // Delegate Functions
    @Override
    public void successAuth(TokenModel tokenModel) {
        this.authDelegate.successAuth(tokenModel);
    }

    @Override
    public void failureAuth(Exception err) {
        this.authDelegate.failureAuth(err);
    }

    @Override
    public void successRegister(TokenModel tokenModel) {
        this.registerDelegate.successRegister(tokenModel);
    }

    @Override
    public void failureRegister(Exception err) {
        this.registerDelegate.failureRegister(err);
    }
}
