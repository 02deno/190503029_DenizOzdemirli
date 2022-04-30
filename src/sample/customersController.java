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
import javafx.stage.Stage;

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
        //addressCol.setCellValueFactory(new PropertyValueFactory("address"));
        //emailCol.setCellValueFactory(new PropertyValueFactory("address"));
        //phoneNumber1Col.setCellValueFactory(new PropertyValueFactory("address"));

        customerList = FXCollections.observableArrayList();
        addressList = FXCollections.observableArrayList();
        paymentInfoList = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();


        String tableSQL3 = "SELECT * FROM payment_information";

        try {
            Statement statement2 = connectDb.createStatement();
            ResultSet queryResult2 = statement2.executeQuery(tableSQL3);
            while(queryResult2.next()) {
                PaymentInformation paymentInformation = new PaymentInformation(
                        queryResult2.getInt("payment_information_id"),
                        queryResult2.getString("card_type"),
                        queryResult2.getString("card_number"),
                        queryResult2.getDate("expiry_date"),
                        queryResult2.getInt("car_code")
                        //card_code olması lazım databank da create table yaparken column adını car_code
                        //diye yanlış girdin şimdilik bu isimle devam et sonra düzeltirsin.

                );

                paymentInfoList.add(paymentInformation);

            }
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }




        String tableSQL2 = "SELECT * FROM person_address";

        try {
            Statement statement2 = connectDb.createStatement();
            ResultSet queryResult2 = statement2.executeQuery(tableSQL2);
            while(queryResult2.next()) {
                PersonAddress address = new PersonAddress(
                        queryResult2.getInt("address_id"),
                        queryResult2.getString("country"),
                        queryResult2.getString("district"),
                        queryResult2.getString("home_number"),
                        queryResult2.getString("street"),
                        queryResult2.getString("zip_code")

                );
                addressList.add(address);

            }
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        String tableSQL = "SELECT * FROM customer ";

        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(tableSQL);

            while(queryResult.next()) {
                Customer customer = new Customer(
                        queryResult.getInt("customer_id"),
                        queryResult.getString("first_name"),
                        queryResult.getString("last_name")

                );
                customerList.add(customer);
                tableView.setItems(customerList);

                for(int i = 0; i<addressList.size(); i++)
                {
                    PersonAddress personAddress= (PersonAddress) addressList.get(i);
                    if(personAddress.getId() == queryResult.getInt("address_id")) {
                        customer.setAddress(personAddress);
                    }
                }

                customer.setEmail(queryResult.getString("email"));
                customer.setPhoneNumber1(queryResult.getString("phone_number_1"));
                customer.setPhoneNumber2(queryResult.getString("phone_number_2"));

                for(int i = 0; i<paymentInfoList.size(); i++)
                {
                    PaymentInformation paymentInformation= (PaymentInformation) paymentInfoList.get(i);
                    if(paymentInformation.getId() == queryResult.getInt("payment_information_id")) {
                        customer.setPaymentInformation(paymentInformation);
                    }
                }

            }

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void refreshTable() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();

        String tableSQL3 = "SELECT * FROM payment_information";

        try {
            Statement statement2 = connectDb.createStatement();
            ResultSet queryResult2 = statement2.executeQuery(tableSQL3);
            while(queryResult2.next()) {
                PaymentInformation paymentInformation = new PaymentInformation(
                        queryResult2.getInt("payment_information_id"),
                        queryResult2.getString("card_type"),
                        queryResult2.getString("card_number"),
                        queryResult2.getDate("expiry_date"),
                        queryResult2.getInt("car_code")
                        //card_code olması lazım databank da create table yaparken column adını car_code
                        //diye yanlış girdin şimdilik bu isimle devam et sonra düzeltirsin.

                );

                paymentInfoList.add(paymentInformation);

            }
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        String tableSQL2 = "SELECT * FROM person_address";

        try {
            Statement statement2 = connectDb.createStatement();
            ResultSet queryResult2 = statement2.executeQuery(tableSQL2);
            while(queryResult2.next()) {
                PersonAddress address = new PersonAddress(
                        queryResult2.getInt("address_id"),
                        queryResult2.getString("country"),
                        queryResult2.getString("district"),
                        queryResult2.getString("home_number"),
                        queryResult2.getString("street"),
                        queryResult2.getString("zip_code")

                );
                addressList.add(address);

            }
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }




        String tableSQL = "SELECT * FROM customer ";
        try {
            customerList.clear();

            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(tableSQL);

            while(queryResult.next()) {
                Customer customer = new Customer(

                        queryResult.getInt("customer_id"),
                        queryResult.getString("first_name"),
                        queryResult.getString("last_name")

                );
                customerList.add(customer);
                tableView.setItems(customerList);
                for(int i = 0; i<addressList.size(); i++)
                {
                    PersonAddress personAddress= (PersonAddress) addressList.get(i);
                    if(personAddress.getId() == queryResult.getInt("address_id")) {
                        customer.setAddress(personAddress);
                    }
                }
                customer.setEmail(queryResult.getString("email"));
                customer.setPhoneNumber1(queryResult.getString("phone_number_1"));
                customer.setPhoneNumber2(queryResult.getString("phone_number_2"));

                for(int i = 0; i<paymentInfoList.size(); i++)
                {
                    PaymentInformation paymentInformation= (PaymentInformation) paymentInfoList.get(i);
                    if(paymentInformation.getId() == queryResult.getInt("payment_information_id")) {
                        customer.setPaymentInformation(paymentInformation);
                    }
                }

            }

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void showPersonDetails(Customer customer) {

        if(customer != null ) {
            idLabel.setText(""+customer.getId());
            firstNameLabel.setText(customer.getName());
            lastNameLabel.setText(customer.getSurname());
            if(customer.getAddress() == null ) {
                addressLabel.setText("kimde hata ortaya çık");
            }else {
                addressLabel.setText(customer.getAddress().toString());
            }

            emailLabel.setText(customer.getEmail());
            phoneNumber1Label.setText(customer.getPhoneNumber1());
            phoneNumber2Label.setText(customer.getPhoneNumber2());

            if(customer.getPaymentInformation() == null) {
                paymentInfoLabel.setText("kimde hata ortaya çık");
            }else {
                paymentInfoLabel.setText(customer.getPaymentInformation().toString());
            }


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

    public void addCustomer() {

    }

    public void editCustomer() {

    }

    public void deleteCustomer() {

        //silince customer'ın address ve payment information
        //ile de bağlantısı kesiliyo
        //tekrardan eklerken onlarla bağlantısını
        //da tekrardan ayarlamak gerekiyo yoksa hata veriyo


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Customer selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            tableView.getItems().remove(selectedIndex);
            String tableSQL = "DELETE FROM customer WHERE customer_id = " + selectedItem.getId();

            try {
                Statement statement = connectDb.createStatement();
                statement.execute(tableSQL);

            }catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");


            alert.showAndWait();
        }

    }




}