package com.example.potato.winebar;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class barreview extends ListActivity {

    ReviewItemAdapter bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bd = new ReviewItemAdapter(getApplicationContext());
        getListView().setFooterDividersEnabled(true);
        setListAdapter(bd);
        bd.add(new ReviewItem("",1));
    }

}
