package edu.sjsu.cs.SpotFinder;

public class Place {
    private String name;
    private String address;
    private double rating;
    private String category;
    private double distance;

    public Place() {}
    public Place(String name, String address, double rating, String category, double distance) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.category = category;
        this.distance = distance;
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

    public void getReviews() {
        // Should be overridden by subclasses
    }
}
