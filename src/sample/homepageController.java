package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homepageController implements Initializable {

    @FXML
    private TreeView treeView;

    @FXML
    private Text label;

    private String currentOption;



    @FXML
    private Button homepageButton;

    @FXML
    private Button carsButton;

    @FXML
    private Button customersButton;

    @FXML
    private Button leasesButton;

    @FXML
    private Button employeesButton;

    @FXML
    private Button settingsButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        //treeView.getStylesheets().add("myTree.css");




        TreeItem<String> rootItem = new TreeItem<>("Options") ;


        TreeItem<String> branchItem1 = new TreeItem<>("Customer") ;
        TreeItem<String> branchItem2 = new TreeItem<>("Car") ;
        TreeItem<String> branchItem3 = new TreeItem<>("Lease") ;
        TreeItem<String> branchItem4 = new TreeItem<>("Employee") ;
        TreeItem<String> branchItem5 = new TreeItem<>("Settings") ;


        TreeItem<String> leafItem1 = new TreeItem<>("Add a customer") ;
        TreeItem<String> leafItem2 = new TreeItem<>("Delete a customer") ;
        TreeItem<String> leafItem3 = new TreeItem<>("Modify a customer") ;
        TreeItem<String> leafItem4 = new TreeItem<>("Add a car") ;
        TreeItem<String> leafItem5 = new TreeItem<>("Delete a car") ;
        TreeItem<String> leafItem6 = new TreeItem<>("Modify a car") ;
        TreeItem<String> leafItem7 = new TreeItem<>("Add a lease") ;
        TreeItem<String> leafItem8 = new TreeItem<>("Delete a lease") ;
        TreeItem<String> leafItem9 = new TreeItem<>("Modify a lease") ;
        TreeItem<String> leafItem10 = new TreeItem<>("Add an employee") ;
        TreeItem<String> leafItem11 = new TreeItem<>("Delete an employee") ;
        TreeItem<String> leafItem12 = new TreeItem<>("Modify an employee") ;

        TreeItem<String> leafItem13 = new TreeItem<>("Search a customer") ;
        TreeItem<String> leafItem14 = new TreeItem<>("Search a car") ;
        TreeItem<String> leafItem15 = new TreeItem<>("Search a lease") ;
        TreeItem<String> leafItem16 = new TreeItem<>("Search an employee") ;

        TreeItem<String> leafItem17 = new TreeItem<>("Log out") ;

        treeView.setRoot(rootItem);
        rootItem.getChildren().addAll(branchItem1,branchItem2,branchItem3,branchItem4,branchItem5);

        branchItem1.getChildren().addAll(leafItem1,leafItem2,leafItem3,leafItem13);
        branchItem2.getChildren().addAll(leafItem4,leafItem5,leafItem6,leafItem14);
        branchItem3.getChildren().addAll(leafItem7,leafItem8,leafItem9,leafItem15);
        branchItem4.getChildren().addAll(leafItem10,leafItem11,leafItem12,leafItem16);
        branchItem5.getChildren().addAll(leafItem17);

    }

    public void getOption() {

        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();

        if(item != null) {
            System.out.println(item.getValue());
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

            }else if(currentOption.equals("Log out")) {
                label.setText("To log out go to SETTINGS page in the left corner ");
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











}
