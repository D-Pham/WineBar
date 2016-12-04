package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends Activity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        Button createAccountButton = (Button) findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(view);
            }
        });
    }

    public void createAccount(View view) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        EditText signUpFullNameEditText = (EditText) findViewById(R.id.signUpFullName);
        EditText signUpEmailEditText = (EditText) findViewById(R.id.signUpEmail);
        EditText signUpPasswordEditText = (EditText) findViewById(R.id.signUpPassword);

        final String signUpFullName = signUpFullNameEditText.getText().toString();
        final String signUpEmail = signUpEmailEditText.getText().toString();
        final String signUpPassword = signUpPasswordEditText.getText().toString();
        final Intent welcome = new Intent(this, Welcome.class);

        if (signUpFullName.matches("")) {
            Toast.makeText(this, "You did not enter a full name", Toast.LENGTH_SHORT).show();
            return;
        } else if (signUpEmail.matches("")) {
            Toast.makeText(this, "You did not enter an email", Toast.LENGTH_SHORT).show();
            return;
        } else if (signUpPassword.matches("")) {
            Toast.makeText(this, "You did not enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(signUpEmail, signUpPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(Register.this, "Sign up failed. Try again later!",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        HashMap<String, String> campaigns = new HashMap<String, String>();
                        AuthUserAccount authUserAccount = new AuthUserAccount(signUpFullName, signUpEmail, campaigns);
                        final String key = mDatabase.child("users").push().getKey();
                        mDatabase.child("users").child(key).setValue(authUserAccount);
                        mDatabase.child("user_keys").child(signUpEmail.toLowerCase().replace(".", "&")).setValue(key);
                        System.out.println("Worked");
                        welcome.putExtra("userkey", key);
                        startActivity(welcome);
                    }
                });
    }
}
