package com.example.demo;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AddFoodController {
    ClientMain client;
    @FXML
    TextField enterName, enterCategory, enterPrice;
    //    @FXML
//    Label noticeLabel;
    Food food;

    void setClient(ClientMain client) {
        this.client = client;
    }

    @FXML
    public void addButton(ActionEvent actionEvent) {
        String[] foodAttribute = new String[3];
        foodAttribute[0] = enterName.getText();
        foodAttribute[1] = enterCategory.getText();
        foodAttribute[2] = enterPrice.getText();

        //
        //noticeLabel.setText("");//By Mukdho
        //


        if (foodAttribute[0] != null) {
            if (!FoodAlready(foodAttribute[0])) {
                if (foodAttribute[1] != null && (foodAttribute[2] != null)) {
                    try {

                        food = new Food(ClientMain.restaurantID, foodAttribute[1], foodAttribute[0], Double.parseDouble(foodAttribute[2]));
                        // showAdditionConfirmationMessage();
                        /////
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("CONFIRMATION");
                        alert.setHeaderText("You are about to add new food item");
                        alert.setContentText("Are you sure?");

                        Optional<ButtonType> result = alert.showAndWait();

                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            ClientMain.restaurantMenu.add(food);
                            try {
                                ClientMain.socketWrapper.write("Add food");
                                ClientMain.socketWrapper.write(food);
                                //alert message for confirmation

                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }

                        }
                        /////

                    } catch (Exception e) {
                        //noticeLabel.setText("Fill the properties properly");
                        System.out.println(e);
                    }
                } //else noticeLabel.setText("Food Already exist!");
            } //else noticeLabel.setText("Food Already exist!");

        } //else noticeLabel.setText("Title cannot be empty");
        backToHome();
    }

    @FXML
    void backToHome() {
        try {
            client.showCompanyHome();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    boolean FoodAlready(String title) {
        boolean foodAlreadyAdded = false;
        for (Food food : ClientMain.restaurantMenu) {
            if (title.equalsIgnoreCase(food.getName())) {
                foodAlreadyAdded = true;
                break;
            }
        }
        return foodAlreadyAdded;
    }



    public void enterName(ActionEvent actionEvent) {
    }

    public void enterCategory(ActionEvent actionEvent) {
    }

    public void enterPrice(ActionEvent actionEvent) {
    }
}

