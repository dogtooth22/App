package com.example.myapplication.notifications;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CurrentWalk extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {
    private static final int TAG_CODE_PERMISSION_LOCATION = 1;
    private String userIndex;
    private String walkerIndex;
    private String notification;
    private String userPhone;
    private Button callbutton;
    private String message;
    private Button confirmButton;
    private LatLng lugar;
    private DatabaseReference ref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_page);
        callbutton = (Button) findViewById(R.id.callButton);

        Intent intent = getIntent();
        userIndex = intent.getStringExtra("userIndex");
        notification = intent.getStringExtra("notification");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        callbutton.setOnClickListener(view -> {
            Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", userPhone, null));
            startActivity(phoneCall);
        });
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            map.setMapStyle(new MapStyleOptions(getResources().getString(R.string.dia_json)));
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("user").
                    child(userIndex).child("notifications").child(notification);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    lugar = new LatLng(Double.parseDouble(dataSnapshot.child("latitude").getValue().toString()),
                            Double.parseDouble(dataSnapshot.child("longitude").getValue().toString()));
                    CameraPosition USM = CameraPosition.builder().target(lugar).zoom(16).build();
                    map.moveCamera(CameraUpdateFactory.newCameraPosition(USM));
                    walkerIndex = String.valueOf(dataSnapshot.child("userIndex").getValue());
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("walker").
                            child(walkerIndex);
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            map.clear();
                            LatLng markerLocation = new LatLng(Double.parseDouble(snapshot.child("latitude").getValue().toString()),
                                    Double.parseDouble(snapshot.child("longitude").getValue().toString()));
                            Marker markerMoving = map.addMarker(new MarkerOptions().position(markerLocation).title("Your walker is here!").
                                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                            Marker marker = map.addMarker(new MarkerOptions().position(lugar)
                                    .title("Your meetup location!"));
                            markerMoving.showInfoWindow();
                            marker.showInfoWindow();
                            userPhone = String.valueOf(snapshot.child("phone").getValue());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle possible errors.
                }
            });
        }
        else{
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
        }
    }
}
