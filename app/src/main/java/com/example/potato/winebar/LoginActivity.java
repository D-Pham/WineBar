package com.example.potato.winebar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

        Button newAccountButton = (Button) findViewById(R.id.newAccountButton);
        final Intent register = new Intent(this, Register.class);
        newAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(register);
            }
        });
    }

    public void login(View view) {
        EditText loginEmailEditText = (EditText) findViewById(R.id.loginEmail);
        EditText loginPasswordEditText = (EditText) findViewById(R.id.loginPassword);

        final String loginEmail = loginEmailEditText.getText().toString();
        final String loginPassword = loginPasswordEditText.getText().toString();
        if (loginEmail.equals("")) {
            Toast toast = Toast.makeText(LoginActivity.this, "Please enter an email", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 50);
            toast.show();
            return;
        } else if (loginPassword.equals("")) {
            Toast toast = Toast.makeText(LoginActivity.this, "Please enter a password", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 50);
            toast.show();
            return;
        }

        final Intent welcome = new Intent(this, Welcome.class);
        mAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast toast = Toast.makeText(LoginActivity.this, "Your email or password is incorrect", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.BOTTOM, 0, 50);
                            toast.show();
                            return;
                        }

                        ValueEventListener userKeyListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String key = dataSnapshot.getValue(String.class);
                                welcome.putExtra("userkey", key);
                                startActivity(welcome);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("hey", "loadUserKey:onCancelled", databaseError.toException());
                            }
                        };

                        mDatabase.child("user_keys").child(loginEmail.replace(".", "&")).addValueEventListener(userKeyListener);
                    }
                });
    }

}

