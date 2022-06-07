package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;



public class carsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Slider slider;

    @FXML
    private TreeView treeView;


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

            boolean remove = selectedItem.getParent().getChildren().remove(selectedItem);
            System.out.println(remove);
            connectNow.deleteCar((String) selectedItem.getValue());

        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");
            alert.showAndWait();
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
                    image = new Image(new File("/Users/pc/IdeaProjects/autovermietung/src/sample/"+carList.get(i).getMake()+carList.get(i).getModel()+".jpg").toURI().toString());
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
}
