package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Confirmation extends Activity {

    ListView customerBought;
    TextView customerStreetAddr;
    TextView customerCity;
    TextView customerZip;
    TextView customerState;
    TextView customerPaid;
    TextView grandTotalPrice;
    Button placeOrder;
    Random r;

    ArrayList<cartObject> tempList = new ArrayList<cartObject>();
    adapterCart orderLA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirmation);




        grandTotalPrice = (TextView) findViewById(R.id.orderSummaryTotalPriceTV);
        placeOrder = (Button) findViewById(R.id.place_order_button);
        customerPaid = (TextView) findViewById(R.id.cardLastDigits);
        customerPaid.setText("XXXX-XXXX-XXXX-" + getIntent().getStringExtra("fourDigits"));

        customerStreetAddr = (TextView) findViewById(R.id.customerStreetAddr);
        customerCity = (TextView) findViewById(R.id.customerCity);
        customerZip = (TextView) findViewById(R.id.customerZip);
        customerState = (TextView) findViewById(R.id.customerState);



        customerStreetAddr.setText(getIntent().getStringExtra("streetAddr"));
        customerCity.setText(getIntent().getStringExtra("city"));
        customerZip.setText(getIntent().getStringExtra("zip"));
        customerState.setText(getIntent().getStringExtra("state"));


        cartObject c;
        for (String s: getIntent().getStringArrayListExtra("listOfWines")) {
            if(s.equals("Gewürztraminer")) {
                c = new cartObject("gewurztraminer2", s, Integer.toString(3), Integer.toString(60));
            }else{
                c = new cartObject(s.toLowerCase()+"2", s, Integer.toString(3), Integer.toString(60));
            }
            tempList.add(c);
        }

        customerBought = (ListView) findViewById(R.id.listView);

        System.out.println(tempList);

        orderLA = new adapterCart(this, tempList);
        customerBought.setAdapter(orderLA);

        grandTotalPrice.setText("Grand Total: $" +Integer.toString(getIntent().getIntExtra("grandTotal", 1)) +".00");


        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r = new Random();
                Intent thankYouIntent = new Intent(Confirmation.this, ThankYou.class);
                int orderNum = 10000 + r.nextInt(89999);
                thankYouIntent.putExtra("orderNum", orderNum);
                thankYouIntent.putExtra("hashMap", getIntent().getSerializableExtra("hashMap"));
                thankYouIntent.putExtra("grandTotal", getIntent().getIntExtra("grandTotal", 1));
                thankYouIntent.putStringArrayListExtra("listOfWines", getIntent().getStringArrayListExtra("listOfWines"));
                startActivity(thankYouIntent);
            }
        });



    }
}
