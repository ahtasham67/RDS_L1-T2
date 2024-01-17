
package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldName1;
    @FXML
    private Label errorMessageName;
    @FXML
    private ScrollPane scrollPaneName;
    @FXML
    private VBox vBox;
    @FXML
    private HBox titleBar;
    //modify for food later
    private ArrayList<Restaurant> restaurantList;
    Restaurant_Database_System RDS = new Restaurant_Database_System();

//    public void setRestaurantList(ArrayList<Restaurant> restaurants) {
//        this.restaurantList = restaurants;
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPaneName.setVisible(true);
        titleBar.setVisible(true);

        ArrayList<orderedFood>foodlistToShow=new ArrayList<>();
        foodlistToShow = ClientMain.foodOFOrder;



        scrollPaneName.setVisible(true);
        titleBar.setVisible(true);
        int j = 1;
        for(orderedFood food: foodlistToShow){
            System.out.println(food.getFood().getName());
        }
        for (int i = foodlistToShow.size() - 1; i >= 0; i--) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hboxORDER.fxml"));
            Node root;
            try {
                root = (Node) fxmlLoader.load();
                ((HboxORDERcontroller) fxmlLoader.getController()).setRestaurant(foodlistToShow.get(i));
                ((HboxORDERcontroller) fxmlLoader.getController()).setFields(j);
                vBox.getChildren().add(root);
                j++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    ClientMain client;

    void setClient(ClientMain client) {
        this.client = client;
    }



    @FXML
    void backToHome() {
       //ReadThread.stopKor();
        try {
            client.showCompanyHome();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
