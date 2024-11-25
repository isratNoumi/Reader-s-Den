package com.example.adminapp;

public class Insert2 {
    private String BookName;
    private String BookImage;
    private String BookPrice;
    private String categoryId;
    private String mKey;
    private String Description;
    public Insert2() {
    }

    public Insert2( String bookImage,String bookName, String bookPrice, String categoryId,String description) {
        BookName = bookName;
        BookImage = bookImage;
        BookPrice = bookPrice;
        this.categoryId = categoryId;
        Description=description;

    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookImage() {
        return BookImage;
    }

    public void setBookImage(String bookImage) {
        BookImage = bookImage;
    }

    public String getBookPrice() {
        return BookPrice;
    }

    public void setBookPrice(String bookPrice) {
        BookPrice = bookPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
