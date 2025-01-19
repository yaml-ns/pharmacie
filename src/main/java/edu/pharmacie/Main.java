package edu.pharmacie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(edu.pharmacie.Main.class.getResource("views/main-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                scene.getStylesheets().add(edu.pharmacie.Main.class.getResource("style.css").toExternalForm());
                stage.setTitle("Pharmacie Bon LeBon");
                stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("logo.jpeg"))));
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

    }

    public static void main(String[] args) {
        launch();
    }
}