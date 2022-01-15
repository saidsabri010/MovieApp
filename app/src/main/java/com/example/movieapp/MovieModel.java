package com.example.movieapp;

public class MovieModel {
    private String id;
    private String overview;
    private String poster_path;
    private String title;
    private float vote_average;

    public MovieModel(String id, String overview, String poster_path, String title, float vote_average) {
        this.id = id;
        this.overview = overview;
        this.poster_path = poster_path;
        this.title = title;
        this.vote_average = vote_average;
    }

    public MovieModel() {
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", overview='" + overview + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }
}
