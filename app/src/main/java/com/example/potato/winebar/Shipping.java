package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Shipping extends Activity {

    EditText streetText;
    EditText cityText;
    EditText phoneNumber;
    EditText zipCode;
    Button continueConfirmation;
    Spinner stateSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        streetText = (EditText) findViewById(R.id.streetAddrEditTxt);
        cityText = (EditText) findViewById(R.id.cityEditTxt);
        phoneNumber = (EditText) findViewById(R.id.phoneEditTxt);
        zipCode = (EditText) findViewById(R.id.zipEditTxt);
        continueConfirmation = (Button) findViewById(R.id.continueCheckoutButton);
        stateSpinner = (Spinner) findViewById(R.id.stateSpinner);


        continueConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (streetText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a Street Address!", Toast.LENGTH_SHORT).show();
                } else if (cityText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a City!", Toast.LENGTH_SHORT).show();
                } else if (phoneNumber.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a Phone Number!", Toast.LENGTH_SHORT).show();
                } else if (zipCode.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a Zip Code!", Toast.LENGTH_SHORT).show();
                } else if (stateSpinner.getSelectedItem().toString().equals("Select State")) {
                    Toast.makeText(getApplicationContext(), "Please select a State!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent shippingIntent = new Intent(Shipping.this, Checkout.class);

                    shippingIntent.putExtra("streetAddr", streetText.getText().toString());
                    shippingIntent.putExtra("city", cityText.getText().toString());
                    shippingIntent.putExtra("phoneNumber", phoneNumber.getText().toString());
                    shippingIntent.putExtra("zip", zipCode.getText().toString());
                    shippingIntent.putExtra("state", stateSpinner.getSelectedItem().toString());
                    shippingIntent.putExtra("hashMap", getIntent().getSerializableExtra("hashMap"));
                    shippingIntent.putExtra("grandTotal", getIntent().getIntExtra("grandTotal", 1));
                    shippingIntent.putStringArrayListExtra("listOfWines", getIntent().getStringArrayListExtra("listOfWines"));


                    startActivity(shippingIntent);
                }
            }
        });




    }
}
