package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class homepageController implements Initializable {

    @FXML
    private TreeView treeView;

    public void initialize(URL url, ResourceBundle resourceBundle) {


        TreeItem<String> rootItem = new TreeItem<>("Options") ;
        //TreeItem<String> rootItem = new TreeItem<>("Alper", new ImageView(new Image("resim.jpeg"))) ;

        TreeItem<String> branchItem1 = new TreeItem<>("Customer") ;
        TreeItem<String> branchItem2 = new TreeItem<>("Car") ;
        TreeItem<String> branchItem3 = new TreeItem<>("Lease") ;

        TreeItem<String> leafItem1 = new TreeItem<>("Add a customer") ;
        TreeItem<String> leafItem2 = new TreeItem<>("Delete a customer") ;
        TreeItem<String> leafItem3 = new TreeItem<>("Modify a customer") ;
        TreeItem<String> leafItem4 = new TreeItem<>("Add a car") ;
        TreeItem<String> leafItem5 = new TreeItem<>("Delete a car") ;
        TreeItem<String> leafItem6 = new TreeItem<>("Modify a car") ;
        TreeItem<String> leafItem7 = new TreeItem<>("Add a lease") ;
        TreeItem<String> leafItem8 = new TreeItem<>("Delete a lease") ;
        TreeItem<String> leafItem9 = new TreeItem<>("Modify a lease") ;

        treeView.setRoot(rootItem);
        rootItem.getChildren().addAll(branchItem1,branchItem2,branchItem3);

        branchItem1.getChildren().addAll(leafItem1,leafItem2,leafItem3);
        branchItem2.getChildren().addAll(leafItem4,leafItem5,leafItem6);
        branchItem3.getChildren().addAll(leafItem7,leafItem8,leafItem9);

    }
}
