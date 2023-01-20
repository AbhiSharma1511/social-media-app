package com.appdeb.socialmedia.Models;

import android.graphics.Bitmap;
public class CurrentUserPostModel{

    Bitmap bitmap;

    public CurrentUserPostModel(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
