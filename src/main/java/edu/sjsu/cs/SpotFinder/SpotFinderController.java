package edu.sjsu.cs.SpotFinder;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.temporal.Temporal;

@RestController
public class SpotFinderController {
    @FXML
    private TextField citySearchField;
    @FXML
    protected void onSearchButtonClick() {
        String city = citySearchField.getText();

    }
}
