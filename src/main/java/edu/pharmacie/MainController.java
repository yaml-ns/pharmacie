package edu.pharmacie;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane mainContainer;
    private Pane loginView;
    private Pane dashboardView;
    @FXML
    public void initialize(){
           try {
               FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("views/login-view.fxml"));
               FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("views/dashboard-view.fxml"));

               loginView = loginLoader.load();
             try {

               dashboardView = dashboardLoader.load();
             }catch (Exception e){
                 System.out.println(e.getCause());
                 System.out.println(e.getMessage());
                 System.out.println(e.getClass());
             }
               dashboardView.setVisible(false);

               LoginController loginController = loginLoader.getController();
               loginController.setMainController(this);

               mainContainer.getChildren().addAll(loginView,dashboardView);


           }catch (Exception e){
               System.out.println("ici");
               System.out.println(e.getStackTrace());
           }
    }

    public void showDashboard(){
        loginView.setVisible(false);
        dashboardView.setVisible(true);
    }

}