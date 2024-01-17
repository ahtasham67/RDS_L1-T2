package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    ClientMain client;
    @FXML
    private Label welcomeText;
    @FXML
    private Button LoginasCustomer;
    @FXML
    private Button LoginasRestaurantAdmin;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    void setClient(ClientMain client) {
        this.client = client;
    }
    @FXML
    private void LoginasCustomer(ActionEvent actionEvent) throws IOException {
        client.showUserlogInPage();
    }
    @FXML
    private void LoginasRestaurantAdmin(ActionEvent actionEvent) throws IOException {
        client.showLoginPage();
    }

    public void loginCheck(ActionEvent actionEvent) {
    }
}