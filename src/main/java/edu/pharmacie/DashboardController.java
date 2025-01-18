package edu.pharmacie;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class DashboardController {
    @FXML
    private HBox dashboardMenuButton;

    public void initialize(){
        dashboardMenuButton.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent event) {
        HBox clickedHbox = (HBox) event.getSource();
        clickedHbox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #19C896;");
        System.out.println(clickedHbox.getId());
    }

}
