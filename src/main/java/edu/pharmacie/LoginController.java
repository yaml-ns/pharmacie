package edu.pharmacie;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private MainController mainController;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        FontIcon fontIcon = new FontIcon(FontAwesomeSolid.ARROW_CIRCLE_RIGHT);
        fontIcon.setIconSize(15);
        fontIcon.setIconColor(Color.WHITE);
        loginButton.setGraphic(fontIcon);

    }

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    @FXML
    protected void onLoginButton() {
        if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")){
            mainController.showDashboard();
        }
    }
}
