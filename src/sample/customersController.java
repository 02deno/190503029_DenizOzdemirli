package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class customersController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Customer> tableView = new TableView<>();

    @FXML
    private TableColumn<Customer,String> idCol;
    @FXML
    private TableColumn<Customer,String>  firstNameCol;
    @FXML
    private TableColumn<Customer,String>  lastNameCol;

    private ObservableList<Customer> customerList;
    private ObservableList<PersonAddress> addressList;
    private ObservableList<PaymentInformation> paymentInfoList;


    @FXML
    private Label idLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneNumber1Label;
    @FXML
    private Label phoneNumber2Label;
    @FXML
    private Label paymentInfoLabel;

    @FXML
    private Button refreshButton;

    private AutoRentalSystem ars;





    public void switchToHomepages(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
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

    public void switchToSettings(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buildData();
        showPersonDetails(null);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> showPersonDetails(newValue) );

    }

    // tableview'deki column'ları
    //SQL ile veri çekerek doldurma fonskiyonu
    public void buildData() {

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory("name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory("surname"));

        DatabaseConnection connectNow = new DatabaseConnection();
        tableView.setItems(connectNow.createAllCustomers());


    }

    public void refreshTable() {
        DatabaseConnection connectNow = new DatabaseConnection();
        tableView.setItems(connectNow.createAllCustomers());
    }

    public void showPersonDetails(Customer customer) {

        if(customer != null ) {
            idLabel.setText(""+customer.getId());
            firstNameLabel.setText(customer.getName());
            lastNameLabel.setText(customer.getSurname());
            addressLabel.setText(customer.getAddress().toString());
            emailLabel.setText(customer.getEmail());
            phoneNumber1Label.setText(customer.getPhoneNumber1());
            phoneNumber2Label.setText(customer.getPhoneNumber2());
            paymentInfoLabel.setText(customer.getPaymentInformation().toString());

        }else {
            idLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            addressLabel.setText("");
            emailLabel.setText("");
            phoneNumber1Label.setText("");
            phoneNumber2Label.setText("");
            paymentInfoLabel.setText("");


        }

    }


    public void switchtoEditDialog(ActionEvent event) throws IOException {

        DatabaseConnection connectNow = new DatabaseConnection();


        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Customer selectedItem = tableView.getSelectionModel().getSelectedItem();



        if (selectedIndex >= 0) {
            CustomerEditController customerEditController = new CustomerEditController();
            //System.out.println(selectedItem);
            customerEditController.setCustomer(selectedItem);

            try {
                Parent parent = FXMLLoader.load(getClass().getResource("customerEditDialog.fxml"));
                Scene scene  = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();

            }catch (IOException ioException) {
                ioException.printStackTrace();
                ioException.getCause();
            }



        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");
            alert.showAndWait();
        }


    }

    public void switchtoAddDialog(ActionEvent event) throws IOException {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("customerAddDialog.fxml"));
            Scene scene  = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        }catch(IOException ioException) {
            ioException.printStackTrace();
            ioException.getCause();
        }


    }

    public void addCustomer() {

    }


    public void editCustomer() {

        DatabaseConnection connectNow = new DatabaseConnection();


        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Customer selectedItem = tableView.getSelectionModel().getSelectedItem();



        if (selectedIndex >= 0) {
            CustomerEditController customerEditController = new CustomerEditController();
            customerEditController.setCustomer(selectedItem);

        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");
            alert.showAndWait();
        }


    }

    public void deleteCustomer() {

        //silince customer'ın address ve payment information
        //ile de bağlantısı kesiliyo
        //tekrardan eklerken onlarla bağlantısını
        //da tekrardan ayarlamak gerekiyo yoksa hata veriyo


        DatabaseConnection connectNow = new DatabaseConnection();


        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Customer selectedItem = tableView.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0) {
            tableView.getItems().remove(selectedIndex);
            connectNow.deleteCustomer(selectedItem);

        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");
            alert.showAndWait();
        }

    }




}