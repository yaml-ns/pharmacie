package edu.pharmacie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        FontIcon fontIcon = new FontIcon(FontAwesomeSolid.ARROW_CIRCLE_RIGHT);
        fontIcon.setIconSize(15);
        fontIcon.setIconColor(Color.WHITE);
        loginButton.setGraphic(fontIcon);

    }

    @FXML
    protected void onLoginButton() {
        System.out.println("Hello world");
    }
}