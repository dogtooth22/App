package com.grimlin31.buddywalkowner.Login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.grimlin31.buddywalkowner.R;
import com.grimlin31.buddywalkowner.Main.BuddyWalkerAppCompactActivity;

public class LoginActivityController
        extends BuddyWalkerAppCompactActivity
        implements LoginContracts.ActivityController {

    private static final String TAG = "LOGIN_AC";
    private LoginContracts.Presenter presenter;
    // Inputs
    private EditText email;
    private EditText pass;
    private Button loginButton;
    private Button googleLoginButton;


    private ProgressBar proBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.email = findViewById(R.id.email);
        this.pass = findViewById(R.id.pass);

        this.proBar = findViewById(R.id.progressBar);
        this.googleLoginButton = findViewById(R.id.google_button);
        this.loginButton = findViewById(R.id.login_button);

        this.loaded();

        presenter = new LoginPresenter(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    @Override
    public void loginUser(View view) {
        Log.i(TAG, "login User func");

        presenter.loginButtonClicked(
                this.email.getText().toString(),
                this.pass.getText().toString()
        );
    }

    @Override
    public void registerUser(View view) {
        Log.i(TAG, "go to register");
        this.presenter.registerButtonClicked();
    }

    @Override
    public void showError(Throwable err) {
        Log.w("FAILURE_LOGIN_FLOW2", "ActivityController: Show Error Function");
        super.showToastError(err);
    }

    @Override
    public void loading() {
        this.proBar.setVisibility(View.VISIBLE);
        this.loginButton.setEnabled(false);
        this.googleLoginButton.setEnabled(false);
        Log.i(TAG,"loading func");
    }

    @Override
    public void loaded() {
        this.proBar.setVisibility(View.GONE);
        this.loginButton.setEnabled(true);
        this.googleLoginButton.setEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public String getStringValue(int id) {
        return super.getStringValue(id);
    }

}