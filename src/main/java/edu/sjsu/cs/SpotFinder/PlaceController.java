package edu.sjsu.cs.SpotFinder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class PlaceController {
    @FXML private ScrollPane scrollPane;
    @FXML private Label name;
    @FXML private Label address;
    @FXML private Label rating;
    @FXML private Label category;
    @FXML private VBox rootVBox; 
    @FXML private Label firstText;

   
    public void initialize() {
        Platform.runLater(this::setScrollToTop);
    }

    private void setScrollToTop() {
        scrollPane.setVvalue(0.0);
    }

    @FXML
    protected void goBackToHomepage() {
        try {
            // Load SpotFinder.fxml
            File fxmlFile = new File("src/main/resources/SpotFinder.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            Parent root = fxmlLoader.load();

            // Create a new Scene with the loaded FXML file
            Scene scene = new Scene(root, 800, 750);

            // Get the current stage (assuming the current stage is the one displaying the results page)
            Stage stage = (Stage) name.getScene().getWindow();

            // Set the new Scene to the stage
            stage.setScene(scene);
            stage.setTitle("Homepage");

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
        }
    }

    public void displayDetails(List<Place> places) {
        StringBuilder details = new StringBuilder();
        int i = 1;

        // loop through place objects
        for (Place place : places) {
            details.append(i + ")\n");
            details.append("Business Name: ").append(place.getName()).append("\n");
            details.append("Address: ").append(place.getAddress()).append("\n");
            details.append("Rating: ").append(String.format("%.2f stars", place.getRating())).append("\n");
            details.append("Category: ").append(place.getCategory()).append("\n");

            long roundedDistance = Math.round(place.getDistance());
            details.append("Distance from you: ").append(roundedDistance).append(" meters").append("\n\n");
            i++;
        }

        name.setText(details.toString());
    }

    public void showNone() {
        StringBuilder noResults = new StringBuilder();
        noResults.append("\nNo results found!");
        noResults.append("\n");
        noResults.append("You live in the middle of nowhere  :c\n\n\n");  
        noResults.append("▒▒▒▒▒▒▒█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n");
        noResults.append("▒▒▒▒▒▒▒█░▒▒▒▒▒▒▒▓▒▒▓▒▒▒▒▒▒▒░█\n");
        noResults.append("▒▒▒▒▒▒▒█░▒▒▓▒▒▒▒▒▒▒▒▒▄▄▒▓▒▒░█░▄▄\n");
        noResults.append("▒▒▄▀▀▄▄█░▒▒▒▒▒▒▓▒▒▒▒█░░▀▄▄▄▄▄▀░░█\n");
        noResults.append("▒▒█░░░░█░▒▒▒▒▒▒▒▒▒▒▒█░░░░░░░░░░░█\n");
        noResults.append("▒▒▒▀▀▄▄█░▒▒▒▒▓▒▒▒▓▒█░░░█▒░░░░█▒░░█\n");
        noResults.append("▒▒▒▒▒▒▒█░▒▓▒▒▒▒▓▒▒▒█░░░░░░░▀░░░░░█\n");
        noResults.append("▒▒▒▒▒▄▄█░▒▒▒▓▒▒▒▒▒▒▒█░░█▄▄█▄▄█░░█\n");
        noResults.append("▒▒▒▒█░░░█▄▄▄▄▄▄▄▄▄▄█░█▄▄▄▄▄▄▄▄▄█\n");
        noResults.append("▒▒▒▒█▄▄█░░█▄▄█░░░░░░█▄▄█░░█▄▄█ \n\n");        
        
        noResults.append("              ───▄▀▀▀▄▄▄▄▄▄▄▀▀▀▄───\n");
        noResults.append("              ───█▒▒░░░░░░░░░▒▒█───\n");
        noResults.append("              ────█░░█░░░░░█░░█────\n");
        noResults.append("              ─▄▄──█░░░▀█▀░░░█──▄▄─\n");
        noResults.append("              █░░█─▀▄░░░░░░░▄▀─█░░█");
        name.setText(noResults.toString());
    }
}