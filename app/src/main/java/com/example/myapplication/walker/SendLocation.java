package com.example.myapplication.walker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SendLocation extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {

    private static final int TAG_CODE_PERMISSION_LOCATION = 1;
    private Button doneButton;
    private LatLng latLng, location;
    private double latitude;
    private double longitude;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_location);
        doneButton = (Button) findViewById(R.id.button);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
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
    public void onMapReady(@NonNull GoogleMap map) {
        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude", 0);
        longitude = intent.getDoubleExtra("longitude", 0);
        latLng = new LatLng(latitude, longitude);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            map.setMapStyle(new MapStyleOptions(getResources().getString(R.string.dia_json)));
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);

            map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {}

                @SuppressWarnings("unchecked")
                @Override
                public void onMarkerDragEnd(Marker marker) {
                    location = marker.getPosition();
                    map.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                }

                @Override
                public void onMarkerDrag(Marker marker) {}
            });

            Marker marker = map.addMarker(new MarkerOptions().position(latLng)
                    .title("Drag me to set your meetup location!").draggable(true));
            marker.showInfoWindow();

            doneButton.setOnClickListener(view -> {
                Intent messagePage = new Intent(this, Message.class);
                messagePage.putExtra("walkerIndex", intent.getStringExtra("walkerIndex"));
                messagePage.putExtra("userIndex", intent.getStringExtra("userIndex"));
                if(location == null) {
                    messagePage.putExtra("latitude", latLng.latitude);
                    messagePage.putExtra("longitude", latLng.longitude);
                }
                else{
                    messagePage.putExtra("latitude", location.latitude);
                    messagePage.putExtra("longitude", location.longitude);
                }
                startActivity(messagePage);
            });
        }
        else{
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
        }
        CameraPosition USM = CameraPosition.builder().target(latLng).zoom(16).build();
        map.moveCamera(CameraUpdateFactory.newCameraPosition(USM));
    }
}
