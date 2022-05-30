package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {

    private static final int TAG_CODE_PERMISSION_LOCATION = 1;
    private GoogleMap mMap;
    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
    FloatingActionButton FAB;
    List<Marker> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        FAB = findViewById(R.id.pasear);

    }
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            map.setMapStyle(new MapStyleOptions(getResources().getString(R.string.dia_json)));
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            map.setOnMarkerClickListener(this);
            map.getUiSettings().setMapToolbarEnabled(false);

            markers = new ArrayList<Marker>();
            Random r = new Random();
            int min = 1; int max = 10;
            int randomInt = (int) Math.floor(Math.random()*(max - min + 1) + min);
            double upper = 0.002; double lower = -0.002;
            for(int i = 0; i < randomInt; i++){
                double randomLat = upper + (upper - lower) * r.nextDouble(); double randomLng = upper + (upper - lower) * r.nextDouble();
                LatLng lugar = new LatLng(location.getLatitude() + randomLat, location.getLongitude() + randomLng);
                Marker marker = map.addMarker(new MarkerOptions().position(lugar)); marker.setTag("Marcador en: " + lugar.latitude + ", " + lugar.longitude);
                markers.add(marker);
            }

            /*FAB.setOnClickListener(v -> {
                for(int i = 0; i < 5; i++){
                    ArrayList<Marker> newMarkers = new ArrayList<Marker>();
                    for (Marker marker : markers) {
                        LatLng posicion = marker.getPosition();
                        double caminataUpper = 0.000005;
                        double caminataLower = -0.000005;
                        double caminataRndLat = caminataUpper + (caminataUpper - caminataLower) * r.nextDouble();
                        double caminataRndLng = caminataUpper + (caminataUpper - caminataLower) * r.nextDouble();
                        LatLng caminata = new LatLng(posicion.latitude + caminataRndLat, posicion.longitude + caminataRndLng);
                        Marker nuevoMarker = map.addMarker(new MarkerOptions().position(caminata)); nuevoMarker.setTag(0);
                        marker.remove();
                        newMarkers.add(nuevoMarker);
                    }
                    markers = new ArrayList<Marker>();
                    markers = newMarkers;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });*/
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
        }
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        String message = marker.getId();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}