package edu.pharmacie.component;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class ErrorLabel extends Label {

    public ErrorLabel(String text){
        setText(text);
        setMinWidth(Double.MAX_VALUE);
        setStyle("-fx-text-fill: red");
    }

}
