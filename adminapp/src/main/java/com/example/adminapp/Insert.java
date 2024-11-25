package com.example.adminapp;

public class Insert {
    private String BookName;
    private String BookImage;
    private String BookPrice;
    private String categoryId;

    public Insert() {
    }

    public String getBookImage() {
        return BookImage;
    }

    public Insert(String BookName, String BookImage, String BookPrice, String categoryId) {
        this.BookName = BookName;
        this.BookImage = BookImage;
        this.BookPrice = BookPrice;
        this.categoryId = categoryId;
    }

    public void setBookImage(String BookImage) {
        this.BookImage = BookImage;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public String getBookPrice() {
        return BookPrice;
    }

    public void setBookPrice(String BookPrice) {
        this.BookPrice = BookPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
