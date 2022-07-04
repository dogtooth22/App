package com.grimlin31.buddywalkowner.FormRegister.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.grimlin31.buddywalkowner.DataManager.Local.UserSQLHelper.UserContractsDataBase;
import com.grimlin31.buddywalkowner.Model.UserOwner.UserOwnerModel;
import com.grimlin31.buddywalkowner.R;

import java.util.HashMap;
import java.util.Map;

public class FormRegisterP2 extends Fragment {

    private Spinner petSelector;

    private EditText nameInput;
    private EditText addressInput;
    private EditText phoneInput;


    public FormRegisterP2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(
                R.layout.fragment_form_register_p2,
                container,
                false
        );

        this.nameInput = fragment.findViewById(R.id.name_input);
        this.addressInput = fragment.findViewById(R.id.address_input);
        this.phoneInput = fragment.findViewById(R.id.phone_input);

        this.petSelector = fragment.findViewById(R.id.pet_spinner);
        this.setSpinner();
        return fragment;
    }

    private void setSpinner() {;

        String[] pets = {"Select One","Cat", "Dog"};

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                getActivity(),
                R.layout.item_spinner,
                pets
        );
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);

        this.petSelector.setAdapter(adapter);
    }

    public Map<String, String> getFormInputs() {

        Map<String, String> data = new HashMap<String, String>();

        data.put(UserContractsDataBase.columnName, this.nameInput.getText().toString());
        data.put(UserContractsDataBase.columnAddress, this.addressInput.getText().toString());
        data.put(UserContractsDataBase.columnPhone, this.phoneInput.getText().toString());
        String spinnerSelected = this.petSelector.getSelectedItem().toString();

        if (spinnerSelected.equalsIgnoreCase("Select One")) {
            spinnerSelected = " ";
        }

        data.put(UserContractsDataBase.columnTypePet, spinnerSelected);

        return data;
    }
}