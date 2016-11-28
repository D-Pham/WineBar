package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ankush_2 on 11/27/2016.
 */

public class ReviewDetailActivity extends Activity {

    Intent prev;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_place_review_detail);

        // TODO - implement the Activity

        TextView acidityVal  = (TextView) findViewById(R.id.acidityVal);
        TextView  tanninVal  = (TextView) findViewById(R.id.tanninVal);
        TextView  wine_name  = (TextView) findViewById(R.id.wine_name);
        TextView  sweetnessVal  = (TextView) findViewById(R.id.sweetnessVal);
        TextView  bodyVal  = (TextView) findViewById(R.id.bodyVal);

        //ImageView pic = (ImageView) findViewById(R.id.pic);


        TextView acidityNotes  = (TextView) findViewById(R.id.acidityNotes);
        TextView  tanninNotes  = (TextView) findViewById(R.id.tanninNotes);
        TextView  sweetnessNotes  = (TextView) findViewById(R.id.sweetnessNotes);
        TextView  bodyNotes  = (TextView) findViewById(R.id.bodyNotes);


        acidityVal.setText(getIntent().getStringExtra("Acidity"));
        tanninVal.setText(getIntent().getStringExtra("Tannin"));
        wine_name.setText(getIntent().getStringExtra("wine"));

        sweetnessVal.setText(getIntent().getStringExtra("Sweetness"));

        bodyVal.setText(getIntent().getStringExtra("Body"));


        acidityNotes.setText(getIntent().getStringExtra("AcidityNotes"));
        tanninNotes.setText(getIntent().getStringExtra("TanninNotes"));
        sweetnessNotes.setText(getIntent().getStringExtra("SweetnessNotes"));

        bodyNotes.setText(getIntent().getStringExtra("BodyNotes"));

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

