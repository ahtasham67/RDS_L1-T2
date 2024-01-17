package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class RestaurantMainMenuController {
    ClientMain client;

    void setClient(ClientMain client) {
        this.client = client;
    }

    public void showMenuList(ActionEvent actionEvent) throws Exception {
        client.showMenuListPage();
    }

    public void addFoodItems(ActionEvent actionEvent) throws Exception {
        client.addFoodPage();
    }
    public void init(){
        new ReadThread(ClientMain.socketWrapper,client);
    }

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

    public void showOrderMessages(ActionEvent actionEvent) throws Exception {
        client.showOrderPagefromRestaurant();


    }


}


