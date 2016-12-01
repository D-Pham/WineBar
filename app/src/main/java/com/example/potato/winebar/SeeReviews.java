package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.test.espresso.core.deps.guava.reflect.TypeToken;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ankush_2 on 11/27/2016.
 */

public class SeeReviews extends Activity{
    private DatabaseReference mDatabase;

    List<review> reviewItems;
    ListView list;
    Button switchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seereviews);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        list = (ListView) findViewById(R.id.list);
        reviewItems = new ArrayList<review>();
        switchView = (Button)findViewById(R.id.switchView);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String email = user.getEmail().replace(".","&");

        switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), wineNotesList.class);
                startActivity(intent);
            }
        });


        mDatabase.child("user_keys").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                // Retrieves FireBase data to get the unique key of the current user.
                String uKey = ((HashMap<String,String>)snapshot.getValue()).get(email).toString();
                System.out.println("asd");
                System.out.println(uKey);
                mDatabase.child("users").child(uKey).child("reviews").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            System.out.println("child");
                            System.out.println(child);
                            DataSnapshot wine = child.child("wine");
                            DataSnapshot sweetness = child.child("Sweetness");
                            DataSnapshot date = child.child("Date");
                            DataSnapshot body = child.child("Body");
                            DataSnapshot acidity = child.child("Acidity");
                            DataSnapshot tanin = child.child("Tannin");
                            DataSnapshot name = child.child("name");
                            DataSnapshot overall = child.child("Overall");
                            review item = new review((String)wine.getValue().toString().toLowerCase() + "2", (String)wine.getValue(), (String)date.getValue(),
                                    (String)sweetness.child("Notes").getValue(), (String)body.child("Notes").getValue(),
                                    (String)acidity.child("Notes").getValue(), (String)tanin.child("Notes").getValue(),
                                    (String)sweetness.child("Rating").getValue(), (String)body.child("Rating").getValue(),
                                    (String)acidity.child("Rating").getValue(), (String)tanin.child("Rating").getValue(),
                                    (String)name.getValue(), (String)overall.child("Notes").getValue(), (String)overall.child("Rating").getValue());
                            System.out.println((String)wine.getValue().toString());
                            reviewItems.add(item);
                            update();
                        }
                        System.out.println("asd");
                        System.out.println(reviewItems);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
            @Override public void onCancelled(DatabaseError error) {}
        });


        // ** Retrieve review items from users account
        // for example just hardcoded one

        //review TESTexample = new review("merlot2", "Merlot", "Red Wine", "5", "4", "3", "2");
//        TESTexample.addAcidityNotes("Great!");
//        TESTexample.addBodyNotes("Great!");
//        TESTexample.addSweetnessNotes("Great!");
//        TESTexample.addTanninNotes("Great!");
//
//        reviewItems.add(TESTexample);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                review selection = (review) arg0.getItemAtPosition(arg2);
                Intent intent = new Intent(getApplicationContext(), ReviewDetailActivity.class);

                intent.putExtra("wine",selection.wine);
                intent.putExtra("pic",selection.pic);
                intent.putExtra("Acidity",selection.acidityRating);
                intent.putExtra("Body", selection.bodyRating);
                intent.putExtra("Sweetness",selection.sweetnessRating);
                intent.putExtra("Tannin",selection.tanninRating);

                intent.putExtra("AcidityNotes",selection.acidityNotes);
                intent.putExtra("BodyNotes", selection.bodyNotes);
                intent.putExtra("SweetnessNotes",selection.sweetnessNotes);
                intent.putExtra("TanninNotes",selection.tanninNotes);
                intent.putExtra("name",selection.name);
                intent.putExtra("Overall", selection.overallRating);
                intent.putExtra("OverallNotes",selection.overallNotes);



                startActivity(intent);
            }
        });
    }
    void update(){
        adapterReviews adp = new adapterReviews(this, reviewItems);
        list.setAdapter(adp);
    }

}
