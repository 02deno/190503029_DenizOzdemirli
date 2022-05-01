package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomerEditController {

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

    public void setPerson(Customer customer) {
        this.customer = customer;

        firstNameField.setText(customer.getName());
        lastNameField.setText(customer.getSurname());
        countryField.setText(customer.getAddress().getCountry());
        districtField.setText(customer.getAddress().getDistrict());
        streetField.setText(customer.getAddress().getStreet());
        zipCodeField.setText(customer.getAddress().getZipCode());
        emailField.setText(customer.getEmail());
        phoneNumber1Field.setText(customer.getPhoneNumber1());
        phoneNumber2Field.setText(customer.getPhoneNumber2());

        //kart tipini seçtirebilirsin altına mastercard vize gibi
        //seçenekler koyarak
        //en son optimizasyon kısmında bak
        cardTypeField.setText(customer.getPaymentInformation().getCardType());
        cardNumberField.setText(customer.getPaymentInformation().getCardNumber());

        //DatePicker ekle yerine
        expiryDateField.setText(""+customer.getPaymentInformation().getExpiryDate());
        expiryDateField.setPromptText("yyyy-mm-dd");
    }


    public void ok() throws ParseException {

        if (isInputValid()) {
            customer.setName(firstNameField.getText());
            customer.setSurname(lastNameField.getText());

            customer.getAddress().setCountry(countryField.getText());
            customer.getAddress().setDistrict(districtField.getText());
            customer.getAddress().setStreet(streetField.getText());
            customer.getAddress().setZipCode(zipCodeField.getText());

            customer.setEmail(emailField.getText());
            customer.setPhoneNumber1(phoneNumber1Field.getText());
            customer.setPhoneNumber2(phoneNumber2Field.getText());

            customer.getPaymentInformation().setCardType(cardTypeField.getText());
            customer.getPaymentInformation().setCardNumber(cardNumberField.getText());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            customer.getPaymentInformation().setExpiryDate(formatter.parse(expiryDateField.getText()));
            customer.getPaymentInformation().setCardCode(Integer.parseInt(cardCodeField.getText()));


            okClicked = true;
            dialogStage.close();
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
        if (countryField.getText() == null || countryField.getText().length() == 0) {
            errorMessage += "No valid country!\n";
        }
        if (districtField.getText() == null || districtField.getText().length() == 0) {
            errorMessage += "No valid district!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }
        if (zipCodeField.getText() == null || zipCodeField.getText().length() == 0) {
            errorMessage += "No valid zipcode!\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid email!\n";
        }
        if (phoneNumber1Field.getText() == null || phoneNumber1Field.getText().length() == 0) {
            errorMessage += "No valid phonenumber1!\n";
        }
        if (phoneNumber2Field.getText() == null || phoneNumber2Field.getText().length() == 0) {
            errorMessage += "No valid phonenumber2!\n";
        }
        if (cardTypeField.getText() == null || cardTypeField.getText().length() == 0) {
            errorMessage += "No valid cardtype!\n";
        }
        if (cardNumberField.getText() == null || cardNumberField.getText().length() == 0) {
            errorMessage += "No valid card number!\n";
        }
        if (expiryDateField.getText() == null || expiryDateField.getText().length() == 0) {
            errorMessage += "No valid expiry date!\n";
        }
        if (cardCodeField.getText() == null || cardCodeField.getText().length() == 0) {
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

    public void cancel() {
        dialogStage.close();
    }


}
