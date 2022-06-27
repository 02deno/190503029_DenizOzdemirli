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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class homepageController implements Initializable {

    @FXML
    private TreeView treeView;

    @FXML
    private Text label;

    private String currentOption;

    @FXML
    private Label allCars;

    @FXML
    private Label availableCars;

    @FXML
    private BarChart<?,?> grafik;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void initialize(URL url, ResourceBundle resourceBundle) {


        DatabaseConnection connectNow = new DatabaseConnection();
        ObservableList<Car> carList = connectNow.createAllCars();

        allCars.setText(""+carList.size());
        availableCars.setText(""+connectNow.isAvailable());

        TreeItem<String> rootItem = new TreeItem<>("Options") ;


        TreeItem<String> branchItem1 = new TreeItem<>("Customer") ;
        TreeItem<String> branchItem2 = new TreeItem<>("Car") ;
        TreeItem<String> branchItem3 = new TreeItem<>("Lease") ;
        TreeItem<String> branchItem4 = new TreeItem<>("Employee") ;
        TreeItem<String> branchItem5 = new TreeItem<>("Settings") ;
        TreeItem<String> branchItem6 = new TreeItem<>("Logout") ;


        TreeItem<String> leafItem1 = new TreeItem<>("Add a customer") ;
        TreeItem<String> leafItem2 = new TreeItem<>("Delete a customer") ;
        TreeItem<String> leafItem3 = new TreeItem<>("Modify a customer") ;
        TreeItem<String> leafItem4 = new TreeItem<>("Add a car") ;
        TreeItem<String> leafItem5 = new TreeItem<>("Delete a car") ;
        TreeItem<String> leafItem6 = new TreeItem<>("Modify a car") ;
        TreeItem<String> leafItem7 = new TreeItem<>("Add a lease") ;
        TreeItem<String> leafItem8 = new TreeItem<>("Delete a lease") ;
        TreeItem<String> leafItem9 = new TreeItem<>("Modify a lease") ;
        TreeItem<String> leafItem18 = new TreeItem<>("Print a lease") ;
        TreeItem<String> leafItem10 = new TreeItem<>("Add an employee") ;
        TreeItem<String> leafItem11 = new TreeItem<>("Delete an employee") ;
        TreeItem<String> leafItem12 = new TreeItem<>("Modify an employee") ;

        TreeItem<String> leafItem13 = new TreeItem<>("Search a customer") ;
        TreeItem<String> leafItem14 = new TreeItem<>("Search a car") ;
        TreeItem<String> leafItem15 = new TreeItem<>("Search a lease") ;
        TreeItem<String> leafItem16 = new TreeItem<>("Search an employee") ;

        TreeItem<String> leafItem17 = new TreeItem<>("Change Password") ;

        treeView.setRoot(rootItem);
        rootItem.getChildren().addAll(branchItem1,branchItem2,branchItem3,branchItem4,branchItem5,branchItem6);

        branchItem1.getChildren().addAll(leafItem1,leafItem2,leafItem3,leafItem13);
        branchItem2.getChildren().addAll(leafItem4,leafItem5,leafItem6,leafItem14);
        branchItem3.getChildren().addAll(leafItem7,leafItem8,leafItem9,leafItem15,leafItem18);
        branchItem4.getChildren().addAll(leafItem10,leafItem11,leafItem12,leafItem16);
        branchItem5.getChildren().addAll(leafItem17);



        int x1 = 0;
        int x2 = 0;
        int x3 = 0;
        int x4 = 0;
        int x5 = 0;
        int x6 = 0;


        for(Car car : carList) {
            double price = car.getPriceProDay();
            if(price>0 &&  price < 2) {
                x1++;
            }else if(price>1.99 &&  price < 4) {
                x2++;
            }else if(price>3.99 &&  price < 6) {
                x3++;
            }else if(price>5.99 &&  price < 8) {
                x4++;
            }else if(price>7.99 &&  price < 10) {
                x5++;
            }else if(price>9.99) {
                x6++;
            }
        }

        /*
        System.out.println(x1);
        System.out.println(x2);
        System.out.println(x3);
        System.out.println(x4);
        System.out.println(x5);
        System.out.println(x6);


         */

        //Defining the x axis
        xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "0-1.99", "2-3.99", "4-5.99", "6-7.99","8-9.99","10+")));
        xAxis.setLabel("Rent Prices");

//Defining the y axis.
        yAxis = new NumberAxis();
        yAxis.setLabel("Number");

        //grafik.setTitle("Comparison between Car Rental Prices");

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.getData().add(new XYChart.Data("0-1.99", x1));
        dataSeries1.getData().add(new XYChart.Data("2-3.99", x2));
        dataSeries1.getData().add(new XYChart.Data("4-5.99", x3));
        dataSeries1.getData().add(new XYChart.Data("6-7.99", x4));
        dataSeries1.getData().add(new XYChart.Data("8-9.99", x5));
        dataSeries1.getData().add(new XYChart.Data("10+", x6));

        grafik.getData().add(dataSeries1);




    }

    public void getOption() {

        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();

        if(item != null) {
            //System.out.println(item.getValue());
            currentOption = item.getValue();

            if(currentOption.equals("Add a customer") ) {
                label.setText("To perform a function about customer go to CUSTOMERS page in the left corner ");

            }else if(currentOption.equals("Add a car")) {
                label.setText("To perform a function about customer go to CARS page in the left corner ");

            }else if(currentOption.equals("Add a lease")) {
                label.setText("To perform a function about customer go to LEASES page in the left corner ");

            }else if(currentOption.equals("Add an employee")) {
                label.setText("To perform a function about customer go to EMPLOYEES page in the left corner ");

            }else if(currentOption.equals("Delete a customer") ) {
                label.setText("To perform a function about customer go to CUSTOMERS page in the left corner ");

            }else if(currentOption.equals("Delete a car")) {
                label.setText("To perform a function about customer go to CARS page in the left corner ");

            }else if(currentOption.equals("Delete a lease")) {
                label.setText("To perform a function about customer go to LEASES page in the left corner ");

            }else if(currentOption.equals("Delete an employee")) {
                label.setText("To perform a function about customer go to EMPLOYEES page in the left corner ");

            }else if(currentOption.equals("Modify a customer") ) {
                label.setText("To perform a function about customer go to CUSTOMERS page in the left corner ");

            }else if(currentOption.equals("Modify a car")) {
                label.setText("To perform a function about customer go to CARS page in the left corner ");

            }else if(currentOption.equals("Modify a lease")) {
                label.setText("To perform a function about customer go to LEASES page in the left corner ");

            }else if(currentOption.equals("Modify a employee")) {
                label.setText("To perform a function about customer go to EMPLOYEES page in the left corner ");

            }if(currentOption.equals("Search a customer") ) {
                label.setText("To perform a function about customer go to CUSTOMERS page in the left corner ");

            }else if(currentOption.equals("Search a car")) {
                label.setText("To perform a function about customer go to CARS page in the left corner ");

            }else if(currentOption.equals("Search a lease")) {
                label.setText("To perform a function about customer go to LEASES page in the left corner ");

            }else if(currentOption.equals("Search an employee")) {
                label.setText("To perform a function about customer go to EMPLOYEES page in the left corner ");

            }else if(currentOption.equals("Logout")) {
                label.setText("To log out go to LOGOUT page in the left corner ");
            }else if(currentOption.equals("Change Password")) {
                label.setText("To change password go to SETTINGS page in the left corner ");
            }else if(currentOption.equals("Print a lease")) {
                label.setText("To print a lease go to LEASES page in the left corner ");
            }
        }







    }

    public void switchToCars(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("cars.fxml"));
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

    public void fillGraphic() {
    }









}
