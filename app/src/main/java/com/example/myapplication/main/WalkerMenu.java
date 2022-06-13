package com.example.myapplication.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WalkerMenu extends AppCompatActivity {
    private TextView walkerInfo;
    private String walkerIndex;
    private String nombreUsuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walker_menu);
        walkerInfo = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        walkerIndex = intent.getStringExtra("walkerIndex");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("walker");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nombreUsuario = String.valueOf(dataSnapshot.child(walkerIndex).child("username").getValue());
                walkerInfo.setText("Nombre del paseador: " + nombreUsuario);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });

    }
}
