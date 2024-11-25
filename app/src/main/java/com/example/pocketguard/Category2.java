package com.example.pocketguard;

public class Category2 {

    private String BookImage;
    private String BookName;
    private String BookPrice;
    private String categoryId;
    private String description;

    public Category2() {
    }

    public Category2(String bookImage,String bookName,String bookPrice,String categoryId,String description) {

        BookImage=bookImage;
       BookName=bookName;
       BookPrice=bookPrice;
       this.categoryId=categoryId;
       this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBookPrice() {
        return BookPrice;
    }

    public void setBookPrice(String bookPrice) {
        BookPrice = bookPrice;
    }

    public String getBookImage() {
        return BookImage;
    }

    public void setBookImage(String bookImage) {
        BookImage = bookImage;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }
}
