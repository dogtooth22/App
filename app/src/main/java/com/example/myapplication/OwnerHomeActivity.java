package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.main.MainActivity;
import com.example.myapplication.walker.WalkerMenu;
import com.example.myapplication.main.SecondActivity;
import com.example.myapplication.main.ThirdActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myapplication.SaveSharedPreferences.SavedSharedPreference;
import com.google.firebase.auth.FirebaseAuth;

public class OwnerHomeActivity extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {

    MainActivity   firstActivity = new MainActivity();
    SecondActivity secondActivity = new SecondActivity();
    ThirdActivity  thirdActivity = new ThirdActivity();
    private static final int TAG_CODE_PERMISSION_LOCATION = 1;
    private String userIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        Intent intent = getIntent();
        userIndex = intent.getStringExtra("userIndex");

        Bundle bundle = new Bundle();
        bundle.putString("userIndex", userIndex);
        firstActivity.setArguments(bundle);
        thirdActivity.setArguments(bundle);

        BottomNavigationView navigation = findViewById(R.id.home_navigator);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(!checkPermission())
        {
            askPermission();
        }
        loadFragment(firstActivity);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.firstActivity:
                    loadFragment(firstActivity);
                    return true;
                case R.id.secondActivity:
                    loadFragment(secondActivity);
                    return true;
                case R.id.thirdActivity:
                    loadFragment(thirdActivity);
                    return true;
            }
            return false;
        }
    };


    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment);
        transaction.commit();
    }

    @Override
    protected void onResume(){
        super.onResume();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        if(SavedSharedPreference.getUserName(OwnerHomeActivity.this).length() == 0){
            finish();
        }
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        return result == PackageManager.PERMISSION_GRANTED;
    }
    private void askPermission() {
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                200);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200: {
                // Si el request es cancelado entonces el arreglo tiene largo 0
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //se aceptaron los permisos, hacer algo
                } else {
                    //se rechazaron los permisos, hacer otra cosa
                    Toast.makeText(getApplicationContext(), "Localization access is needed to use this app.", Toast.LENGTH_SHORT);
                    SavedSharedPreference.clearUserName(OwnerHomeActivity.this);
                    finish();
                }

                return;
            }
        }
    }

    public void toLogout(View view) {
        SavedSharedPreference.clearUserName(OwnerHomeActivity.this);
        FirebaseAuth.getInstance().signOut();
        finish();
        System.exit(0);
    }

    // TODO creo que esto no es necesario

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Intent intent = new Intent(this, WalkerMenu.class);
        String message = marker.getId();
        intent.putExtra("Hola", message);
        startActivity(intent);

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {

            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
        }
    }
}