package edu.sjsu.cs.SpotFinder;

import edu.sjsu.cs.SpotFinder.JavaFXApplication.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Value("classpath:/SpotFinder.fxml")
    private Resource resource;
    private String applicationTitle;
    private ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui.title}")String applicationTitle,
                            ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
            fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));

            Parent parent = fxmlLoader.load();

            Stage stage = event.getStage();

            Scene scene = new Scene(parent, 800, 850);
            File stylesFile = new File("src/main/resources/styles.css");
            scene.getStylesheets().add(stylesFile.toURI().toURL().toExternalForm());
            stage.setScene(scene);

            stage.setTitle(applicationTitle);
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}