package edu.pharmacie;

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
               FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("views/login-view.fxml"));
               FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("views/admin-view.fxml"));

               loginView = loginLoader.load();
             try {

               adminView = adminLoader.load();
             }catch (Exception e){
                 System.out.println(e.getCause());
                 System.out.println(e.getMessage());
                 System.out.println(e.getClass());
             }
               adminView.setVisible(false);

               LoginController loginController = loginLoader.getController();
               loginController.setMainController(this);

               mainContainer.getChildren().addAll(loginView, adminView);


           }catch (Exception e){
               System.out.println(e.getStackTrace());
           }
    }

    public void showDashboard(){
        loginView.setVisible(false);
        adminView.setVisible(true);
    }

}