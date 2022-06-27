package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;



public class carsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Slider mySlider;

    private static int myPriceRange;

    @FXML
    private TreeView treeView;

    @FXML
    private CheckBox available;


    private Image image;




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
    public void switchtoEditDialog(ActionEvent event) throws IOException {

        DatabaseConnection connectNow = new DatabaseConnection();

        int selectedIndex = treeView.getSelectionModel().getSelectedIndex();
        TreeItem selectedItem = (TreeItem) treeView.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0) {
            if(selectedItem.getParent().getValue().toString().equals("Cars")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Wrong Choice");
                alert.setHeaderText("Brand selected,not a Car");
                alert.setContentText("Please select a car not a brand.");
                alert.showAndWait();
            }else {
                CarEditController carEditController = new CarEditController();
                //arabanın ismini çek
                //database'DE searchCar fonksiyonu yaz
                //isimle bulsun
                //daha sonra o araba objesini setCar'a gönder.
                //System.out.println(selectedItem.getValue().toString());
                String[] splited = selectedItem.getValue().toString().split("-");

                String make = splited[0];
                String[] splited2 = splited [1].split("\\(");
                String model = splited2[0];
                String name = make+" "+model;
                //System.out.println("make: "+make);
                //System.out.println("model :" + model);
                //System.out.println("name: " + name);
                Car car = connectNow.searchCar(name);
                carEditController.setCar(car);

                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("CarEditDialog.fxml"));
                    Scene scene  = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();

                }catch (IOException ioException) {
                    ioException.printStackTrace();
                    ioException.getCause();
                }

            }






        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Car Selected");
            alert.setContentText("Please select a car.");
            alert.showAndWait();
        }







    }

    public void switchtoAddDialog(ActionEvent event) throws IOException {

        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("carAddDialog.fxml"));
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



    public void deleteCar() {

        DatabaseConnection connectNow = new DatabaseConnection();

        int selectedIndex = treeView.getSelectionModel().getSelectedIndex();
        TreeItem selectedItem = (TreeItem) treeView.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0) {
            if(selectedItem.getParent().getValue().toString().equals("Cars")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Wrong Choice");
                alert.setHeaderText("Brand selected,not a Car");
                alert.setContentText("Please select a car not a brand.");
                alert.showAndWait();
            }else {
                if(selectedItem.getParent().getChildren().size() == 1) {
                    selectedItem.getParent().getParent().getChildren().remove(selectedItem.getParent());
                }
                boolean remove = selectedItem.getParent().getChildren().remove(selectedItem);

                System.out.println(remove);
                System.out.println((String) selectedItem.getValue());
                connectNow.deleteCar((String) selectedItem.getValue());
            }

        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Car Selected");
            alert.setContentText("Please select a car in the table.");
            alert.showAndWait();
        }



    }

    public void createTreeView(ObservableList<Car> carList) {
        TreeItem<String> rootItem = new TreeItem<>("Cars") ;
        treeView.setRoot(rootItem);
        treeView.setShowRoot(false);


        HashMap<String,Integer> hm = new HashMap();



        //farklı modeller hashmap'e kaydedildi
        //hangi modelden kaç araba olduğu kaydedildi.
        for(int i = 0 ; i<carList.size() ; i++) {
            String carMake = carList.get(i).getMake();
            if(hm.containsKey(carMake)) {
                hm.replace(carMake,hm.get(carMake) + 1);
            }else {
                hm.put(carMake,1);
            }
        }

        //modeller hashmapten çekilip eklendi.
        TreeItem<String> branchItem;
        for(String key : hm.keySet()) {
            branchItem = new TreeItem<>(key) ;
            rootItem.getChildren().addAll(branchItem);
        }


        TreeItem<String> leafItem;
        for(int i = 0 ; i<carList.size() ; i++) {

            for(TreeItem<String> item :rootItem.getChildren()) {
                if(carList.get(i).getMake().equals(item.getValue())) {
                    //System.out.println(carList.get(i).getMake()+carList.get(i).getModel()+".jpg");
                    image = new Image(new File("/Users/pc/IdeaProjects/autovermietung/src/sample/"+carList.get(i).getMake().replaceAll("\\s+","")+carList.get(i).getModel().replaceAll("\\s+","")+".jpg").toURI().toString());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);
                    leafItem = new TreeItem<String>(carList.get(i).toString2(),imageView) ;
                    item.getChildren().addAll(leafItem);
                    break;

                }

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //araba sınıf için resim path ini saklayan
        //bir string attribute oluştur
        //Ordan çek al resmi ve imageview e setle


        TreeItem<String> rootItem = new TreeItem<>("Cars") ;
        treeView.setRoot(rootItem);
        treeView.setShowRoot(false);

        //branch itemler araba markası olucak
        //hashmaple arabalrın markalarını çek databaseden
        //ve her key için branchitem oluştur ve ağaca ekle.
        //nissan honda ford
        //carr_model de saklı

        //leaf itemler arabanın kendisi olucak
        //hangi markaya ait olduğunun bulup
        //onun altına eklicek.

        DatabaseConnection connectNow = new DatabaseConnection();
        ObservableList<Car> carList = connectNow.createAllCars();


        HashMap<String,Integer> hm = new HashMap();



        //farklı modeller hashmap'e kaydedildi
        //hangi modelden kaç araba olduğu kaydedildi.
        for(int i = 0 ; i<carList.size() ; i++) {
            String carMake = carList.get(i).getMake();
            if(hm.containsKey(carMake)) {
                hm.replace(carMake,hm.get(carMake) + 1);
            }else {
                hm.put(carMake,1);
            }
        }

        //modeller hashmapten çekilip eklendi.
        TreeItem<String> branchItem;
        for(String key : hm.keySet()) {
            branchItem = new TreeItem<>(key) ;
            rootItem.getChildren().addAll(branchItem);
        }



        //arabalrın hangi markaya ait olduğu
        //bulunup ağaç diyagramındaki yeri bulunucak ve ona göre eklenicek
        TreeItem<String> leafItem;



        for(int i = 0 ; i<carList.size() ; i++) {

            for(TreeItem<String> item :rootItem.getChildren()) {
                if(carList.get(i).getMake().equals(item.getValue())) {
                    //System.out.println(carList.get(i).getMake()+carList.get(i).getModel()+".jpg");
                    image = new Image(new File("/Users/pc/IdeaProjects/autovermietung/src/sample/"+carList.get(i).getMake().replaceAll("\\s+","")+carList.get(i).getModel().replaceAll("\\s+","")+".jpg").toURI().toString());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);
                    leafItem = new TreeItem<String>(carList.get(i).toString2(),imageView) ;
                    item.getChildren().addAll(leafItem);
                    break;

                }

            }
        }



        mySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                myPriceRange = (int) mySlider.getValue();

                /*

                if(myPriceRange == 0) {
                    System.out.println("My Price Range: 0-1.99 ");

                }else if(myPriceRange == 2) {
                    System.out.println("My Price Range: 2-3.99 ");
                }else if(myPriceRange == 4) {
                    System.out.println("My Price Range: 4-5.99 ");
                }else if(myPriceRange == 6) {
                    System.out.println("My Price Range: 6-7.99 ");
                }else if(myPriceRange == 8) {
                    System.out.println("My Price Range: 8-9.99 ");
                }else if(myPriceRange == 10) {
                    System.out.println("My Price Range: 10-12 ");
                }

                 */

                filterTreeView();
            }

        });
    }

    public void filterTreeView() {
        // 0-1.99 : 0
        // 2-3.99 : 2
        // 4-5.99 : 4
        // 6-7.99 : 6
        // 8-9.99 : 8
        // 10-12 : 10

        DatabaseConnection connectNow = new DatabaseConnection();
        ObservableList<Car> carList1;
        if(available.isSelected()) {
            carList1 = availableCars();
        }else {
            carList1 = connectNow.createAllCars();
        }

        ObservableList<Car> filteredCarList ;
        filteredCarList= FXCollections.observableArrayList();

        for(int i = 0;i<carList1.size();i++) {
            Car car = carList1.get(i);
            if(myPriceRange == 10 && car.getPriceProDay() >= myPriceRange) {
                filteredCarList.add(car);
            }else if (car.getPriceProDay() < myPriceRange + 1.99 && car.getPriceProDay() >= myPriceRange ) {
                // Filter matches first name.
                filteredCarList.add(car);
            }
        }

        createTreeView(filteredCarList);


    }

    public void refreshTreeView() {
        DatabaseConnection connectNow = new DatabaseConnection();
        ObservableList<Car> carList = connectNow.createAllCars();
        createTreeView(carList);
    }

    public ObservableList availableCars() {

        DatabaseConnection connectNow = new DatabaseConnection();
        ObservableList<Car> carList1 = connectNow.createAllCars();
        ObservableList<Car> availableCarList ;
        availableCarList= FXCollections.observableArrayList();

        for(int i = 0;i<carList1.size();i++) {
            Car car = carList1.get(i);
            if(car.isAvailable()) {
                availableCarList.add(car);
            }
        }
        return availableCarList;
    }


}
