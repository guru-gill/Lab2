package com.hands_on_android.lab2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hands_on_android.lab2.R;
import com.hands_on_android.lab2.api.ServiceGenerator;
import com.hands_on_android.lab2.api.model.BreedList;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Name: Kashyap K
Student A00209673:
 */

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private TextView breed_name, sub_breed_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.list_view);
        breed_name=(TextView) findViewById(R.id.breed_name);
        sub_breed_count=(TextView) findViewById(R.id.sub_breed_count);

        //Template code ends

        //Handle the click event for listView item

        //Finish implementing this request call
        //Once you get the response, Load the listView with the data you got from the request
        //ServiceGenerator.getService().getBreedsList();
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.view_list_item, R.id.breed_name);

        ServiceGenerator.getService().getBreedsList().enqueue(new Callback<BreedList>() {
            @Override
            public void onResponse(Call<BreedList> call, Response<BreedList> response) {
                if(response.isSuccessful() && response.body()!=null){
                    String[] from = {"breed_name", "sub_breed"};
                    int[] to = {R.id.breed_name, R.id.sub_breed_count};

                    SimpleAdapter adapter = new SimpleAdapter(
                            ListActivity.this,response.body().getBreedsForListView()
                            ,
                            R.layout.view_list_item,
                            from,
                            to

                    );
                    listView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<BreedList> call, Throwable t) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView breed = view.findViewById(R.id.breed_name);
                Intent intent = new Intent(ListActivity.this, ImageActivity.class);
                intent.putExtra("breed", breed.getText().toString());
                startActivity(intent);
            }
        });





    }


}

