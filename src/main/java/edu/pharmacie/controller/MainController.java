package edu.pharmacie.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainController {

    @FXML
    private StackPane mainContainer;
    private Pane loginView;
    private Pane adminView;
    @FXML
    public void initialize(){
           try {
               FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/edu/pharmacie/views/login-view.fxml"));
               FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("/edu/pharmacie/views/admin-view.fxml"));

               loginView = loginLoader.load();
               adminView = adminLoader.load();
               adminView.setVisible(false);

               LoginController loginController = loginLoader.getController();
               loginController.setMainController(this);

               mainContainer.getChildren().addAll(loginView, adminView);

           }catch (Exception e){
               e.getStackTrace();
           }
    }

    public void showDashboard(){
        loginView.setVisible(false);
        adminView.setVisible(true);
    }

}