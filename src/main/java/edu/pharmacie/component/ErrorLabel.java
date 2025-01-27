package edu.pharmacie.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class ErrorLabel extends HBox {

    public ErrorLabel(String text){
        setPrefWidth(300);
        setAlignment(Pos.TOP_LEFT);
        VBox.setMargin(this, new Insets(0, 0, 5, 0));
        Label cross = new Label("⦾ ");
        cross.setStyle("-fx-text-fill: #6c0202");
        Label label = new Label(text);
        getStyleClass().add("form-error-container");
        label.setStyle("-fx-text-fill: #6c0202");
        setMargin(this,new Insets(2));
        getChildren().addAll(cross,label);
    }

}
