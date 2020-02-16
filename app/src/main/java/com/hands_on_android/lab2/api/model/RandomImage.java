package com.hands_on_android.lab2.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RandomImage implements Serializable {

    @SerializedName("message")
    String ImageURL;

    public String getImageURL()
    {
        return ImageURL;
    }
}
