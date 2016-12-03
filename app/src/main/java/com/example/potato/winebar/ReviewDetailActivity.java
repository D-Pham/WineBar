package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ankush_2 on 11/27/2016.
 */

public class ReviewDetailActivity extends Activity {

    Intent prev;
    Bundle data;
    List<finalReview> items;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_place_review_detail);

        // TODO - implement the Activity

        list = (ListView) findViewById(R.id.list);
        items = new ArrayList<finalReview>();

        String name = getIntent().getStringExtra("name");
        String wine = getIntent().getStringExtra("wine");
        String soverallRating = getIntent().getStringExtra("Overall");
        String soverallNotes = getIntent().getStringExtra("OverallNotes");
        String sacidityRating = getIntent().getStringExtra("Acidity");
        String sacidityNotes = getIntent().getStringExtra("AcidityNotes");
        String stanninRating = getIntent().getStringExtra("Tannin");
        String stanninNotes = getIntent().getStringExtra("TanninNotes");
        String ssweetnessRating = getIntent().getStringExtra("Sweetness");
        String ssweetnessNotes = getIntent().getStringExtra("SweetnessNotes");
        String sbodyRating = getIntent().getStringExtra("Body");
        String sbodyNotes = getIntent().getStringExtra("BodyNotes");
        String sfruitRating = getIntent().getStringExtra("Fruit");
        String sfruitNotes = getIntent().getStringExtra("FruitNotes");

        TextView  noteName  = (TextView) findViewById(R.id.name);
        TextView  wineName  = (TextView) findViewById(R.id.wine);
        TextView  overallRating  = (TextView) findViewById(R.id.overallRating);
        TextView  overallNotes  = (TextView) findViewById(R.id.overallNotes);

        noteName.setText(name);
        wineName.setText(wine);
        overallRating.setText("Overall Rating: " + soverallRating);
        overallNotes.setText(soverallNotes);

        System.out.println("ASd");
        System.out.println(soverallRating);

        items.add(new finalReview("Acidity", sacidityRating, sacidityNotes));
        items.add(new finalReview("Fruit", sfruitRating, sfruitNotes));
        items.add(new finalReview("Tannin", stanninRating, stanninNotes));
        items.add(new finalReview("Sweetness", ssweetnessRating, ssweetnessNotes));
        items.add(new finalReview("Body", sbodyRating, sbodyNotes));

        System.out.println("items");
        adapterFinal adp = new adapterFinal(this, items);
        list.setAdapter(adp);

        //flag.setImageBitmap(getIntent().getBundleExtra()get("Flag"));


        // Find ID of imageview and assign the wine image retrieved from extras to it
       // ImageView temp = (ImageView) findViewById(R.id.pic);
       //
        //
        prev = this.getIntent();
        data = prev.getExtras();

        // Find ID of imageview and assign the wine image retrieved from extras to it
        ImageView temp = (ImageView) findViewById(R.id.winePic);
        int imgID = this.getResources().getIdentifier((String)data.get("pic"),"drawable",this.getPackageName());

        temp.setImageResource(imgID);










    }
}

