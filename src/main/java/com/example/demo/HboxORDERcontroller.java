
package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HboxORDERcontroller {

    private orderedFood orderedFood;

    //    public Restaurant getRestaurant() {
//        return restaurant;
//    }
//
    public void setRestaurant(orderedFood food) {
        this.orderedFood = food;
    }

    @FXML
    private Label column1;
    @FXML
    private Label column11;
    @FXML
    private Label column111;

    @FXML
    private Label siNo;
    @FXML
    private HBox hBox;
    private boolean showTransfer = true;

    public void setShowTransfer(boolean bol) {
        showTransfer = bol;
    }

    public void serveOrder(ActionEvent event) {

    }

    public void setFields(int i) {
        if (i % 2 == 0) {
            hBox.setStyle("-fx-background-color:  #2E8BC0");
            column1.setStyle("-fx-text-fill: #ffffff");
            //column2.setStyle("-fx-text-fill: #ffffff");

        }
        siNo.setText(Integer.toString(i));
        column1.setText(orderedFood.getFood().getName());
        column11.setText(orderedFood.getUserName());
        column111.setText(orderedFood.getCnt() + "");
        //String categories = String.join(", ", restaurant.getCategories());
        //column2.setText(categories);
    }
}
