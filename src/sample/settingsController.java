package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class settingsController implements Initializable {



    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Employee employee;


    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }



    public void switchToHomepages(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToCustomers(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("customers.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToLeases(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("leases.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToEmployees(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("employees.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToCars(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("cars.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToLogout(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("logout.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void changePassword() {


        //login yaptığı kullanıcı adını ve şifreyi tut.
        //check old password databaseden çek
        //yeni şifreyle confirm şifre birbirne eşit mi diye bak
        //eğer bunların hepsi true ise
        //update ile çalışanın şifresini değiştir

        LoginController loginController = new LoginController();

        System.out.println(loginController.getUsername());
        System.out.println(loginController.getPassword());

        String errorMessage = "";


        DatabaseConnection connectNow = new DatabaseConnection();

        System.out.println("------");

        System.out.println(currentPassword.getText());
        System.out.println(newPassword.getText());
        System.out.println(confirmPassword.getText());

        System.out.println("------");

        boolean checkOldPassword = loginController.getPassword().equals(currentPassword.getText());
        boolean samePassword = newPassword.getText().equals(confirmPassword.getText());


        if (currentPassword.getText() == null || currentPassword.getText().length() == 0 ) {
            errorMessage += "No valid old Password!\n";
        }
        if (newPassword.getText() == null || newPassword.getText().length() == 0) {
            errorMessage += "No valid new Password!\n";
        }
        if (confirmPassword.getText() == null || confirmPassword.getText().length() == 0 ) {
            errorMessage += "No valid confirm Password!\n";
        }

        if (errorMessage.length() != 0) {

            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
        }
        else if(checkOldPassword == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Old password");
            alert.setHeaderText("Old password is incorrect");
            alert.setContentText(errorMessage);

            alert.showAndWait();
        }else if(samePassword == false)  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("New Password");
            alert.setHeaderText("Passwords do not match.");
            alert.setContentText(errorMessage);

            alert.showAndWait();
        }

        if(checkOldPassword == true && samePassword == true && currentPassword.getText().length() != 0 && confirmPassword.getText().length() != 0 ) {
            //System.out.print("değiştir\n");
            connectNow.changePassword(loginController.getUsername(),newPassword.getText());
            //employee.setPassword(newPassword.getText());
        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}