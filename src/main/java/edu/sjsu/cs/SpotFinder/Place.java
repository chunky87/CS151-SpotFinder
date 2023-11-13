package edu.sjsu.cs.SpotFinder;

public class Place {
    private String name;
    private String address;
    private double rating;
    private String category;

    public Place() {}
    public Place(String name, String address, double rating, String category) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.category = category;
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

    public void getReviews() {
        // Should be overridden by subclasses
    }
}
