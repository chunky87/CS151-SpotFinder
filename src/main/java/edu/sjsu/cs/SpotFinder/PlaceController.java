package edu.sjsu.cs.SpotFinder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PlaceController {
    @FXML private Label name;
    @FXML private Label address;
    @FXML private Label rating;
    @FXML private Label category;

    public void displayDetails(Place place) {
        name.setText(place.getName());
        address.setText(place.getAddress());
        rating.setText(String.format("%.2f stars", place.getRating()));
        category.setText(place.getCategory());
    }
}
