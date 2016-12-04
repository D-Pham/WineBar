package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.test.espresso.core.deps.guava.reflect.TypeToken;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReviewItem extends Activity {
    String uKey;
    Intent prev;
    Bundle data;
    String[][] reviews = new String[7][2];
    int state = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewitem);

        // Hide back button when on first review page
        findViewById(R.id.bak).setVisibility(View.GONE);

        // Hide linear layout 1 for first page
        findViewById(R.id.lin1).setVisibility(View.GONE);


        prev = this.getIntent();
        data = prev.getExtras();

        // Find ID of imageview and assign the wine image retrieved from extras to it
        ImageView temp = (ImageView) findViewById(R.id.imageView);
        int imgID = this.getResources().getIdentifier((String)data.get("pic"),"drawable",this.getPackageName());

        temp.setImageResource(imgID);
    }

    public void next(View v) throws JSONException {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        findViewById(R.id.bak).setVisibility(View.VISIBLE);
        findViewById(R.id.lin1).setVisibility(View.VISIBLE);

        /**
        *  Determines if current review page has been visited or not, if yes, repopulate
        *  the text fields with the previously stored information. Does not work if going back
        *  when on the final review page because it does not get submitted/stored. Otherwise
        *  store the data into a String[][] array. First [] refers to review page # and second []
        *  consists of two elements, [0] is the numerical rating and [1] is any notes.
        **/
        if ((((TextView) findViewById(R.id.notesed)).getText().toString().equals("")
            || ((TextView) findViewById(R.id.ratinged)).getText().toString().equals("")) && state != 0){
            Toast.makeText(getApplicationContext(),"Please fill in all fields.", Toast.LENGTH_SHORT).show();
        } else {
            if (state < 7 && (reviews[state][1] == null)) {
                reviews[state][0] = ((TextView)findViewById(R.id.ratinged)).getText().toString();
                reviews[state][1] = ((TextView)findViewById(R.id.notesed)).getText().toString();
                ((TextView) findViewById(R.id.notesed)).setText("");
                ((TextView) findViewById(R.id.ratinged)).setText("");
            } else if (state < 7) {
                ((TextView)findViewById(R.id.ratinged)).setText(reviews[state+1][0]);
                ((TextView)findViewById(R.id.notesed)).setText(reviews[state+1][1]);
            }

            state++;

            // Switch statement determining what review page you are on.
            switch (state) {
                case 1:
                    ((TextView)findViewById(R.id.cattitle)).setText("Sweetness");
                    ((TextView)findViewById(R.id.note)).setText("Notes:");
                    break;
                case 2:
                    ((TextView)findViewById(R.id.cattitle)).setText("Acidity");
                    break;
                case 3:
                    ((TextView)findViewById(R.id.cattitle)).setText("Tannin");
                    break;
                case 4:
                    ((TextView)findViewById(R.id.cattitle)).setText("Fruit");
                    break;
                case 5:
                    ((TextView)findViewById(R.id.cattitle)).setText("Body");
                    break;
                case 6:
                    ((TextView)findViewById(R.id.cattitle)).setText("Overall");
                    break;
                default:

                    // Default means end of review reached and commits saved data to a JSON object
                    final JSONObject ret2 = new JSONObject();
                    ret2.put("wine",prev.getStringExtra("name"));

                    for(int i = 0; i < 7; i++){ // Iterate through saved array
                        JSONObject temp = new JSONObject();
                        switch (i){
                            case 0:
                                ret2.put("name",reviews[i][1]);
                                break;
                            case 1:
                                temp.put("Rating",reviews[i][0]);
                                temp.put("Notes",reviews[i][1]);
                                ret2.put("Sweetness",temp);
                                break;
                            case 2:
                                temp.put("Rating",reviews[i][0]);
                                temp.put("Notes",reviews[i][1]);
                                ret2.put("Acidity",temp);
                                break;
                            case 3:
                                temp.put("Rating",reviews[i][0]);
                                temp.put("Notes",reviews[i][1]);
                                ret2.put("Tannin",temp);
                                break;
                            case 4:
                                temp.put("Rating",reviews[i][0]);
                                temp.put("Notes",reviews[i][1]);
                                ret2.put("Fruit",temp);
                                break;
                            case 5:
                                temp.put("Rating",reviews[i][0]);
                                temp.put("Notes",reviews[i][1]);
                                ret2.put("Body",temp);
                                break;
                            case 6:
                                temp.put("Rating",reviews[i][0]);
                                temp.put("Notes",reviews[i][1]);
                                ret2.put("Overall",temp);
                                break;
                        }
                    }

                    final DatabaseReference root = FirebaseDatabase.getInstance().getReference();
                    final String email = user.getEmail().replace(".","&");

                    // Adds timestamp to the object
                    ret2.put("Date",(new Date().toString()));

                    root.child("user_keys").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            // Retrieves FireBase data to get the unique key of the current user.
                            System.out.println(email);
                            uKey = ((HashMap<String,String>)snapshot.getValue()).get(email).toString();

                            // Converts JSON to Gson to map out data to be committed to FireBase
                            Map<String, Object> poot = new Gson().fromJson(ret2.toString(), new TypeToken<HashMap<String, Object>>(){}.getType());

                            // Updates the user child with new reviews
                            root.child("users/").child(uKey).child("reviews").push().setValue(poot);
                        }
                        @Override public void onCancelled(DatabaseError error) {}
                    });

                    // Review intent finished. Return to welcome page.
                    Intent done = new Intent(this,Welcome.class);
                    startActivity(done);
                    break;
            }
        }
    }

    // Handles repopulation of previously entered fields when navigating
    // to a previous review screen
    public void back(View v){
        state--;

        if (state == 0) {
            // First page does not have a ratings field
            ((TextView) findViewById(R.id.notesed)).setText(reviews[state][1]);
            findViewById(R.id.bak).setVisibility(View.GONE);
            findViewById(R.id.lin1).setVisibility(View.GONE);

        } else {
            ((TextView) findViewById(R.id.ratinged)).setText(reviews[state][0]);
            ((TextView) findViewById(R.id.notesed)).setText(reviews[state][1]);
        }

        switch (state) {
            case 0:
                ((TextView) findViewById(R.id.cattitle)).setText("Name of Note");
                ((TextView)findViewById(R.id.note)).setText("Notes:");
                break;
            case 1:
                ((TextView) findViewById(R.id.cattitle)).setText("Sweetness");
                break;
            case 2:
                ((TextView) findViewById(R.id.cattitle)).setText("Acidity");
                break;
            case 3:
                ((TextView) findViewById(R.id.cattitle)).setText("Tannin");
                break;
            case 4:
                ((TextView) findViewById(R.id.cattitle)).setText("Fruit");
                break;
            case 5:
                ((TextView) findViewById(R.id.cattitle)).setText("Body");
                break;
        }
    }
}
