package com.hands_on_android.lab2.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hands_on_android.lab2.R;
import com.hands_on_android.lab2.api.ServiceGenerator;
import com.hands_on_android.lab2.api.model.RandomImage;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageActivity extends AppCompatActivity {
    private static ImageView imageView;
    static String BreedList;
    static String ImageURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);
        imageView = findViewById(R.id.view_image);

        //Create your own layout file and add it here

        Intent intent = getIntent();
        BreedList = intent.getStringExtra("namebreed");

        //The layout must contain an ImageView to render the image


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //Update the value of setTitle function to use selected breed name.
            getSupportActionBar().setTitle(BreedList);
        }


        //Get a random image of selected breed and parse it into the image view
        ServiceGenerator.getService().getRandomImage(BreedList).enqueue(new Callback<RandomImage>() {
            @Override
            public void onResponse(Call<RandomImage> call, Response<RandomImage> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ImageURL = response.body().getImageURL();
                    Picasso.get().load(ImageURL).fit().centerInside().into(imageView);
                }
            }

            @Override
            public void onFailure(Call<RandomImage> call, Throwable t) {

            }
        });

    }

        private void handleRefresh(){
            ServiceGenerator.getService().getRandomImage(BreedList).enqueue(new Callback<RandomImage>() {
                @Override
                public void onResponse(Call<RandomImage> call, Response<RandomImage> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ImageURL = response.body().getImageURL();
                        Picasso.get().load(ImageURL).fit().centerInside().into(imageView);
                    }
                }

                @Override
                public void onFailure(Call<RandomImage> call, Throwable t) {

                }
            });
        }
        //Add a feature to refresh image here


        private void handleOpen() {
            Uri ImageUri = Uri.parse(ImageURL);
            Intent intent1 = new Intent(Intent.ACTION_VIEW, ImageUri);
            startActivity(intent1);

        }
            @Override
            public boolean onCreateOptionsMenu (Menu menu){
                getMenuInflater().inflate(R.menu.image_menu, menu);
                return super.onCreateOptionsMenu(menu);
            }

            @Override
            public boolean onOptionsItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case android.R.id.home:
                        finish();
                        break;
                    case R.id.action_refresh:
                        handleRefresh();
                        break;
                    case R.id.action_open:
                        handleOpen();
                        break;
                }

                return true;
            }
        }
