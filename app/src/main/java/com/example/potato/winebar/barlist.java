package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class barlist extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barlist);

        TextView selec = (TextView) findViewById(R.id.shitbar);
        selec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectbar = new Intent(barlist.this,barselect.class);
                startActivity(selectbar);
            }
        });
    }

}
