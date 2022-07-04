package com.grimlin31.buddywalkowner.FormRegister;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.fragment.app.FragmentTransaction;

import com.grimlin31.buddywalkowner.Main.BuddyWalkerAppCompactActivity;
import com.grimlin31.buddywalkowner.R;
import com.grimlin31.buddywalkowner.FormRegister.fragments.FormRegisterP1;
import com.grimlin31.buddywalkowner.FormRegister.fragments.FormRegisterP2;

import java.util.HashMap;
import java.util.Map;

public class FormRegisterActivityController
        extends BuddyWalkerAppCompactActivity
        implements FormRegisterContracts.ActivityController {

    FormRegisterContracts.Presenter presenter;

    FragmentTransaction transaction;
    FormRegisterP1 formRegister1;
    FormRegisterP2 formRegister2;

    Button next;
    Button cancel;
    private ProgressBar proBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Set architecture
        this.presenter = new FormRegisterPresenter(this);

        // Set items
        this.next = findViewById(R.id.next);
        this.cancel = findViewById(R.id.cancel);
        this.proBar = findViewById(R.id.progressBar);

        // Set Fragments
        formRegister1 = new FormRegisterP1();
        formRegister2 = new FormRegisterP2();

        this.loaded();
        getSupportFragmentManager()
                .beginTransaction()
                .add(
                        R.id.form_register_frag_container,
                        formRegister1
                ).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
        this.presenter = null;
    }

    @Override
    public void nextRegisterStep(View view) {
        int fragmentNumber = this.presenter.getShowFragment();
        this.loading();

        Map<String, String> data = this.getDataFromFragment(fragmentNumber);

        if(!isFormComplete(data)){
            Error err = new Error(getResources().getResourceName(R.string.incomplete_form));
            this.showError(err);
            this.loaded();
            return;
        }
        this.presenter.pressContinuesButton(data);
    }

    @Override
    public void backRegisterStep(View view) {
        this.presenter.pressCancelButton();
    }

    @Override
    public void showFragment(int number) {
        this.transaction = getSupportFragmentManager().beginTransaction();
        switch (number) {
            case 1:
                this.transaction.replace(
                        R.id.form_register_frag_container,
                        formRegister1
                ).commit();
                this.next.setText(R.string.next);
                this.cancel.setText(R.string.cancel);
                break;
            case 2:
                this.transaction.replace(
                        R.id.form_register_frag_container,
                        formRegister2
                ).commit();
                this.next.setText(R.string.save);
                this.cancel.setText(R.string.back);
                break;
            default:
                Error err = new Error("Something was wrong");
                this.showError(err);
                Log.e("FORM_REGISTER", "Fragment Number Does Not Exist in showFragment");
        }
        this.transaction = null;
    }

    @Override
    public void showError(Throwable err) {
        super.showToastError(err);
    }

    public Map<String, String> getDataFromFragment(int numbFrg) {
        switch (numbFrg){
            case 1:
                return this.formRegister1.getDataInputs();
            case 2:
                return this.formRegister2.getFormInputs();
            default:
                Log.e("FORM_REGISTER", "Fragment Number Does Not Exist in getDataFromFragment");
                return new HashMap<>();
        }
    }



    private boolean isFormComplete(Map<String, String> form){
        boolean hasNull = form.values().contains(null);
        return !hasNull;
    }

    public void loading() {
        this.proBar.setVisibility(View.VISIBLE);
        this.next.setEnabled(false);
        this.cancel.setEnabled(false);
    }

    public void loaded() {
        this.proBar.setVisibility(View.GONE);
        this.next.setEnabled(true);
        this.cancel.setEnabled(true);
    }

    @Override
    public String getStringValue(int id) {
        return super.getStringValue(id);
    }
}
