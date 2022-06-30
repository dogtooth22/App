package com.example.myapplication.walker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.walker.SendLocation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WalkerMenu extends AppCompatActivity {
    private TextView walkerInfo;
    private String walkerIndex;
    private String userIndex;
    private String userName;
    private String walkerPhone;
    private Button locationButton;
    private Button callButton;
    private double latitude;
    private double longitude;

    //TODO hay que controlar la logica cuando está ocupado y que quede bonito

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walker_menu);
        callButton = (Button) findViewById(R.id.call);
        locationButton = (Button) findViewById(R.id.markLocation);

        Intent intent = getIntent();
        walkerIndex = intent.getStringExtra("walkerIndex");
        userIndex = intent.getStringExtra("userIndex");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("walker");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName = String.valueOf(dataSnapshot.child(walkerIndex).child("username").getValue());
                walkerPhone = String.valueOf(dataSnapshot.child(walkerIndex).child("phone").getValue());
                latitude = (double) dataSnapshot.child(walkerIndex).child("latitude").getValue();
                longitude = (double) dataSnapshot.child(walkerIndex).child("longitude").getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });

        callButton.setOnClickListener(view -> {
            Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", walkerPhone, null));
            startActivity(phoneCall);
        });

        locationButton.setOnClickListener(view -> {
            Intent locationPage = new Intent(this, SendLocation.class);
            locationPage.putExtra("walkerIndex", walkerIndex);
            locationPage.putExtra("userIndex", userIndex);
            locationPage.putExtra("latitude", latitude);
            locationPage.putExtra("longitude", longitude);
            startActivity(locationPage);
        });

    }
}
