package com.grimlin31.buddywalkowner.register;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.grimlin31.buddywalkowner.parentClass.BuddyWalkerAppCompactActivity;
import com.grimlin31.buddywalkowner.Presentation.LoginActivity;
import com.grimlin31.buddywalkowner.R;

public class UserForm extends BuddyWalkerAppCompactActivity {

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
    }

    private void setSpinner(int idSelector) {
        this.petSelector = findViewById(idSelector);

        String[] pets = {"Select One","Cat", "Dog"};

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this,
                R.layout.item_spinner,
                pets
        );
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);

        this.petSelector.setAdapter(adapter);
    }
    public void toLogin(View view)  {
        super.nextActivity(LoginActivity.class);
    }


}
