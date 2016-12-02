package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Welcome extends Activity {

    ImageButton edit;
    ImageButton notes;
    ImageButton cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        edit = (ImageButton)findViewById(R.id.editButton);
        notes = (ImageButton)findViewById(R.id.notesButton);
        cart = (ImageButton)findViewById(R.id.cartButton);


        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Go to edit
                Intent temp = new Intent(Welcome.this, barselect.class);
                startActivity(temp);
            }
        });
        notes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Go to notes
                Intent temp = new Intent(Welcome.this, SeeReviews.class);
                startActivity(temp);
            }
        });

        cart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Go to purchase wines
                Intent temp = new Intent(Welcome.this, PurchaseWines.class);
                startActivity(temp);
            }
        });


    }
}
