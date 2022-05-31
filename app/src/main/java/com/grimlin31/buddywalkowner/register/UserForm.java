package com.grimlin31.buddywalkowner.register;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.grimlin31.buddywalkowner.R;

public class UserForm extends AppCompatActivity {

    private Spinner petSelector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        this.setSpinner(R.id.pet_spinner);

    }

    @Override
    protected void onResume() {
        super.onResume();

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    private void setSpinner(int idSelector) {
        this.petSelector = findViewById(idSelector);

        String[] pets = {"Cat", "Dog"};

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this,
                R.layout.item_spinner,
                pets
        );
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);

        this.petSelector.setAdapter(adapter);
    }

}
