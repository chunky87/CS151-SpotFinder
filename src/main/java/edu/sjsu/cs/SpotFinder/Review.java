package edu.sjsu.cs.SpotFinder;

public class Review {
    private String username;
    private double rating;
    private String comment;
    private String date;

    public Review(String username, double rating, String comment, String date) {
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }
}
