package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ankush_2 on 12/1/2016.
 */

public class PurchaseWines extends Activity {

    Button addCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_purchaselist);


        addCart = (Button) findViewById(R.id.addToCartButton);

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


             EditText merlotQuantity = (EditText) findViewById(R.id.merlotQuantity);
                EditText cavaQuantity = (EditText) findViewById(R.id.cavaQuantity);
                EditText rieslingQuantity = (EditText) findViewById(R.id.rieslingQuantity);
                EditText mourvedreQuantity = (EditText) findViewById(R.id.mourvedreQuantity);
                EditText barbarescoQuantity = (EditText) findViewById(R.id.barbarescoQuantity);
                EditText chardonnayQuantity = (EditText) findViewById(R.id.chardonnayQuantity);
                EditText gewurztraminerQuantity = (EditText) findViewById(R.id.gewurztraminerQuantity);
                EditText claretQuantity = (EditText) findViewById(R.id.claretQuantity);
                EditText beaujolaisQuantity = (EditText) findViewById(R.id.beaujolaisQuantity);

     //           System.out.println("Merlot " + merlotQuantity.getText().toString());
     //           System.out.println("Cava " + cavaQuantity.getText().toString());


                Intent intent = new Intent(getApplicationContext(), Cart.class);

                  intent.putExtra("Cava", cavaQuantity.getText().toString());
                  intent.putExtra("Riesling",  rieslingQuantity.getText().toString());
                  intent.putExtra("Merlot", merlotQuantity.getText().toString());
                 intent.putExtra("Mourvedre",  mourvedreQuantity.getText().toString());
                 intent.putExtra("Barbaresco",  barbarescoQuantity.getText().toString());
                intent.putExtra("Chardonnay",  chardonnayQuantity.getText().toString());
                intent.putExtra("Gewürztraminer",  gewurztraminerQuantity.getText().toString());
                intent.putExtra("Beaujolais",  beaujolaisQuantity.getText().toString());
                intent.putExtra("Claret",  claretQuantity.getText().toString());



//                System.out.println(intent.getStringExtra("Cava"));

                startActivity(intent);

            }
        });

    }
}
