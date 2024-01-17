package com.example.demo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


public class UserLogInController {
    ClientMain client;
    @FXML
    TextField userName, userPass;
    @FXML
    Label notRegisteredNotification;
    @FXML
    Button userLogInButton;

    void setClient(ClientMain client) {
        this.client = client;
    }

    @FXML
    void userLogIn() throws IOException, ClassNotFoundException {
        String username=userName.getText();
        ClientMain.usrname=username;
            ClientMain.socketWrapper.write(username);

            try {

                ArrayList<Restaurant>temp1=(ArrayList<Restaurant>) ClientMain.socketWrapper.read();
                ArrayList<Food>temp2=(ArrayList<Food>) ClientMain.socketWrapper.read();

                for(Restaurant rest:temp1){
                    ClientMain.allRestaurants.add(rest);
                }
                for(Food food:temp2){
                    ClientMain.foodList.add(food);
                }
//                for(Food food:ClientMain.restaurantMenu){
//                    System.out.println(food.getName());
//                }

            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();
                System.out.println("EEEERRRRROOOOOOOOO");
            }
           // ClientMain.readThread = new ReadThread();
            client.showUserHome();
            //ekhane error khacchi

    }
}
