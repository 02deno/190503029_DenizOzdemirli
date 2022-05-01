package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.Button;

public class CustomerAddController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField districtField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumber1Field;
    @FXML
    private TextField phoneNumber2Field;
    @FXML
    private TextField cardTypeField;
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField expiryDateField;
    @FXML
    private TextField cardCodeField;

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;


    private Stage dialogStage;
    private Customer customer;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }



    private void cancel() {
        dialogStage.close();
    }
}
