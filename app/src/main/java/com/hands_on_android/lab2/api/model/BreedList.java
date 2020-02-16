package com.hands_on_android.lab2.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BreedList implements Serializable {

    @SerializedName("message")
    HashMap<String, ArrayList<String>> breedsMap;

    public ArrayList<HashMap<String, String>> getBreedsForListView() {
        //In this function, we will convert the breedMap into an ArrayList to be used for ListView



        //Before building the result, we want to create an ArrayList of sorted breed names
        ArrayList<String> sortedBreeds = new ArrayList<>(breedsMap.keySet());
        Collections.sort(sortedBreeds);

        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        //Using sortedBreeds, populate the result
for(String breeds : sortedBreeds){
    ArrayList<String> BreedLists = breedsMap.get(breeds);
    HashMap<String, String> item = new HashMap<>();
    item.put("breedname", breeds);
    item.put("breed", String.valueOf(BreedLists.size()));
    result.add(item);
}

        return result;
    }
}
