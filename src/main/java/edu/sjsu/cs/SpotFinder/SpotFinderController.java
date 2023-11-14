package edu.sjsu.cs.SpotFinder;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpotFinderController {
    @FXML
    private TextField citySearchField;
    @FXML
    protected void onSearchButtonClick() {
        String city = citySearchField.getText();

        if (city.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Search failed.");
            alert.setHeaderText(null);
            alert.setContentText("Please enter something before searching.");
            alert.showAndWait();
        }
        else {
            // (perform search here)
            VBox resultsLayout = new VBox();
            Scene resultsScene = new Scene(resultsLayout, 800, 600);

            Stage primaryStage = (Stage) citySearchField.getScene().getWindow();
            primaryStage.setScene(resultsScene);
        }
    }
}
