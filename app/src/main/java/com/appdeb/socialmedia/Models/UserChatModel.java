package com.appdeb.socialmedia.Models;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

public class UserChatModel {

    String userProfileName, userProfileDescription;
    Bitmap UserProfileImageBitmap;

    public UserChatModel(String userProfileName, String userProfileDescription, Bitmap userProfileImage) {
        this.userProfileName = userProfileName;
        this.userProfileDescription = userProfileDescription;
        this.UserProfileImageBitmap = userProfileImage;
    }

    public String getUserProfileName() {
        return userProfileName;
    }

    public void setUserProfileName(String userProfileName) {
        this.userProfileName = userProfileName;
    }

    public String getUserProfileDescription() {
        return userProfileDescription;
    }

    public void setUserProfileDescription(String userProfileDescription) {
        this.userProfileDescription = userProfileDescription;
    }

    public Bitmap getUserProfileImageBitmap() {
        return UserProfileImageBitmap;
    }

    public void setUserProfileImageBitmap(Bitmap userProfileImageBitmap) {
        UserProfileImageBitmap = userProfileImageBitmap;
    }
}
