package com.example.myapplication.walker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.OwnerHomeActivity;
import com.example.myapplication.R;
import com.example.myapplication.main.ThirdActivity;
import com.example.myapplication.model.Notification;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

public class Message extends AppCompatActivity {
    private Button sendButton;
    private String userIndex;
    private String walkerIndex;
    private DatabaseReference ref;
    private DatabaseReference mDatabaseUser;
    private DatabaseReference mDatabaseWalker;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        Intent intent = getIntent();
        userIndex = intent.getStringExtra("userIndex");
        walkerIndex = intent.getStringExtra("walkerIndex");

        sendButton = findViewById(R.id.send);
        sendButton.setOnClickListener(view -> {
            sendInfo(userIndex, walkerIndex, intent.getDoubleExtra("latitude", 0), intent.getDoubleExtra("longitude", 0));
        });
    }

    private void sendInfo(String user, String walker, double lan, double lon) {
        EditText messageText = (EditText) findViewById(R.id.messageET);
        String message = messageText.getText().toString();

        Notification notificationUser = new Notification(2, walker, message, lan, lon);
        Notification notificationWalker = new Notification(2, user, message, lan, lon);
        List<Notification> notifications = new LinkedList<Notification>();

        mDatabaseWalker = FirebaseDatabase.getInstance().getReference("walker").
                child(walker).child("notifications");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference("user").
                child(user).child("notifications");
        String keyNotification = mDatabaseWalker.push().getKey();

        mDatabaseUser.child(keyNotification).setValue(notificationUser);
        mDatabaseWalker.child(keyNotification).setValue(notificationWalker);
        goBack(user);
    }

    private void goBack(String user) {
        Toast.makeText(this, "Your message was sent!", Toast.LENGTH_SHORT).show();
        Intent goToHome = new Intent(Message.this, OwnerHomeActivity.class);
        goToHome.putExtra("userIndex", user);
        startActivity(goToHome);
    }
}
