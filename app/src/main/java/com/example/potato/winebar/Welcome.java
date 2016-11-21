package com.example.potato.winebar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Welcome extends AppCompatActivity {

    ImageButton edit;
    ImageButton notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        edit = (ImageButton)findViewById(R.id.editButton);
        notes = (ImageButton)findViewById(R.id.notesButton);

        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Go to edit
            }
        });
        notes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Go to notes
            }
        });

    }
}
