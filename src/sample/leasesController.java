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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import javafx.print.PrinterJob;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class leasesController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Lease> tableView = new TableView<>();

    @FXML
    private TableColumn<Lease,String> idCol;
    @FXML
    private TableColumn<Customer,String>  customerCol;
    @FXML
    private TableColumn<Car,String>  carCol;


    @FXML
    private Label idLabel;
    @FXML
    private Label customerLabel;
    @FXML
    private Label carLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label closedLabel;
    @FXML
    private Label employeeLabel;
    @FXML
    private Label insuranceLabel;
    @FXML
    private Label startendKmLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private Label priceProKmLabel;


    @FXML
    private TextField filterTextField;

    double bHeight = 0.0;


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

    public void switchToCars(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("cars.fxml"));
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

    public void switchToLogout(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("logout.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildData();
        showLeaseDetails(null);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> showLeaseDetails(newValue) );
    }

    // tableview'deki column'ları
    //SQL ile veri çekerek doldurma fonskiyonu
    public void buildData() {

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        customerCol.setCellValueFactory(new PropertyValueFactory("customer"));
        carCol.setCellValueFactory(new PropertyValueFactory("rentedCar"));

        DatabaseConnection connectNow = new DatabaseConnection();
        tableView.setItems(connectNow.createAllLeases());


    }

    public void refreshTable() {

        DatabaseConnection connectNow = new DatabaseConnection();
        tableView.getItems().clear();
        tableView.setItems(connectNow.createAllLeases());
    }

    public void showLeaseDetails(Lease lease) {

        if(lease != null ) {
            idLabel.setText(""+lease.getId());
            customerLabel.setText(lease.getCustomer().getName() + " " + lease.getCustomer().getSurname() );
            carLabel.setText(lease.getRentedCar().getName()+"\n"+lease.getRentedCar().getLicenseNumber());
            startDateLabel.setText(lease.getStartDate());
            endDateLabel.setText(lease.getEndDate());
            priceLabel.setText(""+lease.getPrice());
            if(lease.isClosed() == true) {
                closedLabel.setText("Yes");
            }else {
                closedLabel.setText("No");
            }

            employeeLabel.setText(lease.getSupervisingEmployee().getName() + " "+ lease.getSupervisingEmployee().getSurname());
            startendKmLabel.setText(""+lease.getStartKm()+"-"+lease.getEndKm());
            durationLabel.setText(""+lease.getDuration());
            insuranceLabel.setText(""+lease.getInsuranceCosts());
            priceProKmLabel.setText(""+lease.getPriceProDay());

        }else {
            idLabel.setText("");
            customerLabel.setText("");
            carLabel.setText("");
            startDateLabel.setText("");
            endDateLabel.setText("");
            priceLabel.setText("");
            closedLabel.setText("");
            employeeLabel.setText("");
            startendKmLabel.setText("");
            durationLabel.setText("");
            insuranceLabel.setText("");
            priceProKmLabel.setText("");


        }

    }

    public void switchtoEditDialog(ActionEvent event) throws IOException {

        DatabaseConnection connectNow = new DatabaseConnection();


        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Lease selectedItem = tableView.getSelectionModel().getSelectedItem();



        if (selectedIndex >= 0) {
            LeaseEditController leaseEditController = new LeaseEditController();
            //System.out.println(selectedItem);
            leaseEditController.setLease(selectedItem);

            try {
                Parent parent = FXMLLoader.load(getClass().getResource("LeaseEditDialog.fxml"));
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
            alert.setHeaderText("No Lease Selected");
            alert.setContentText("Please select a lease in the table.");
            alert.showAndWait();
        }


    }

    public void switchtoAddDialog(ActionEvent event) throws IOException {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("LeaseAddDialog.fxml"));
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



    public void deleteLease() {

        //silince customer'ın address ve payment information
        //ile de bağlantısı kesiliyo
        //tekrardan eklerken onlarla bağlantısını
        //da tekrardan ayarlamak gerekiyo yoksa hata veriyo


        DatabaseConnection connectNow = new DatabaseConnection();


        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Lease selectedItem = tableView.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0) {
            tableView.getItems().remove(selectedIndex);
            connectNow.deleteLease(selectedItem);

        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Lease Selected");
            alert.setContentText("Please select a lease in the table.");
            alert.showAndWait();
        }
        refreshTable();

    }

    public void filterTable() {
        String text = filterTextField.getText();
        // If filter text is empty, display all persons.
        DatabaseConnection connectNow = new DatabaseConnection();
        ObservableList<Lease> leaseList = connectNow.createAllLeases();
        ObservableList<Lease> filteredLeaseList ;
        filteredLeaseList= FXCollections.observableArrayList();
        if(text == null) {
            //do nothing
        }else {
            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = text.toLowerCase();
            for(int i = 0;i<leaseList.size();i++) {
                Lease lease = leaseList.get(i);
                if (lease.getCustomer().getName().toLowerCase().contains(lowerCaseFilter)) {
                    // Filter matches first name.
                    filteredLeaseList.add(lease);
                } else if (lease.getCustomer().getSurname().toLowerCase().contains(lowerCaseFilter)) {
                    // Filter matches last name.
                    filteredLeaseList.add(lease);
                }else if (lease.getRentedCar().getName().toLowerCase().contains(lowerCaseFilter)) {
                    // Filter matches last name.
                    filteredLeaseList.add(lease);
                }else if (lease.getRentedCar().getLicenseNumber().toLowerCase().contains(lowerCaseFilter)) {
                    // Filter matches last name.
                    filteredLeaseList.add(lease);
                }
            }

            tableView.setItems(filteredLeaseList);
        }

        /*
        for(int i = 0;i<filteredLeaseList.size();i++) {
            System.out.println(filteredLeaseList.get(i));
        }

         */

    }

    public void print () {


        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Lease lease = tableView.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0) {
            //Node node = new Circle(100, 200, 200);
            String str = "----------------------------------------------------------------------------------------\n"+
                    "                                  Auto Rental\n" +
                    "                           www.autorental.com\n" +
                    "                                +94700000000\n" +
                    "----------------------------------------------------------------------------------------\n" +
                    "                               Lease Contract\n\n" +
                    "This Agreement will begin on " + lease.getStartDate()+" and remain \n" +
                    "in full force and effect until the Vehicle is returned to\n" +
                    "the Owner. It is agreed that the Renter will return\n" +
                    "the Vehicle on " +lease.getEndDate()+" unless the Agreement is\n" +
                    "terminated earlier.\n\n" +
                    "Customer : \n"+lease.getCustomer().toString2() + "\n" +
                    "Employee : \n"+lease.getSupervisingEmployee() + "\n" +
                    "Car : \n"+lease.getRentedCar().toString3() + "\n\n" +
                    "                                                          Total amount: "+lease.getPrice()+" €\n" +
                    "                                                          Date : \n"+
                    "                                                          Sign : \n" ;

            ;

            Node node1 = new Text(10, 50, str);
            Text text = new Text();
            text.setFont(new Font(20));
            text.setText(str);
            Node node2 = text;

            Text text1 = new Text();
            text1.setFont(new Font(40));
            text1.setText("\n\n\n\n\n             Güzel oldu?");
            Node node3 = text1;

            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null) {
                boolean success = job.printPage(node2);
                //job.printPage(node3);
                if (success) {
                    job.endJob();
                }
            }

        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Lease Selected");
            alert.setContentText("Please select a lease in the table.");
            alert.showAndWait();
        }

    }
}