package edu.pharmacie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(edu.pharmacie.Main.class.getResource("views/main-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Pharmacie Bon LeBon");
                stage.getIcons().add(new Image(edu.pharmacie.Main.class.getResourceAsStream("logo.jpeg")));
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