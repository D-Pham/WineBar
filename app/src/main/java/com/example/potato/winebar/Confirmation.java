package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class Confirmation extends Activity {

    ListView customerBought;
    TextView customerStreetAddr;
    TextView customerCity;
    TextView customerZip;
    TextView customerState;
    TextView customerPaid;
    Button placeOrder;
    Random r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        placeOrder = (Button) findViewById(R.id.place_order_button);
        customerPaid = (TextView) findViewById(R.id.cardLastDigits);
        customerPaid.setText(getIntent().getStringExtra("fourDigits"));

        customerStreetAddr = (TextView) findViewById(R.id.customerStreetAddr);
        customerCity = (TextView) findViewById(R.id.customerCity);
        customerZip = (TextView) findViewById(R.id.customerZip);
        customerState = (TextView) findViewById(R.id.customerState);


        customerStreetAddr.setText(getIntent().getStringExtra("streetAddr"));
        customerCity.setText(getIntent().getStringExtra("city"));
        customerZip.setText(getIntent().getStringExtra("zip"));
        customerState.setText(getIntent().getStringExtra("state"));

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r = new Random();
                Intent thankYouIntent = new Intent(Confirmation.this, ThankYou.class);
                int orderNum = 10000 + r.nextInt(89999);
                thankYouIntent.putExtra("orderNum", orderNum);
                thankYouIntent.putStringArrayListExtra("listOfWines", getIntent().getStringArrayListExtra("listOfWines"));
                startActivity(thankYouIntent);
            }
        });



    }
}
