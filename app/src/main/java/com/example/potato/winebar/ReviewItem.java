package com.example.potato.winebar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class reviewitem extends AppCompatActivity {
    String[][] reviews = new String[5][2];
    int state = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewitem);
    }

    public void next(View v){
        reviews[state][0] = ((TextView)findViewById(R.id.ratinged)).getText().toString();
        reviews[state][1] = ((TextView)findViewById(R.id.notesed)).getText().toString();
        state++;
        switch (state) {
            case 1:
                ((TextView)findViewById(R.id.cattitle)).setText("Acidity");
                break;
            case 2:
                ((TextView)findViewById(R.id.cattitle)).setText("Tannin");
                break;
            case 3:
                ((TextView)findViewById(R.id.cattitle)).setText("Fruit");
                break;
            case 4:
                ((TextView)findViewById(R.id.cattitle)).setText("Body");
                break;
            default:
                Intent done = new Intent(this,Welcome.class);
                done.putExtra("results",reviews);
                startActivity(done);
                break;
        }

    }
}
