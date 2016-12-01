package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class wineNotesList extends Activity {

    List<wines> items;
    ListView list;
    Button switchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_notes_list);

        list = (ListView) findViewById(R.id.list);
        items = new ArrayList<wines>();
        items.add(new wines("merlot2", "Merlot", "Red Wine"));
        items.add(new wines("riesling2", "Riesling", "White Wine"));
        items.add(new wines("mourvedre2", "Mourvedre", "Red Wine"));
        items.add(new wines("cava2", "Cava", "White Wine"));
        items.add(new wines("barbaresco2", "Barbaresco", "Red Wine"));

        adapterWines adp = new adapterWines(this, items);
        list.setAdapter(adp);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                wines selection = (wines) arg0.getItemAtPosition(arg2);
                Intent intent = new Intent(getApplicationContext(), specificWineNote.class);
                intent.putExtra("name",selection.getName().toString());
                System.out.println("moving");
                startActivity(intent);
            }
        });
    }
}
