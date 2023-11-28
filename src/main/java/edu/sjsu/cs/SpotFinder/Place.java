package edu.sjsu.cs.SpotFinder;

import java.util.ArrayList;
import java.util.List;

public class Place {
    private String name;
    private String address;
    private double rating;
    private String category;
    private double distance;
    private List<Review> reviews;
    private String id;

    public Place() {}
    public Place(String name, String address, double rating, String category, double distance) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.category = category;
        this.distance = distance;
        this.reviews = new ArrayList<>();
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Object getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public double getDistance() {
        return distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
