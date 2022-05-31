package com.grimlin31.buddywalkowner;

import android.os.Bundle;
import android.view.View;

import com.grimlin31.buddywalkowner.parentClass.BuddyWalkerAppCompactActivity;
import com.grimlin31.buddywalkowner.register.RegisterActivity;

import java.util.TimerTask;

public class LoginActivity extends BuddyWalkerAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void toRegister(View view) {
        super.nextActivity(RegisterActivity.class);
    }
}