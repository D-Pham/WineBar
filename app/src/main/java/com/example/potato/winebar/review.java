package com.example.potato.winebar;

/**
 * Created by Ankush_2 on 11/27/2016.
 */

public class review {

    String pic;
    String name; // name of wine
    String description;
    String sweetnessRating;
    String bodyRating;
    String acidityRating;
    String tanninRating;
    String sweetnessNotes;
    String bodyNotes;
    String acidityNotes;
    String tanninNotes;


    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSweetnessRating() {
        return sweetnessRating;
    }
    public String getBodyRating() {
        return bodyRating;
    }
    public String getAcidityRating() {
        return acidityRating;
    }
    public String getTanninRating() {
        return tanninRating;
    }

    public String getSweetnessNotes() {
        return sweetnessNotes;
    }
    public String getBodyNotes() {
        return bodyNotes;
    }
    public String getAcidityNotes() {
        return acidityNotes;
    }
    public String getTanninNotes() {
        return tanninNotes;
    }



    public review(String pic, String name, String description, String sweetnessRating, String bodyRating, String acidityRating, String tanninRating){
        this.pic = pic;
        this.name = name;

        this.sweetnessRating = sweetnessRating;
        this.bodyRating = bodyRating;
        this.acidityRating = acidityRating;
        this.tanninRating = tanninRating;
        this.description = description;

    }


    public void addAcidityNotes(String notes) {
        this.acidityNotes = notes;

    }
    public void addBodyNotes(String notes) {
        this.bodyNotes = notes;

    }
    public void addSweetnessNotes(String notes) {
        this.sweetnessNotes = notes;

    }
    public void addTanninNotes(String notes) {
        this.tanninNotes = notes;

    }




}
