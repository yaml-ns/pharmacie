package edu.pharmacie.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminController {

    @FXML
    private StackPane adminViewContainer;

    private Node dashboardView;
    private Node employeeView;
    private Node drugView;
    private Node customerView;

    private Node currentView;

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

    public void initialize() throws IOException {
        selectedItem = dashboardMenuButton;
        addMenuButtonListeners();

        dashboardView = loadView("/edu/pharmacie/views/dashboard-view.fxml");
        employeeView = loadView("/edu/pharmacie/views/employee-view.fxml");
        drugView = loadView("/edu/pharmacie/views/drug-view.fxml");
        customerView = loadView("/edu/pharmacie/views/customer-view.fxml");
        currentView = dashboardView;
        adminViewContainer.getChildren().add(currentView);

    }

    private Node loadView(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        return loader.load();
    }
    private void addMenuButtonListeners(){
        for (Node node : buttonsList.getChildren()) {
            if (node instanceof HBox hBox && hBox.getStyleClass().contains("left-menu-button")) {
                hBox.setOnMouseClicked(this::handleMouseClick);
            }
        }
    }
    private void handleMouseClick(MouseEvent event) {
        HBox clickedItem = (HBox) event.getSource();
        String viewName = (String) clickedItem.getUserData();
        handleMenuItemsClasses(clickedItem);
        try {
        Node view = (Node) getClass().getDeclaredField(viewName).get(this);
        currentView = view;
        showView(view);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    private void showView(Node view) {
        adminViewContainer.getChildren().clear();
        adminViewContainer.getChildren().add(view);
    }

    private void handleMenuItemsClasses(HBox clickedMenuItem){
        selectedItem.getStyleClass().remove("selected");
        selectedItem = clickedMenuItem;
        clickedMenuItem.getStyleClass().add("selected");
    }


}
