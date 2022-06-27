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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class employeesController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Employee> tableView = new TableView<>();

    @FXML
    private TableColumn<Employee,String> idCol;
    @FXML
    private TableColumn<Employee,String>  firstNameCol;
    @FXML
    private TableColumn<Employee,String>  lastNameCol;


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
    private TextField filterTextField;


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

    public void switchToCars(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("cars.fxml"));
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

    public void switchToLogout(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("logout.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void buildData() {

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory("name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory("surname"));

        DatabaseConnection connectNow = new DatabaseConnection();
        tableView.setItems(connectNow.createAllEmployees());


    }

    public void refreshTable() {

        DatabaseConnection connectNow = new DatabaseConnection();
        tableView.getItems().clear();
        tableView.setItems(connectNow.createAllEmployees());
    }

    public void showPersonDetails(Employee employee) {

        if(employee != null ) {
            idLabel.setText(""+employee.getId());
            firstNameLabel.setText(employee.getName());
            lastNameLabel.setText(employee.getSurname());
            emailLabel.setText(employee.getEmail());
            phoneNumber1Label.setText(employee.getPhoneNumber1());
            addressLabel.setText(employee.getAddress().toString());

        }else {
            idLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            addressLabel.setText("");
            emailLabel.setText("");
            phoneNumber1Label.setText("");



        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildData();
        showPersonDetails(null);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> showPersonDetails(newValue) );
    }

    public void switchtoEditDialog(ActionEvent event) throws IOException {


        DatabaseConnection connectNow = new DatabaseConnection();
        LoginController loginController = new LoginController();
        String username = loginController.getUsername();
        if(!connectNow.getRolle(username).equals("manager")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Not the Manager");
            alert.setHeaderText("You do not have the permission to do this function.");
            alert.setContentText("Please stop trying!");
            alert.showAndWait();
        }else {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            Employee selectedItem = tableView.getSelectionModel().getSelectedItem();



            if (selectedIndex >= 0) {
                EmployeeEditController employeeEditController = new EmployeeEditController();
                //System.out.println(selectedItem);
                employeeEditController.setEmployee(selectedItem);

                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("EmployeeEditDialog.fxml"));
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
                alert.setHeaderText("No Employee Selected");
                alert.setContentText("Please select a employee in the table.");
                alert.showAndWait();
            }

        }





    }

    public void switchtoAddDialog(ActionEvent event) throws IOException {

        DatabaseConnection connectNow = new DatabaseConnection();
        LoginController loginController = new LoginController();
        String username = loginController.getUsername();
        if(!connectNow.getRolle(username).equals("manager")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Not the Manager");
            alert.setHeaderText("You do not have the permission to do this function.");
            alert.setContentText("Please stop trying!");
            alert.showAndWait();
        }else {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("EmployeeAddDialog.fxml"));
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




    }

    public void deleteEmployee() {

        //silince customer'ın address ve payment information
        //ile de bağlantısı kesiliyo
        //tekrardan eklerken onlarla bağlantısını
        //da tekrardan ayarlamak gerekiyo yoksa hata veriyo

        DatabaseConnection connectNow = new DatabaseConnection();


        LoginController loginController = new LoginController();
        String username = loginController.getUsername();
        //System.out.println(connectNow.getRolle(username));

        if(!connectNow.getRolle(username).equals("manager")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Not the Manager");
            alert.setHeaderText("You do not have the permission to do this function.");
            alert.setContentText("Please stop trying!");
            alert.showAndWait();
        }else {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            Employee selectedItem = tableView.getSelectionModel().getSelectedItem();

            if (selectedIndex >= 0) {
                tableView.getItems().remove(selectedIndex);
                connectNow.deleteEmployee(selectedItem);

            }else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText("No Employee Selected");
                alert.setContentText("Please select a employee in the table.");
                alert.showAndWait();
            }
            refreshTable();
        }







    }

    public void filterTable() {

        String text = filterTextField.getText();
        // If filter text is empty, display all persons.
        DatabaseConnection connectNow = new DatabaseConnection();
        ObservableList<Employee> employeeList = connectNow.createAllEmployees();
        ObservableList<Employee> filteredEmployeeList ;
        filteredEmployeeList= FXCollections.observableArrayList();
        if(text == null) {
            //do nothing
        }else {
            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = text.toLowerCase();
            for(int i = 0;i<employeeList.size();i++) {
                Employee employee = employeeList.get(i);
                if (employee.getName().toLowerCase().contains(lowerCaseFilter)) {
                    // Filter matches first name.
                    filteredEmployeeList.add(employee);
                } else if (employee.getSurname().toLowerCase().contains(lowerCaseFilter)) {
                    // Filter matches last name.
                    filteredEmployeeList.add(employee);
                }
            }

            tableView.setItems(filteredEmployeeList);
        }

        /*
        for(int i = 0;i<filteredEmployeeList.size();i++) {
            System.out.println(filteredEmployeeList.get(i));
        }

         */



    }

}