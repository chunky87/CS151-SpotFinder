package edu.sjsu.cs.SpotFinder;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class SpotFinderController {

    private List<Place> places = new ArrayList<>();

    @FXML private ImageView imageView;
    private final String apiKey;
    private final String apiUrl = "https://api.yelp.com/v3/businesses/search";    
    private final String apiUrl2 = "https://api.yelp.com/v3/businesses";   
    
    Dotenv dotenv = Dotenv.configure().directory("./").filename("secret.env").load();
    
    private final HttpHeaders headers; 

    public SpotFinderController() {
            apiKey = dotenv.get("API_KEY");
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

    public void initialize() {

        citySearchField.setPromptText("Ex: San Jose, CA");
        Platform.runLater(() -> citySearchField.getParent().requestFocus());
    }

    @FXML
    protected void onSearchButtonClick() { 
        int limit = 10; 
        int radius = 3000; 
        String city = citySearchField.getText();

        String searchUrl = apiUrl + "?location=" + city + "&limit=" + limit + "&radius=" + radius;

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        try {

            if (currentStage == null) {
                currentStage = (Stage) citySearchField.getScene().getWindow();
            }
            // Make the request to the Yelp API
            ResponseEntity<String> response = restTemplate.exchange(searchUrl, HttpMethod.GET, entity, String.class);

            // Inside the SpotFinderController.java after making the API request
            ObjectMapper objectMapper = new ObjectMapper();

            if (response.getStatusCode().is2xxSuccessful()) {
                String responseBody = response.getBody();

                try {
                    JsonNode jsonNode = objectMapper.readTree(responseBody);

                    // Assuming 'businesses' is an array of business objects in the JSON response
                    JsonNode businesses = jsonNode.get("businesses");

                    // Check if there are any results
                    if (businesses.size() == 0) {
                        // No results found
                        showNoResults();
                    } else {
                        // Loop through each business
                        for (JsonNode business : businesses) {
                            String name = business.get("name").asText();
                            String address = business.get("location").get("address1").asText();
                            double rating = business.get("rating").asDouble();
                            String category = business.get("categories").get(0).get("title").asText();
                            double distance = business.get("distance").asDouble();
                            String businessId = business.get("id").asText();

                            // Create a Place object
                            Place newPlace = new Place(name, address, rating, category, distance);
                            newPlace.setId(businessId);
                            fetchReviewsForPlace(newPlace);
                            places.add(newPlace);
                        }
                        showDetailsInNewPage(places);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (HttpClientErrorException e) {
            if (e.getRawStatusCode() == 400) {
                // Bad Request, location not found
                showNoResults();
            } else {
                // Handle other HTTP client errors as needed
                e.printStackTrace();
            }
        }
    }

    private void fetchReviewsForPlace(Place place) {
        String reviewsUrl = apiUrl2 + "/" + place.getId() + "/reviews?limit=5&sort_by=yelp_sort";
    
        RestTemplate restTemplate2 = new RestTemplate();
        HttpEntity<String> entity2 = new HttpEntity<>("parameters", headers);
    
        ResponseEntity<String> reviewsResponse = restTemplate2.exchange(reviewsUrl, HttpMethod.GET, entity2, String.class);
    
        if (reviewsResponse.getStatusCode().is2xxSuccessful()) {
            String reviewsBody = reviewsResponse.getBody();
    
            try {
                ObjectMapper objectMapper2 = new ObjectMapper();
                JsonNode reviewsNode = objectMapper2.readTree(reviewsBody).get("reviews");
    
                for (JsonNode reviewNode : reviewsNode) {
                    String username = reviewNode.get("user").get("name").asText();
                    double rating = reviewNode.get("rating").asDouble();
                    String comment = reviewNode.get("text").asText();
                    String date = reviewNode.get("time_created").asText();
    
                    // Create a Review object
                    Review review = new Review(username, rating, comment, date);
                    place.getReviews().add(review);
                }
            } catch (IOException e) {
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

            currentStage.close();
            
            Stage newStage = new Stage();
            Scene scene = new Scene(parent2, 800, 750);
            
            File stylesFile = new File("src/main/resources/styles.css");
            scene.getStylesheets().add(stylesFile.toURI().toURL().toExternalForm());

            newStage.setTitle("Location Details");
            newStage.setScene(scene);

            currentStage = newStage;
            
            // Show the new stage
            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNoResults() {
        try {
            File fxmlFile = new File("src/main/resources/NoResults.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            Parent parent3 = fxmlLoader.load();

            PlaceController placeController = fxmlLoader.getController();
            placeController.showNone();

            currentStage.close();
            
            Stage newStage = new Stage();
            Scene scene2 = new Scene(parent3, 800, 750);
            newStage.setScene(scene2);

            File stylesFile = new File("src/main/resources/styles.css");
            scene2.getStylesheets().add(stylesFile.toURI().toURL().toExternalForm());
            newStage.setTitle("No Results Found");

            currentStage = newStage;
            
            // Show the new stage
            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
