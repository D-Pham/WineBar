package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.data;

public class Cart extends Activity {


    Button submit;
    public ListView listOfWines;
    List<cartObject> stringListOfWinesAndQuantities;
    ArrayList<String> actualStrList;
    TextView grandTotal;
    Integer grandTotalPrice = 0;
    adapterCart listOfWinesAdapter;

    HashMap<String, Integer> winesAndQuantities = new HashMap<String, Integer>();

    HashMap<String, Integer> winesAndPrices = new HashMap<String, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        submit = (Button) findViewById(R.id.submit_cart_button);
        listOfWines = (ListView) findViewById(R.id.listOfWines);
        stringListOfWinesAndQuantities = new ArrayList<cartObject>();
        grandTotal = (TextView) findViewById(R.id.totalPriceTextView);

        actualStrList = new ArrayList<String>();

        winesAndQuantities.put("Merlot", Integer.parseInt(getIntent().getStringExtra("Merlot")));
        winesAndQuantities.put("Cava", Integer.parseInt(getIntent().getStringExtra("Cava")));
        winesAndQuantities.put("Riesling", Integer.parseInt(getIntent().getStringExtra("Riesling")));
        winesAndQuantities.put("Mourvedre", Integer.parseInt(getIntent().getStringExtra("Mourvedre")));
        winesAndQuantities.put("Barbaresco", Integer.parseInt(getIntent().getStringExtra("Barbaresco")));
        winesAndQuantities.put("Chardonnay", Integer.parseInt(getIntent().getStringExtra("Chardonnay")));
        winesAndQuantities.put("Gewürztraminer", Integer.parseInt(getIntent().getStringExtra("Gewürztraminer")));
        winesAndQuantities.put("Beaujolais", Integer.parseInt(getIntent().getStringExtra("Beaujolais")));
        winesAndQuantities.put("Claret", Integer.parseInt(getIntent().getStringExtra("Claret")));



        System.out.println(winesAndQuantities);

        for (String s: winesAndQuantities.keySet()) {
            cartObject c = null;
            if (winesAndQuantities.get(s) > 0) {
               // ImageView temp = (ImageView) findViewById(R.id.pic);
               // int imgID = this.getResources().getIdentifier((String)data.get("pic"),"drawable",this.getPackageName());


                if(s.equals("Gewürztraminer")) {
                    c = new cartObject("gewurztraminer2", s, Integer.toString(winesAndQuantities.get(s)), Integer.toString(25 * winesAndQuantities.get(s)));
                } else if (s.equals("Merlot")) {
                    c = new cartObject("merlot2", s, Integer.toString(winesAndQuantities.get(s)), Integer.toString(25 * winesAndQuantities.get(s)));

                } else if (s.equals("Cava")) {
                    c = new cartObject("cava2", s, Integer.toString(winesAndQuantities.get(s)), Integer.toString(20 * winesAndQuantities.get(s)));

                } else if (s.equals("Riesling")) {
                    c = new cartObject("riesling2", s, Integer.toString(winesAndQuantities.get(s)), Integer.toString(25 * winesAndQuantities.get(s)));

                } else if (s.equals("Mourvedre")) {
                    c = new cartObject("mourvedre2", s, Integer.toString(winesAndQuantities.get(s)), Integer.toString(25 * winesAndQuantities.get(s)));

                } else if (s.equals("Barbaresco")) {
                    c = new cartObject("barbaresco2", s, Integer.toString(winesAndQuantities.get(s)), Integer.toString(23 * winesAndQuantities.get(s)));

                } else if (s.equals("Chardonnay")) {
                    c = new cartObject("chardonnay2", s, Integer.toString(winesAndQuantities.get(s)), Integer.toString(25 * winesAndQuantities.get(s)));

                } else if (s.equals("Beaujolais")) {
                    c = new cartObject("beaujolais2", s, Integer.toString(winesAndQuantities.get(s)), Integer.toString(25 * winesAndQuantities.get(s)));

                } else if (s.equals("Claret")) {
                    c = new cartObject("claret2", s, Integer.toString(winesAndQuantities.get(s)), Integer.toString(32 * winesAndQuantities.get(s)));
                }

                actualStrList.add(s);
                stringListOfWinesAndQuantities.add(c);

            }
        }

        for (cartObject c: stringListOfWinesAndQuantities) {
            System.out.println(c.getWineName());
        }

        listOfWinesAdapter = new adapterCart(this, stringListOfWinesAndQuantities);
        listOfWines.setAdapter(listOfWinesAdapter);

        for (cartObject c: stringListOfWinesAndQuantities) {
            grandTotalPrice = grandTotalPrice + c.getTotalPrice();
        }

        grandTotal.setText("$" + grandTotalPrice.toString() + ".00");
        /*setContentView(R.layout.activity_confirmation);
        ListView customerBought = (ListView)   findViewById(R.id.listView);
        customerBought.setAdapter(listOfWinesAdapter);
        setContentView(R.layout.activity_cart);*/



        // winesAndQuantities.put("Merlot", 3);
//        listOfWinesAdapter.add(stringListOfWinesAndQuantities);
 //       listOfWines.setAdapter(listOfWinesAdapter);

        //this is where list view would be updated

     /*   ImageView temp = (ImageView) findViewById(R.id.pic);
        int imgID = this.getResources().getIdentifier("claret2","drawable",this.getPackageName());

        temp.setImageResource(imgID);
       */


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (actualStrList.size() > 0) {
                    Intent shippingIntent = new Intent(Cart.this, Shipping.class);
                    //  shippingIntent.putExtra("wines", stringListOfWinesAndQuantities);
                    shippingIntent.putStringArrayListExtra("listOfWines", actualStrList);
                    shippingIntent.putExtra("hashMap", winesAndQuantities);
                    shippingIntent.putExtra("grandTotal", grandTotalPrice);
                    startActivity(shippingIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "There are no items in the cart!", Toast.LENGTH_SHORT).show();
                }
            }
    });
}

    public void updateWines(Intent i) {

    }

    public void deleteWine(String s) {
        if (winesAndQuantities.containsKey(s)) {
            winesAndQuantities.remove(s);
        }
    }


    public ListView getListOfWines() {
      return this.listOfWines;
    }
}
