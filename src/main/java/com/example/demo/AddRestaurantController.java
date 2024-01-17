package com.example.demo;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


public class AddRestaurantController {
    ClientMain client;
    @FXML
    TextField restaurantName, enterID, enterPrice, enterScore, EnterZipCode, category1, category2, category3;

    void setClient(ClientMain client) {
        this.client = client;
    }

    @FXML
    public void addRestaurant(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String name = restaurantName.getText();
        String Id = enterID.getText();
        String price = enterPrice.getText();
        String Score = enterScore.getText();
        String zipCode = EnterZipCode.getText();
        String Category1 = category1.getText();
        String Category2 = category2.getText();
        String Category3 = category3.getText();
        ArrayList<String> categories = new ArrayList<>();
        categories.add(Category1);
        if (Category2 == null)
            categories.add("");
        else
        categories.add(Category2);
        if (Category3 == null)
            categories.add("");

        else categories.add(Category3);
        Restaurant restaurant = new Restaurant(Integer.parseInt(Id), name, Double.parseDouble(Score), price, zipCode, categories);
        //Restaurant_Database_System RDS=new Restaurant_Database_System();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("You are about to add new Restaurant");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                ClientMain.socketWrapper.write("Add restaurant");
                ClientMain.socketWrapper.write(restaurant);
                //alert message for confirmation

            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }
        String str = (String) ClientMain.socketWrapper.read();
        if (str.equalsIgnoreCase("okay")) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("CONFIRMATION");
            alert1.setHeaderText("Congratulations!You have added new Restaurant");
            alert1.setContentText("Return Home");

            Optional<ButtonType> result1 = alert1.showAndWait();

            if (result1.isPresent() && result1.get() == ButtonType.OK) {
                backToHome();
            }
        } else if (str.equalsIgnoreCase("already exist")) {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("CONFIRMATION");
            alert2.setHeaderText("Restaurant Already Exist!!");
            alert2.setContentText("Try again");

            Optional<ButtonType> result2 = alert2.showAndWait();

            if (result2.isPresent() && result2.get() == ButtonType.OK) {
                backToHome();
                //client.showAddRsetaurantPage();
            }
           else if (result2.isPresent() && result2.get() == ButtonType.CANCEL) {
                backToHome();
                //client.showAddRsetaurantPage();
            }
        }

    }

    @FXML
    void backToHome() {
        try {
            client.showLoginPage();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

