package edu.sjsu.cs.SpotFinder;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ReviewsController {

    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star5;

    public void initialize() {
        // Example: Set the initial rating (replace this with your actual rating value)
       // double rating = /* Get the rating from your data source */;
       // setStarRating(rating);
    }

    private void setStarRating(double rating) {
        // Calculate the number of filled stars based on the rating
        int filledStars = (int) Math.round(rating);

        // Load star images (replace these paths with your actual image paths)
        Image filledStarImage = new Image("/path/to/filled_star.png");
        Image emptyStarImage = new Image("/path/to/empty_star.png");

        // Set star images
        star1.setImage(filledStars >= 1 ? filledStarImage : emptyStarImage);
        star2.setImage(filledStars >= 2 ? filledStarImage : emptyStarImage);
        star3.setImage(filledStars >= 3 ? filledStarImage : emptyStarImage);
        star4.setImage(filledStars >= 4 ? filledStarImage : emptyStarImage);
        star5.setImage(filledStars >= 5 ? filledStarImage : emptyStarImage);
    }
}
