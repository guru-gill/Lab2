package com.hands_on_android.lab2.activities;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
Name:Sarabjeet Kaur
Student #:A00210394
 */

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private TextView Dogsname, SubBreed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.list_view);
        Dogsname = (TextView) findViewById(R.id.breed_name);
        SubBreed = (TextView) findViewById(R.id.sub_breed_count);
/*
        //This is a template code. Delete this
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.view_list_item, R.id.breed_name);
        for (int i = 0; i < 10; i++) {
            adapter.add("Test " + (i + 1));
        }

        listView.setAdapter(adapter);
      //Template code ends
        ArrayList<HashMap<String, String>> items = getItems();
        String[] from = {};
        int[] to = {R.id.breed_name, R.id.sub_breed_count};
        SimpleAdapter adapter1 = new SimpleAdapter(this, items, R.layout.view_list_item, from, to);
        listView.setAdapter(adapter1);
        listView.setOnItemClickListener(this);
        */


        //Handle the click event for listView items

        ServiceGenerator.getService().getBreedsList().enqueue(new Callback<BreedList>() {
            @Override
            public void onResponse(Call<BreedList> call, Response<BreedList> response) {
                //Toast.makeText(getApplicationContext(),String.valueOf(response.body().getBreedsForListView()),Toast.LENGTH_LONG).show();

                if(response.isSuccessful() && response.body()!= null){
                    String[] from = {"breedname", "breed"};
                    int[] to = {R.id.breed_name, R.id.sub_breed_count};

                    SimpleAdapter adapter = new SimpleAdapter(
                            ListActivity.this,response.body().getBreedsForListView(),
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

        //Finish implementing this request call
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView Breed1 = view.findViewById(R.id.breed_name);
                Intent intent1 = new Intent(ListActivity.this, ImageActivity.class);
                intent1.putExtra("namebreed",Breed1.getText().toString());
                startActivity(intent1);
            }
        });
        //Once you get the response, Load the listView with the data you got from the request
        //ServiceGenerator.getService().getBreedsList();
    }
}
