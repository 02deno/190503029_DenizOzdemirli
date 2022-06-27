package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.ResourceBundle;



public class LoginController implements Initializable {

    @FXML
    private Tooltip tooltip =new Tooltip();

    @FXML
    private CheckBox showPassword;

    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField passwordTextfield;

    public static String username;
    public static String password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //File brandingFile = new File();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void login(ActionEvent event) throws IOException {

        if(usernameTextfield.getText().isBlank() == false && passwordTextfield.getText().isBlank() == false ) {

            validateLogin();

        }else{
            loginMessageLabel.setText("Please enter username and password");


        }
    }

    public void validateLogin() throws IOException {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();

        boolean result = connectNow.validateLogin(usernameTextfield.getText(),passwordTextfield.getText());

        if(result == true) {
            username = usernameTextfield.getText();
            password = passwordTextfield.getText();
            Main m = new Main();
            m.changeScene("homepage.fxml");
        }else {
            loginMessageLabel.setText("Invalid login.Please try again.");
        }

    }

    public void showPassword() {
        Point2D p = passwordTextfield.localToScene(passwordTextfield.getBoundsInLocal().getMaxX(), passwordTextfield.getBoundsInLocal().getMaxY());
        if(showPassword.isSelected() && passwordTextfield.getText() != "") {

            tooltip.setText(passwordTextfield.getText());
            tooltip.show(passwordTextfield, p.getX() + 295 , p.getY()  + 135 );
        }else {
            tooltip.hide();
        }
    }


}
