package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ankush_2 on 11/27/2016.
 */

public class SeeReviews extends Activity{

    List<review> reviewItems;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seereviews);

        list = (ListView) findViewById(R.id.list);
        reviewItems = new ArrayList<review>();

        // ** Retrieve review items from users account
        // for example just hardcoded one

        review TESTexample = new review("merlot2", "Merlot", "Red Wine", "5", "4", "3", "2");
        TESTexample.addAcidityNotes("Great!");
        TESTexample.addBodyNotes("Great!");
        TESTexample.addSweetnessNotes("Great!");
        TESTexample.addTanninNotes("Great!");

        reviewItems.add(TESTexample);

        adapterReviews adp = new adapterReviews(this, reviewItems);
        list.setAdapter(adp);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                review selection = (review) arg0.getItemAtPosition(arg2);
                Intent intent = new Intent(getApplicationContext(), ReviewDetailActivity.class);

                intent.putExtra("wine",selection.getName().toString());
                intent.putExtra("pic",selection.getPic().toString());
                intent.putExtra("Acidity",selection.getAcidityRating());
                intent.putExtra("Body", selection.getBodyRating());
                intent.putExtra("Sweetness",selection.getSweetnessRating());
                intent.putExtra("Tannin",selection.getTanninRating());

                intent.putExtra("AcidityNotes",selection.getAcidityNotes());
                intent.putExtra("BodyNotes", selection.getBodyNotes());
                intent.putExtra("SweetnessNotes",selection.getSweetnessNotes());
                intent.putExtra("TanninNotes",selection.getTanninNotes());




                startActivity(intent);
            }
        });
    }


}
