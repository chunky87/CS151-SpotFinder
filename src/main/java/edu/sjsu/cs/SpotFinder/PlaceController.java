package edu.sjsu.cs.SpotFinder;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PlaceController {
    @FXML private Label name;
    @FXML private Label address;
    @FXML private Label rating;
    @FXML private Label category;

    public void displayDetails(List<Place> places) {
        StringBuilder details = new StringBuilder();
        int i = 1;

        // loop through place objects
        for (Place place : places) {
            details.append(i + ")\n");
            details.append("Business Name: ").append(place.getName()).append("\n");
            details.append("Address: ").append(place.getAddress()).append("\n");
            details.append("Rating: ").append(String.format("%.2f stars", place.getRating())).append("\n");
            details.append("Category: ").append(place.getCategory()).append("\n\n");
            i++;
        }

        name.setText(details.toString());
    }
}

