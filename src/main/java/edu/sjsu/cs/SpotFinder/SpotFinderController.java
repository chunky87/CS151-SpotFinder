package edu.sjsu.cs.SpotFinder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SpotFinderController {
    
    private VBox placesContainer;
    private final String apiKey;
    private final String apiUrl = "https://api.yelp.com/v3/businesses/search";
    
    
    private final HttpHeaders headers;


    private String readApiKeyFromFile(String filePath) {
        String apiKey = null;
        try {
            apiKey = new String(Files.readAllBytes(Paths.get(filePath)));
         } 
         catch (IOException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
        return apiKey;
    }   

    public SpotFinderController() {
            apiKey = readApiKeyFromFile("C:/Users/Mathew/access.txt");
            headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("accept", "application/json");
    }

    private Stage currentStage; 
    
    public void setStage(Stage stage) {
        this.currentStage = stage;
    }

    @FXML
    private TextField citySearchField;

    @FXML
    protected void onSearchButtonClick() { 
        int limit = 10; 
        int radius = 5000; 
        String city = citySearchField.getText();

        String searchUrl = apiUrl + "?location=" + city + "&limit=" + limit + "&radius=" + radius;

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // Make the request to the Yelp API
        ResponseEntity<String> response = restTemplate.exchange(searchUrl, HttpMethod.GET, entity, String.class);

        // Inside the SpotFinderController.java after making the API request
        ObjectMapper objectMapper = new ObjectMapper();
        List<Place> places = new ArrayList<>();

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();

            try {
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                // Assuming 'businesses' is an array of business objects in the JSON response
                JsonNode businesses = jsonNode.get("businesses");

                // Loop through each business
                for (JsonNode business : businesses) {
                    String name = business.get("name").asText();
                    String address = business.get("location").get("address1").asText();
                    double rating = business.get("rating").asDouble();
                    String category = business.get("categories").get(0).get("title").asText();

                    // Create a Place object to be in list
                    Place newPlace = new Place(name, address, rating, category);
                    places.add(newPlace);
                }

                showDetailsInNewPage(places);
            } 
            
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showDetailsInNewPage(List<Place> places) {
        try {
            File fxmlFile = new File("src/main/resources/NewPage.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            Parent parent2 = fxmlLoader.load();

            PlaceController placeController = fxmlLoader.getController();
            placeController.displayDetails(places);
            
            Stage newStage = new Stage();
            newStage.setScene(new Scene(parent2, 800, 850));
            newStage.setTitle("Location Details");
            
            // Show the new stage
            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
