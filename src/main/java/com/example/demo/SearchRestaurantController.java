package com.example.demo;

import ServerAll.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SearchRestaurantController {
    ClientMain client;
    void setClient(ClientMain client){
        this.client = client;
    }

    public void searchRestaurantByName(ActionEvent actionEvent) throws Exception {
        client.showRestaurantSearchBYNAME();
    }

    public void searchRestaurantByScore(ActionEvent actionEvent) throws Exception {
        client.showRestaurantSearchBYSCORE();
    }

    public void searchRestaurantByCategory(ActionEvent actionEvent) throws Exception {
       client.showRestaurantSearchBYCategory();
    }

    public void searchRestaurantByPrice(ActionEvent actionEvent)throws Exception {
        client.showRestaurantSearchBYPrice();
    }

    public void searchRestaurantByZipCode(ActionEvent actionEvent) throws Exception {
        client.showRestaurantSearchBYZipCode();
    }

    @FXML
    public void backToSearchPage(ActionEvent actionEvent) throws Exception {
        try {
            client.showUserHome();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void categoryWiseRestaurant(ActionEvent actionEvent) throws Exception {
        client.CategoryWiseRestaurantPage();
    }
}
