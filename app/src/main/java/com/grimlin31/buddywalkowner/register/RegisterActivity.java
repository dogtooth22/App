package com.grimlin31.buddywalkowner.register;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.grimlin31.buddywalkowner.BuddyWalkerAppCompactActivity;
import com.grimlin31.buddywalkowner.LoginActivity;
import com.grimlin31.buddywalkowner.R;

public class RegisterActivity extends BuddyWalkerAppCompactActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void nextFormDataUser(View view)  {
        super.nextActivity(UserForm.class);
    }

    public void toLogin(View view)  {
        super.nextActivity(LoginActivity.class);
    }

}
