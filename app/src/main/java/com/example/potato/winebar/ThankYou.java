package com.example.potato.winebar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ThankYou extends Activity {

    TextView orderNumTV;
    ListView orderLV;
    ArrayList<cartObject> tempList = new ArrayList<cartObject>();
    adapterCart orderLA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);


        for (String s: getIntent().getStringArrayListExtra("listOfWines")) {
            cartObject c = new cartObject(s.toLowerCase()+"2", s, Integer.toString(3), Integer.toString(60));
            tempList.add(c);
        }

        orderNumTV = (TextView) findViewById(R.id.orderNumTextView);
        orderLV = (ListView) findViewById(R.id.orderListOfWines);


System.out.println(tempList);

        orderLA = new adapterCart(this, tempList);
        orderNumTV.setText(""+getIntent().getIntExtra("orderNum", 1));
        orderLV.setAdapter(orderLA);

    }
}
