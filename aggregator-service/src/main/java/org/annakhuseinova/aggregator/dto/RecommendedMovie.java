package org.annakhuseinova.aggregator.dto;

public class RecommendedMovie {

    private String title;
    private int year;
    private double rating;

    public RecommendedMovie(){

    }

    public RecommendedMovie(String title, int year, double rating) {
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
