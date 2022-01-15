package com.example.movieapp;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private static String movieID;
    public static final String QUERY_MOVIES_BY_ID = "https://api.themoviedb.org/3/movie/"+movieID+"?api_key=9ed1d524180420adc3913bc93050c158";
    Context context;
    List<MovieModel> movieModelList = new ArrayList<>();

    public MovieService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(List<MovieModel> movieModels);
    }

    public interface VolleyResponseListenerByID{
        void onError(String message);
        void onResponse(List<MovieModel> movieModels);
    }


    public void getTopRated(VolleyResponseListener volleyResponseListener){
        String url = QUERY_TOP_RATED_MOVIES;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray movie_list = response.getJSONArray("results");
                    for (int i = 0; i < movie_list.length(); i++){
                        JSONObject first_movie = (JSONObject) movie_list.get(i);
                        MovieModel movieModel = new MovieModel();


                        movieModel.setTitle(first_movie.getString("title"));
                        movieModel.setId(first_movie.getString("id"));
                        movieModel.setOverview(first_movie.getString("overview"));
                        movieModel.setVote_average(first_movie.getLong("vote_average"));
                        movieModel.setPoster_path(first_movie.getString("poster_path"));
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

    public void getMovieById(String movieID,VolleyResponseListenerByID volleyResponseListenerByID){
        String  url = "https://api.themoviedb.org/3/movie/"+movieID+"?api_key=9ed1d524180420adc3913bc93050c158";
        List<MovieModel> movieModelList = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = new JSONArray();
                    JSONObject object = array.getJSONObject(0);
                    MovieModel movieModel = new MovieModel();
                    movieModel.setTitle(object.getString("title"));
                    movieModel.setId(object.getString("id"));
                    movieModel.setOverview(object.getString("overview"));
                    movieModel.setVote_average(object.getLong("vote_average"));
                    movieModel.setPoster_path(object.getString("poster_path"));
                    movieModelList.add(movieModel);
                    volleyResponseListenerByID.onResponse(movieModelList);
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
