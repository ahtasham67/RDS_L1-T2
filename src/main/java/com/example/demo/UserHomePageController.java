package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class UserHomePageController {
    ClientMain client;
    @FXML
    public static Label RestaurantCount;
    @FXML
    public static Label FoodCount;

//    UserHomePageController(){
//        int restCount=ClientMain.allRestaurants.size();
//        RestaurantCount.setText(String.valueOf(restCount));
//        int foodCount=ClientMain.foodList.size();
//        FoodCount.setText(String.valueOf(foodCount));
//    }
    void setClient(ClientMain client){
        this.client = client;
    }

    public void searchRestaurant(ActionEvent actionEvent) throws Exception {
        client.showRestaurantSearch();
    }

    public void searchFood(ActionEvent actionEvent) throws Exception{
        client.showFoodSearch();
    }


    public void logOut(ActionEvent actionEvent) throws Exception {
        //client.logOut();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("You are about to log out");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Close the current client window
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();

            // Terminate any ongoing client operations (e.g., close sockets)
            try {
                ClientMain.socketWrapper.write("log out");
                ClientMain.socketWrapper.closeConnection();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            client.start(new Stage());

        }
    }

    public void showCartView(ActionEvent actionEvent) throws Exception {
        client.showCartWindow();
    }
//    public void showMenuList(ActionEvent actionEvent) throws Exception{
//        client.showMenuListPage();
//    }
//
//    public void addFoodItems(ActionEvent actionEvent) throws Exception {
//        client.addFoodPage();
//    }

    /* public void logOut(ActionEvent actionEvent) throws Exception{
         Stage stage = new Stage();
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("CONFIRMATION");
         alert.setHeaderText("You are about to log out");
         alert.setContentText("Are you confirm?");
         if (alert.showAndWait().get() == ButtonType.YES){
             stage.close();
             try {
                 ClientMain.socketWrapper.write("log out");
                 ClientMain.socketWrapper.closeConnection();
                 client.start(new Stage());
             } catch (IOException exception) {
                 exception.printStackTrace();
             }
         }
     }*/
//    public void logOut(ActionEvent actionEvent) throws Exception {
//        //client.logOut();
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("CONFIRMATION");
//        alert.setHeaderText("You are about to log out");
//        alert.setContentText("Are you sure?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//
//        if (result.isPresent() && result.get() == ButtonType.OK) {
//            // Close the current client window
//            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            stage.close();
//
//            // Terminate any ongoing client operations (e.g., close sockets)
//            try {
//                ClientMain.socketWrapper.write("log out");
//                ClientMain.socketWrapper.closeConnection();
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//            client.start(new Stage());
//
//        }
//    }
//
//    public void showOrderMessages(ActionEvent actionEvent) {
//    }
}