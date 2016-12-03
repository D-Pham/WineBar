package com.example.potato.winebar;

/**
 * Created by Jonas on 12/2/2016.
 */

public class cartObject {

    String pic;
    String wineName;
    String quantity;
    String totalPrice;

    public cartObject(String pic, String wineName, String quantity, String totalPrice) {
        this.pic = pic;
        this.wineName = wineName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public Integer getQuantity() {
        return Integer.parseInt(quantity);
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return Integer.parseInt(totalPrice);
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
