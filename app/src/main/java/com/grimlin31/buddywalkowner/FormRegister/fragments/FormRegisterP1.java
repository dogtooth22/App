package com.grimlin31.buddywalkowner.FormRegister.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.grimlin31.buddywalkowner.R;

import java.util.HashMap;
import java.util.Map;

public class FormRegisterP1 extends Fragment {

    private EditText userNameInput;
    private EditText emailInput;
    private EditText passInput;
    private EditText passConfirmInput;


    public FormRegisterP1() {

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View fragment = inflater.inflate(
                R.layout.fragment_form_register_p1,
                container,
                false
        );

        this.userNameInput = fragment.findViewById(R.id.userName);
        this.emailInput = fragment.findViewById(R.id.email);
        this.passInput = fragment.findViewById(R.id.pass);
        this.passConfirmInput = fragment.findViewById(R.id.passConfirm);

        return fragment;
    }

    public Map<String, String> getDataInputs() {

        Map<String, String> data = new HashMap<String, String>();

        data.put("username", this.userNameInput.getText().toString());
        data.put("email", this.emailInput.getText().toString());
        data.put("pass", this.passInput.getText().toString());
        data.put("confirmedPass", this.passConfirmInput.getText().toString());

        return data;
    }
}