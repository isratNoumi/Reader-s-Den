package com.example.adminapp;

public class Category {
    private String categoryTitle;
    private  String categoryId;

    public Category( ) {

    }

    public Category(String categoryTitle) {

        this.categoryTitle=categoryTitle;


    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
