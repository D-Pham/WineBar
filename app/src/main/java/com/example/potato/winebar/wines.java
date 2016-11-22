package com.example.potato.winebar;

import android.graphics.Bitmap;

/**
 * Created by Julien on 11/22/2016.
 */

public class wines {
    String pic;
    String name;
    String description;

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

    public wines(String pic, String name, String description){
        this.pic = pic;
        this.name = name;
        this.description = description;
    }

}
