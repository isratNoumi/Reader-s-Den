package com.example.pocketguard;

import java.io.Serializable;

public class Model implements Serializable {

    String BookName;
    String totalQuantity;
    int totalPrice;


    public Model(){

    }

    public Model(String BookName, int totalPrice, String totalQuantity) {

        this.BookName = BookName;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
