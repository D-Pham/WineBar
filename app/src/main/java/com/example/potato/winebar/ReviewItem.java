package com.example.potato.winebar;

/**
 * Created by Potato on 11/18/2016.
 */


public class ReviewItem {
    private String notes = new String();
    private int rating;

    ReviewItem(String notes, int rating) {
        this.notes = notes;
        this.rating = rating;
    }

    public String getNotes(){
        return this.notes;
    }

    public int getRating(){
        return this.rating;
    }
}
