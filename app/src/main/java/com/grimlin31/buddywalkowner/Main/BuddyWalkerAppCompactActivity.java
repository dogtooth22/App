package com.grimlin31.buddywalkowner.Main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BuddyWalkerAppCompactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void nextActivity(Class<?> cls) {
        Intent nextActivity= new Intent(this, cls);
        startActivity(nextActivity);
    }

    public void nextActivity(Class<?> cls, Object data){
        Intent nextActivity= new Intent(this, cls);
        nextActivity.putExtra("USER", (Bundle) data);
        startActivity(nextActivity);
    };

    protected void showToastError(Throwable err){
        Toast toast = Toast.makeText(getApplicationContext(), err.getMessage(), Toast.LENGTH_LONG);
        toast.show();
    }

    public String getStringValue(int id) {
        return getString(id);
    }
}
