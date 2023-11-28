package edu.sjsu.cs.SpotFinder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlaceController {
    @FXML private Label businessNameLabel;
    @FXML private ScrollPane scrollPane;
    @FXML private Label name1, name2, name3, name4, name5, name6, name7, name8, name9, name10;
    @FXML private Label address1, address2, address3, address4, address5, address6, address7, address8, address9, address10;
    @FXML private Hyperlink rating1, rating2, rating3, rating4, rating5, rating6, rating7, rating8, rating9, rating10;
    @FXML private Label category1, category2, category3, category4, category5, category6, category7, category8, category9, category10;
    @FXML private Label distance1, distance2, distance3, distance4, distance5, distance6, distance7, distance8, distance9, distance10;
    @FXML private Label review1, review2, review3;
    @FXML private VBox rootVBox; 
    @FXML private Label firstText;

   
    public void initialize() {
        Platform.runLater(() -> {
            setScrollToTop();
            rootVBox.requestFocus(); // Set focus to the rootVBox or any other node you prefer
        });
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
            Stage stage = (Stage) name1.getScene().getWindow();

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
        // Create arrays for each label type
        Label[] nameLabels = {name1, name2, name3, name4, name5, name6, name7, name8, name9, name10};
        Label[] addressLabels = {address1, address2, address3, address4, address5, address6, address7, address8, address9, address10};
        Label[] categoryLabels = {category1, category2, category3, category4, category5, category6, category7, category8, category9, category10};
        Label[] distanceLabels = {distance1, distance2, distance3, distance4, distance5, distance6, distance7, distance8, distance9, distance10};
        Hyperlink[] ratingLinks = {rating1, rating2, rating3, rating4, rating5, rating6, rating7, rating8, rating9, rating10};

        for (Hyperlink link : ratingLinks) {
            link.setStyle("-fx-underline: true; -fx-font-size: 16; -fx-text-fill: #e28743; -fx-font-style: italic;");
        }

        // Loop through the places and update the labels
        int j = 1;
        for (int i = 0; i < places.size() && i < 10; i++) {
            Place place = places.get(i);

            nameLabels[i].setText("\n" + j + ") " + place.getName());
            addressLabels[i].setText("Address: " + place.getAddress());
            ratingLinks[i].setText("Rating: " + String.valueOf(place.getRating()) + "\n");

            int finalI = i; 
            ratingLinks[i].setOnAction(event -> handleRatingClick(places.get(finalI)));

            categoryLabels[i].setText("Category: " + place.getCategory());

            long roundedDistance = Math.round(place.getDistance());
            distanceLabels[i].setText("Distance from you: " + roundedDistance + " meters.\n");

            j++;
        }
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
        name1.setText(noResults.toString());
    }

    public void setBusinessName(String businessName) {
        businessNameLabel.setText("Reviews for " + businessName);
    }

    public void handleRatingClick(Place place) {
            try {
            // Load SpotFinder.fxml
            File fxmlFile = new File("src/main/resources/Reviews.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            Parent root = fxmlLoader.load();

            PlaceController placeController = fxmlLoader.getController();
            placeController.setBusinessName(place.getName());
            placeController.displayReviews(place);

            Stage newStage = new Stage();
            Scene scene = new Scene(root, 800, 750);

            File stylesFile = new File("src/main/resources/styles.css");
            scene.getStylesheets().add(stylesFile.toURI().toURL().toExternalForm());

            // Set the new Scene to the stage
            newStage.setScene(scene);
            newStage.setTitle(place.getName());

            // Show the stage
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
        }
    }


    private void displayReviews(Place place) {
        Label[] reviewLabels = {review1, review2, review3};
        int reviewIndex = 0;

        for(int i = 0; i < 3; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            Review review = place.getReviews().get(i);
            stringBuilder.append((reviewIndex+1) + ")\n");
            stringBuilder.append("Rating: " + review.getRating() + "\n");
            stringBuilder.append("User: " + review.getUsername() + "\n");
            stringBuilder.append("Comment: " + review.getComment() + "\n");
            stringBuilder.append("Date of Review: " + review.getDate());

            reviewLabels[i].setText(stringBuilder.toString());
            reviewIndex += 1;
        }   
    }

    @FXML
    protected void goBackToResults() {
        Stage currentStage = (Stage) businessNameLabel.getScene().getWindow(); 
        currentStage.close();
    }
}