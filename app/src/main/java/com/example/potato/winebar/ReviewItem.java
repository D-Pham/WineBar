package com.example.potato.winebar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReviewItem extends AppCompatActivity {
    String[][] reviews = new String[5][2];
    JSONObject ret1 = new JSONObject();
    int state = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewitem);
    }

    public void next(View v) throws JSONException {
        reviews[state][0]= ((TextView)findViewById(R.id.ratinged)).getText().toString();
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
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                ret1.put("user",user.getEmail());
                JSONObject ret2 = new JSONObject();
                JSONObject temp = new JSONObject();
                for(int i =0; i < 5; i++){
                    switch (i){
                        case 0:
                            temp.put("Rating",reviews[i][0]);
                            temp.put("Notes",reviews[i][1]);
                            ret2.put("Sweetness",temp);
                            break;
                        case 1:
                            temp.put("Rating",reviews[i][0]);
                            temp.put("Notes",reviews[i][1]);
                            ret2.put("Acidity",temp);
                            break;
                        case 2:
                            temp.put("Rating",reviews[i][0]);
                            temp.put("Notes",reviews[i][1]);
                            ret2.put("Tannin",temp);
                            break;
                        case 3:
                            temp.put("Rating",reviews[i][0]);
                            temp.put("Notes",reviews[i][1]);
                            ret2.put("Fruit",temp);
                            break;
                        case 4:
                            temp.put("Rating",reviews[i][0]);
                            temp.put("Notes",reviews[i][1]);
                            ret2.put("Body",temp);
                            break;
                    }
                }
                ret1.put("REPLACEWITHWINENAME",ret2);
                Intent done = new Intent(this,Welcome.class);
                done.putExtra("results",ret1.toString());
                startActivity(done);
                break;
        }

    }
}
