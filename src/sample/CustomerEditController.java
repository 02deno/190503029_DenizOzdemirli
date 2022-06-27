package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerEditController implements Initializable {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField licenseNumberField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField districtField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField homeNumberField;
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


    private static Customer customer;
    private Stage dialogStage;



    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;

    }


    public void ok(ActionEvent event)  {
        DatabaseConnection connectNow = new DatabaseConnection();
        if (isInputValid()) {
            customer.setName(firstNameField.getText());
            customer.setSurname(lastNameField.getText());
            customer.setDrivingLicenseNumber(licenseNumberField.getText());

            customer.getAddress().setCountry(countryField.getText());
            customer.getAddress().setDistrict(districtField.getText());
            customer.getAddress().setStreet(streetField.getText());
            customer.getAddress().setZipCode(zipCodeField.getText());
            customer.getAddress().setHomeNumber(homeNumberField.getText());

            customer.setEmail(emailField.getText());
            customer.setPhoneNumber1(phoneNumber1Field.getText());
            customer.setPhoneNumber2(phoneNumber2Field.getText());

            customer.getPaymentInformation().setCardType(cardTypeField.getText());
            customer.getPaymentInformation().setCardNumber(cardNumberField.getText());
            customer.getPaymentInformation().setExpiryDate(expiryDateField.getText());
            customer.getPaymentInformation().setCardCode(Integer.parseInt(cardCodeField.getText()));

            connectNow.editCustomer(customer);


            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();

        }

    }

    private boolean isInputValid() {

        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (licenseNumberField.getText() == null || licenseNumberField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (countryField.getText() == null || countryField.getText().length() == 0) {
            errorMessage += "No valid country!\n";
        }
        if (districtField.getText() == null || districtField.getText().length() == 0) {
            errorMessage += "No valid district!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }
        if (zipCodeField.getText() == null || zipCodeField.getText().length() == 0 || zipCodeField.getText().length() != 5) {
            //uzunluğu 5 mi
            errorMessage += "No valid zipcode!\n";
        }
        if (homeNumberField.getText() == null || homeNumberField.getText().length() == 0 ) {

            errorMessage += "No valid homenumber!\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid email!\n";
        }
        if (phoneNumber1Field.getText() == null || phoneNumber1Field.getText().length() == 0 || phoneNumber1Field.getText().length() != 11) {
            //uzunluğu 11
            //0532-123-45-67
            errorMessage += "No valid phonenumber1!\n";
        }
        if (phoneNumber2Field.getText() == null || phoneNumber2Field.getText().length() == 0  || phoneNumber2Field.getText().length() != 11) {
            errorMessage += "No valid phonenumber2!\n";
        }
        if (cardTypeField.getText() == null || cardTypeField.getText().length() == 0) {
            errorMessage += "No valid cardtype!\n";
        }
        if (cardNumberField.getText() == null || cardNumberField.getText().length() == 0) {
            //uzunlğuğu 16
            //sonra bak
            errorMessage += "No valid card number!\n";
        }
        if (expiryDateField.getText() == null || expiryDateField.getText().length() == 0) {
            //format 2002-05-04 sonra ayarla
            errorMessage += "No valid expiry date!\n";
        }
        if (cardCodeField.getText() == null || cardCodeField.getText().length() == 0 || cardCodeField.getText().length() != 3) {
            //uzunluğu 3
            errorMessage += "No valid card code!\n";
        }



        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        customersController customersController = new customersController();
        customersController.refreshTable();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        firstNameField.setText(customer.getName());
        lastNameField.setText(customer.getSurname());
        licenseNumberField.setText(customer.getDrivingLicenseNumber());
        countryField.setText(customer.getAddress().getCountry());
        districtField.setText(customer.getAddress().getDistrict());
        streetField.setText(customer.getAddress().getStreet());
        zipCodeField.setText(customer.getAddress().getZipCode());
        homeNumberField.setText(customer.getAddress().getHomeNumber());
        emailField.setText(customer.getEmail());
        phoneNumber1Field.setText(customer.getPhoneNumber1());
        phoneNumber2Field.setText(customer.getPhoneNumber2());

        cardTypeField.setText(customer.getPaymentInformation().getCardType());
        cardNumberField.setText(customer.getPaymentInformation().getCardNumber());
        //DatePicker ekle yerine
        expiryDateField.setText(""+customer.getPaymentInformation().getExpiryDate());
        expiryDateField.setPromptText("yyyy-mm-dd");
        cardCodeField.setText(""+customer.getPaymentInformation().getCardCode());




    }
}
