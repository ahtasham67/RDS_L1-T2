package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserShowCartController implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldName1;
    @FXML
    private Label errorMessageName;
    @FXML
    private Label showTotalCost;
    @FXML
    private ScrollPane scrollPaneName;
    @FXML
    private VBox vBox;
    @FXML
    private HBox titleBar;
    //modify for food later
    private ArrayList<Restaurant> restaurantList;
    double totalprice = 0;
    Restaurant_Database_System RDS = new Restaurant_Database_System();

//    public void setRestaurantList(ArrayList<Restaurant> restaurants) {
//        this.restaurantList = restaurants;
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPaneName.setVisible(true);
        titleBar.setVisible(true);

        ArrayList<orderedFood> foodlistToShow = new ArrayList<>();
        foodlistToShow = ClientMain.foodOFOrder;
        for (orderedFood f : foodlistToShow) {
            totalprice += f.getFood().getPrice() * f.getCnt();
        }
        String formattedValue = String.format("%.2f", totalprice);
        showTotalCost.setText("TOTAL: " + formattedValue);

        if (foodlistToShow.size() == 0) {
            errorMessageName.setVisible(true);
            errorMessageName.setText("YOUR CART IS EMPTY");
            return;
        }

        scrollPaneName.setVisible(true);
        titleBar.setVisible(true);
        int j = 1;
        for (int i = foodlistToShow.size() - 1; i >= 0; i--) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hboxForCART.fxml"));
            Node root;
            try {
                root = (Node) fxmlLoader.load();
                ((HboxForCARTcontroller) fxmlLoader.getController()).setRestaurant(foodlistToShow.get(i));
                ((HboxForCARTcontroller) fxmlLoader.getController()).setFields(j);
                vBox.getChildren().add(root);
                j++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//        for (orderedFood f : ClientMain.foodOFOrder) {
//            System.out.println(f.getFood().getName());
//            System.out.println(f.getUserName());
//            System.out.println(f.getCnt());
//        }
    }

    ClientMain client;

    void setClient(ClientMain client) {
        this.client = client;
    }



    @FXML
    void backToHome() {

        try {
            client.showUserHome();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void placeOrder(ActionEvent actionEvent) {

        try {
            // showAdditionConfirmationMessage();
            /////
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("CONFIRMATION");
//            alert.setHeaderText("You are about to place an order");
//            alert.setContentText("Are you sure?");
//
//            Optional<ButtonType> result = alert.showAndWait();

            //if (result.isPresent() && result.get() == ButtonType.OK) {
            //   ClientMain.restaurantMenu.add(food);
            try {
                ClientMain.socketWrapper.write("order");
                ClientMain.socketWrapper.write(ClientMain.foodOFOrder);
                ClientMain.foodOFOrder.clear();
                //alert message for confirmation
client.showCartWindow();
            } catch (IOException exception) {
                exception.printStackTrace();
            }


        }
        catch (Exception e) {
            //noticeLabel.setText("Fill the properties properly");
            System.out.println(e);
        }

        // backToHome();
    }


}


