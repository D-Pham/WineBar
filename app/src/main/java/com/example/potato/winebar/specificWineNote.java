package com.example.potato.winebar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class specificWineNote extends Activity {

    private DatabaseReference mDatabase;

    List<review> reviewItems;
    ListView list;
    TextView wineName;
    Intent prev;
    Bundle data;
    String thisWine;
    //Button switchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("made it");
        setContentView(R.layout.activity_specific_wine_note);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //switchView = (Button)findViewById(R.id.switchView);
        list = (ListView) findViewById(R.id.list);
        reviewItems = new ArrayList<review>();
        wineName = (TextView) findViewById(R.id.wine);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String email = user.getEmail().replace(".","&");
        prev = this.getIntent();
        data = prev.getExtras();
        thisWine = (String)data.get("name");
        wineName.setText(thisWine);

//        switchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), SeeReviews.class);
//                startActivity(intent);
//            }
//        });

        mDatabase.child("user_keys").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                // Retrieves FireBase data to get the unique key of the current user.
                String uKey = ((HashMap<String,String>)snapshot.getValue()).get(email).toString();
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
                            System.out.println(thisWine);
                            if(((String)wine.getValue().toString()).equals(thisWine)) {
                                reviewItems.add(item);
                                update();
                            }
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
