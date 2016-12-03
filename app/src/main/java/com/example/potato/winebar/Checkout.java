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

public class Checkout extends Activity {

    EditText cardNum;
    EditText cardholderName;
    EditText securityCode;
    Spinner expMonth;
    Spinner expYear;
    Button continueToPlaceOrderButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        cardNum = (EditText) findViewById(R.id.cardNumEditText);
        cardholderName = (EditText) findViewById(R.id.nameEditText);
        securityCode = (EditText) findViewById(R.id.securityCodeEditText);
        expMonth = (Spinner) findViewById(R.id.monthDropDown);
        expYear = (Spinner) findViewById(R.id.yearDropDown);
        continueToPlaceOrderButton = (Button) findViewById(R.id.continueToOrderButton);

        continueToPlaceOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardNum.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a card number!", Toast.LENGTH_SHORT).show();
                } else if (cardholderName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a cardholder name!", Toast.LENGTH_SHORT).show();
                } else if (securityCode.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a security code!", Toast.LENGTH_SHORT).show();
                } else if (expMonth.getSelectedItem().toString().equals("Select Month")) {
                    Toast.makeText(getApplicationContext(), "Please enter a card expiration month!", Toast.LENGTH_SHORT).show();
                } else if (expYear.getSelectedItem().toString().equals("Select Year")) {
                    Toast.makeText(getApplicationContext(), "Please enter a card expiration year!", Toast.LENGTH_SHORT).show();
                } else if(cardNum.length() != 16){
                    Toast.makeText(getApplicationContext(), "Please enter a valid credit card number!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent newOrderIntent = new Intent(Checkout.this, Confirmation.class);
                    newOrderIntent.putExtra("fourDigits", cardNum.getText().toString().substring(12,16));
                    newOrderIntent.putExtra("streetAddr", getIntent().getStringExtra("streetAddr"));
                    newOrderIntent.putExtra("city", getIntent().getStringExtra("city"));
                    newOrderIntent.putExtra("phoneNumber", getIntent().getStringExtra("phoneNumber"));
                    newOrderIntent.putExtra("zip", getIntent().getStringExtra("zip"));
                    newOrderIntent.putExtra("state", getIntent().getStringExtra("state"));
                    newOrderIntent.putExtra("listOfWines", getIntent().getStringArrayListExtra("listOfWines"));

                    startActivity(newOrderIntent);
                }
            }
        });

    }
}
