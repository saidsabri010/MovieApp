package com.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>  {
    private Context context;
    private List<MovieModel> movieModelList;

    public MovieAdapter(Context context, List<MovieModel> movieModelList) {
        this.context = context;
        this.movieModelList = movieModelList;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_image, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        holder.title.setText(movieModelList.get(position).getTitle());
        holder.id.setText(movieModelList.get(position).getId());
        holder.overview.setText(movieModelList.get(position).getOverview());
        //holder.voteAverage.setText((int) movieModelList.get(position).getVote_average());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+movieModelList.get(position).getPoster_path()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView id;
        TextView title;
        TextView overview;
        TextView voteAverage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image_view_upload);
            id = itemView.findViewById(R.id.id);
            overview = itemView.findViewById(R.id.overview);
            //voteAverage = itemView.findViewById(R.id.voteAverage);
        }
    }
}
