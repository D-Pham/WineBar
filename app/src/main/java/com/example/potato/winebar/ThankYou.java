package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ThankYou extends Activity {

    TextView orderNumTV;
    ListView orderLV;
    ArrayList<cartObject> tempList = new ArrayList<cartObject>();
    adapterCart orderLA;
    Button main;
    TextView grandTotalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        main = (Button) findViewById(R.id.button5);
        grandTotalPrice = (TextView) findViewById(R.id.thankYouTotalPrice);

        for (String s: getIntent().getStringArrayListExtra("listOfWines")) {

            cartObject c = null;
            HashMap<String, Integer> hashMap = (HashMap<String, Integer>) getIntent().getSerializableExtra("hashMap");
            if(s.equals("Gewürztraminer")) {
                c = new cartObject("gewurztraminer2", s, Integer.toString(hashMap.get(s)), Integer.toString(25 * hashMap.get(s)));
            } else if (s.equals("Merlot")) {
                c = new cartObject("merlot2", s, Integer.toString(hashMap.get(s)), Integer.toString(25 * hashMap.get(s)));

            } else if (s.equals("Cava")) {
                c = new cartObject("cava2", s, Integer.toString(hashMap.get(s)), Integer.toString(20 * hashMap.get(s)));

            } else if (s.equals("Riesling")) {
                c = new cartObject("riesling2", s, Integer.toString(hashMap.get(s)), Integer.toString(25 * hashMap.get(s)));

            } else if (s.equals("Mourvedre")) {
                c = new cartObject("mourvedre2", s, Integer.toString(hashMap.get(s)), Integer.toString(25 * hashMap.get(s)));

            } else if (s.equals("Barbaresco")) {
                c = new cartObject("barbaresco2", s, Integer.toString(hashMap.get(s)), Integer.toString(23 * hashMap.get(s)));

            } else if (s.equals("Chardonnay")) {
                c = new cartObject("chardonnay2", s, Integer.toString(hashMap.get(s)), Integer.toString(25 * hashMap.get(s)));

            } else if (s.equals("Beaujolais")) {
                c = new cartObject("beaujolais2", s, Integer.toString(hashMap.get(s)), Integer.toString(25 * hashMap.get(s)));

            } else if (s.equals("Claret")) {
                c = new cartObject("claret2", s, Integer.toString(hashMap.get(s)), Integer.toString(32 * hashMap.get(s)));
            }

            tempList.add(c);
        }

        orderNumTV = (TextView) findViewById(R.id.orderNumTextView);
        orderLV = (ListView) findViewById(R.id.orderListOfWines);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            GMailSender sender = new GMailSender("corgiappreciation@gmail.com", "corgiswag911");
                            sender.sendMail("Your WineBar App Order #" + getIntent().getIntExtra("orderNum", 1),
                                    "Hello!\n"+"Thank you for your purchase of: $" +
                                            Integer.toString(getIntent().getIntExtra("grandTotal", 1))
                                            + ".00"+"\n"+"Your order number is: " + getIntent().getIntExtra("orderNum", 1) +
                                    "\n"+"\n"+"-WineBar Team",
                                    "WineBarCMSC436@gmail.com", user.getEmail());
                        } catch (Exception e) {
                        }
                    }
                }).start();

                Intent intent = new Intent(getApplicationContext(), Welcome.class);
                startActivity(intent);
            }
        });


        System.out.println(tempList);

        orderLA = new adapterCart(this, tempList);
        orderNumTV.setText(""+getIntent().getIntExtra("orderNum", 1));
        orderLV.setAdapter(orderLA);

        grandTotalPrice.setText("Grand Total: $" + Integer.toString(getIntent().getIntExtra("grandTotal", 1)) + ".00");

    }
}
