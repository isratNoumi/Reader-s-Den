package com.example.pocketguard;

public class Category {
    private String categoryTitle;
    private String categoryId;


    public Category( ) {

    }

    public Category(String categoryTitle) {
        this.categoryTitle=categoryTitle;

    }



    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
