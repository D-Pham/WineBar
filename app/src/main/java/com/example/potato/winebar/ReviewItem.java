package com.example.potato.winebar;

import android.content.Intent;
import android.support.test.espresso.core.deps.guava.reflect.TypeToken;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReviewItem extends AppCompatActivity {
    String uKey;
    Intent prev;
    Bundle data;
    String[][] reviews = new String[5][2];
    JSONObject ret1 = new JSONObject();
    int state = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewitem);
        findViewById(R.id.bak).setVisibility(View.GONE);
        prev = this.getIntent();
        data = prev.getExtras();
        ImageView temp = (ImageView) findViewById(R.id.imageView);
        int imgID = this.getResources().getIdentifier((String)data.get("pic"),"drawable",this.getPackageName());

        temp.setImageResource(imgID);
    }

    public void next(View v) throws JSONException {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        findViewById(R.id.bak).setVisibility(View.VISIBLE);

        if (state < 5 && (reviews[state][0] == null)) {
            reviews[state][0] = ((TextView)findViewById(R.id.ratinged)).getText().toString();
            reviews[state][1] = ((TextView)findViewById(R.id.notesed)).getText().toString();
            ((TextView) findViewById(R.id.notesed)).setText("");
            ((TextView) findViewById(R.id.ratinged)).setText("");
        } else if (state < 5) {
            ((TextView)findViewById(R.id.ratinged)).setText(reviews[state][0]);
            ((TextView)findViewById(R.id.notesed)).setText(reviews[state][1]);
        }

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
                JSONObject ret2 = new JSONObject();
                for(int i = 0; i < 5; i++){
                    JSONObject temp = new JSONObject();
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
                final DatabaseReference root = FirebaseDatabase.getInstance().getReference();
                final String email = user.getEmail().replace(".","&");
                ret1.put(prev.getStringExtra("name"),ret2);

                root.child("user_keys").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        uKey = ((HashMap<String,String>)snapshot.getValue()).get(email).toString();
                        Map<String, Object> poot = new Gson().fromJson(ret1.toString(), new TypeToken<HashMap<String, Object>>(){}.getType());

                        root.child("users/"+uKey+"/reviews/").updateChildren(poot);
                    }
                    @Override public void onCancelled(DatabaseError error) {}
                });
                Intent done = new Intent(this,Welcome.class);

                startActivity(done);
                break;
        }
    }

    public void back(View v){
        state--;
        if (state == 0)
            findViewById(R.id.bak).setVisibility(View.GONE);

        ((TextView)findViewById(R.id.ratinged)).setText(reviews[state][0]);
        ((TextView)findViewById(R.id.notesed)).setText(reviews[state][1]);

        switch (state) {
            case 0:
                ((TextView) findViewById(R.id.cattitle)).setText("Sweetness");
                break;
            case 1:
                ((TextView) findViewById(R.id.cattitle)).setText("Acidity");
                break;
            case 2:
                ((TextView) findViewById(R.id.cattitle)).setText("Tannin");
                break;
            case 3:
                ((TextView) findViewById(R.id.cattitle)).setText("Fruit");
                break;
        }
    }
}
