package com.hands_on_android.lab2.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RandomImage implements Serializable {

    //Add model details to this class
    @SerializedName("message")
    String imageUrl;

    public String getImageUrl(){
        return imageUrl;
    }

}
