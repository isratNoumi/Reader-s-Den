package com.example.pocketguard;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class Helper {
    int image;
    String title;
    GradientDrawable color;

    public Helper(GradientDrawable color, int image, String title) {
        this.image = image;
        this.title = title;
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }


    public Drawable getgradient() {
        return color;
    }
}
