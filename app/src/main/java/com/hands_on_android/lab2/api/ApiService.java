package com.hands_on_android.lab2.api;

import com.hands_on_android.lab2.api.model.BreedList;
import com.hands_on_android.lab2.api.model.RandomImage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("breeds/list/all")
    Call<BreedList> getBreedsList();

    //Finish implementing this function
    //Hint: For this request you will need to add breed to the request path.
    //Search Retrofit's page to learn how you can achieve this
    @GET("breed/{catname}/images/random")
    Call<RandomImage> getRandomImage(@Path("catname")  String breed);
}
