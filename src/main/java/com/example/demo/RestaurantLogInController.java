package com.example.demo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

//import static client.project.ClientMain.companyMovie;

public class RestaurantLogInController {
    ClientMain client;
    @FXML
    TextField companyNameTextField, passwordTextField;
    @FXML
    Label notRegisteredNotification;
    @FXML
    Button loginButton;

    void setClient(ClientMain client) {
        this.client = client;
    }

    @FXML
    void loginCheck() throws IOException {

        ClientMain.restaurantName = companyNameTextField.getText();
        //System.out.println("jrgfrkg");
        try {
            ClientMain.socketWrapper.write(ClientMain.restaurantName);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        String messageFromServer = null;
        try {
            messageFromServer = (String) ClientMain.socketWrapper.read();
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        assert messageFromServer != null;
        if (messageFromServer.equals("No objection")) {
            companyNameTextField.clear();
            passwordTextField.clear();
            try {

                ClientMain.restaurantMenu = (ArrayList<Food>) ClientMain.socketWrapper.read();
                ClientMain.restaurantID = (int) ClientMain.socketWrapper.read();

            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();

            }

            client.showCompanyHome();
        } else {
            notRegisteredNotification.setText(messageFromServer);
            companyNameTextField.clear();
            passwordTextField.clear();
        }
    }


    public void addRestaurant(ActionEvent actionEvent) throws IOException {
        client.showAddRsetaurantPage();
    }
}
