package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.Pet;
import com.example.myapplication.model.User;
import com.example.myapplication.sql.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private Button registerButton;
    private User user;
    private int id;
    private List<Pet> pets = new LinkedList<Pet>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = findViewById(R.id.register);

        mDatabase = FirebaseDatabase.getInstance().getReference("user");
        databaseHelper = new DatabaseHelper(SignInActivity.this);
        mAuth = FirebaseAuth.getInstance();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query lastQuery = databaseReference.child("user").orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    if(child.child("id").getValue() != null)
                        id = Integer.parseInt(child.child("id").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });

        registerButton.setOnClickListener(view -> {
            EditText username = (EditText) findViewById(R.id.et_username);
            EditText email = (EditText) findViewById(R.id.et_email);
            EditText telephone = (EditText) findViewById(R.id.et_phone);
            EditText password = (EditText) findViewById(R.id.et_password);
            EditText password2 = (EditText) findViewById(R.id.et_confirmpass);

            if(!password.getText().toString().equals(password2.getText().toString())){
                Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
                password.setText(null);
                password2.setText(null);
            }
            else if(!isValidEmailAddress(email.getText().toString())){
                Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            }
            else {
                String username_str = username.getText().toString();
                String mail = email.getText().toString();
                String phone = telephone.getText().toString();
                String pass = password.getText().toString();

                Pet pet1 = new Pet(0, "", "", null);
                Pet pet2 = new Pet(1, "", "", null);
                Pet pet3 = new Pet(2, "", "", null);
                pets.add(pet1); pets.add(pet2); pets.add(pet3);
                user = new User(id + 1, mail, phone, pass, username_str, -33.034705, -71.596523, pets);
                databaseHelper.addUser(user);
                onRegister(mail, pass);
            }
        });
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

    private void onRegister(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User successfully created", Toast.LENGTH_SHORT).show();
                    updateUI();
                    startActivity(new Intent(SignInActivity.this, LoginActivity.class));
                }
            }
        });
    }
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^.+@.+\\..+$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private void updateUI() {
        String keyID = mDatabase.push().getKey();
        mDatabase.child(keyID).setValue(user);
    }
}
