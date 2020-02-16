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
    static String breed;
    static String imageUrl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView = findViewById(R.id.image_view);

        //Create your own layout file and add it here
        //The layout must contain an ImageView to render the image
        Intent intent = getIntent();
        breed = intent.getStringExtra("breed");


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //Update the value of setTitle function to use selected breed name.
            getSupportActionBar().setTitle(breed);
        }

        ServiceGenerator.getService().getRandomImage(breed).enqueue(new Callback<RandomImage>() {
            @Override
            public void onResponse(Call<RandomImage> call, Response<RandomImage> response) {
                if(response.isSuccessful() && response.body()!=null){
                    imageUrl = response.body().getImageUrl();
                    Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
                }
            }

            @Override
            public void onFailure(Call<RandomImage> call, Throwable t) {

            }
        });

    //Get a random image of selected breed and parse it into the image view
        //Hint: You will want to keep track of url for currently shown dog,
        // so that you can open currently shown image in the browser
    }



    private void handleRefresh() {
        //Add a feature to refresh image here
        ServiceGenerator.getService().getRandomImage(breed).enqueue(new Callback<RandomImage>() {
            @Override
            public void onResponse(Call<RandomImage> call, Response<RandomImage> response) {
                if(response.isSuccessful() && response.body()!=null){
                    imageUrl = response.body().getImageUrl();
                    Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
                }
            }

            @Override
            public void onFailure(Call<RandomImage> call, Throwable t) {

            }
        });



    }

    private void handleOpen() {
        //Hint: handle a case when the dog image is not shown
        //What would be the value of current url if the http request hasn't been finished?
        //Add a feature to open the current image on web browser here
        Uri imageUri = Uri.parse(imageUrl);
        Intent intent =  new Intent(Intent.ACTION_VIEW,imageUri);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
