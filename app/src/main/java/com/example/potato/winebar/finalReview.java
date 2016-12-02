package com.example.potato.winebar;

/**
 * Created by Julien on 12/1/2016.
 */

public class finalReview {

    String review;
    String rating;
    String notes;

    public finalReview(String review, String rating, String notes) {
        this.review = review;
        this.rating = rating;
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
