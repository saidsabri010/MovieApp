package com.example.movieapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    public static final String QUERY_TOP_RATED_MOVIES = " https://api.themoviedb.org/3/movie/top_rated?api_key=9ed1d524180420adc3913bc93050c158";
    Context context;

    public MovieService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(List<MovieModel> movieModels);
    }

    public void getTopRated(VolleyResponseListener volleyResponseListener){
        String url = QUERY_TOP_RATED_MOVIES;
        List<MovieModel> movieModelList = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray movie_list = response.getJSONArray("results");
                    for (int i = 0; i < movie_list.length(); i++){
                        JSONObject first_movie = (JSONObject) movie_list.get(i);
                        MovieModel movieModel = new MovieModel();


                        movieModel.setId(first_movie.getInt("id"));
                        movieModel.setPoster_path(first_movie.getString("poster_path"));
                        movieModel.setTitle(first_movie.getString("title"));
                        movieModel.setOverview(first_movie.getString("overview"));
                        movieModelList.add(movieModel);

                    }
                    volleyResponseListener.onResponse(movieModelList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
