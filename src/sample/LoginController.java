package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField passwordTextfield;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //File brandingFile = new File();
    }

    public void login(ActionEvent event) throws IOException {

        if(usernameTextfield.getText().isBlank() == false && passwordTextfield.getText().isBlank() == false ) {
            validateLogin();

        }else{
            loginMessageLabel.setText("Please enter username and password");


        }
    }

    public void validateLogin() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username =  '" + usernameTextfield.getText() + "' AND password = '" + passwordTextfield.getText() + "'" ;

        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if(queryResult.getInt(1)==1) {
                    Main m = new Main();
                    m.changeScene("homepage.fxml");


                }else {
                    loginMessageLabel.setText("Invalid login.Please try again.");
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


}
