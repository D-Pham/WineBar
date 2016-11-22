package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class barselect extends Activity {

    List<wines> items;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barselect);

        list = (ListView) findViewById(R.id.list);
        items = new ArrayList<wines>();
        items.add(new wines("merlot2", "Merlot", "Red Wine"));
        items.add(new wines("riesling2", "Riesling", "White Wine"));
        items.add(new wines("mourvedre2", "Mourvedre", "Red Wine"));
        items.add(new wines("cava2", "Cava", "White Wine"));
        items.add(new wines("barbaresco2", "Barbaresco", "Red Wine"));

        adapterWines adp = new adapterWines(this, items);
        list.setAdapter(adp);
    }
}
