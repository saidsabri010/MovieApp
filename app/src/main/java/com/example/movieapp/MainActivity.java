package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button topRated;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topRated = findViewById(R.id.btn_TopRated);
        listView = findViewById(R.id.btn_list);
        MovieService movieService = new MovieService(MainActivity.this);

        topRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieService.getTopRated(new MovieService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "it didn't work", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(List<MovieModel> movieModels) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, movieModels);
                        listView.setAdapter(arrayAdapter);
                    }
                });
            }
        });
    }
}