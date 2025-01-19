package edu.pharmacie;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AdminController {

    @FXML
    private StackPane adminViewContainer;
    @FXML
    private VBox buttonsList;
    @FXML
    private HBox dashboardMenuButton;
    @FXML
    private HBox employeeMenuButton;
    @FXML
    private HBox drugMenuButton;
    @FXML
    private HBox customerMenuButton;


    private HBox selectedItem;

    public void initialize(){
        selectedItem = dashboardMenuButton;
        for (Node node : buttonsList.getChildren()) {
            if (node instanceof HBox hBox && hBox.getStyleClass().contains("left-menu-button")) {
                hBox.setOnMouseClicked(this::handleMouseClick);
            }
        }
    }

    private void handleMouseClick(MouseEvent event) {
        handleMenuItemsClasses((HBox) event.getSource());

    }

    private void handleMenuItemsClasses(HBox clickedMenuItem){
        selectedItem.getStyleClass().remove("selected");
        selectedItem = clickedMenuItem;
        clickedMenuItem.getStyleClass().add("selected");
    }


}
