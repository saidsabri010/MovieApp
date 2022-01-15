package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button topRated, btnByID;
    RecyclerView listView;
    MovieAdapter movieAdapter;
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topRated = findViewById(R.id.btn_TopRated);
        listView = findViewById(R.id.btn_list);
        btnByID = findViewById(R.id.btnGetMovie);
        id = findViewById(R.id.txtID);
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
                        movieAdapter = new MovieAdapter(MainActivity.this, movieModels);
                        listView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        listView.setAdapter(movieAdapter);
                        //ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, movieModels);
                        // listView.setAdapter(arrayAdapter);
                    }
                });
            }
        });
        btnByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieService.getMovieById(id.getText().toString(), new MovieService.VolleyResponseListenerByID() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse(List<MovieModel> movieModels) {
                        movieAdapter = new MovieAdapter(MainActivity.this, movieModels);
                        listView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        listView.setAdapter(movieAdapter);
                    }
                });

            }
        });
    }
}