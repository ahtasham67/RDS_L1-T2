package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MoreInfoFOOD {
    ClientMain client;
    Restaurant_Database_System RDS;
    //    public void transfer(ActionEvent actionEvent) {
//        try {
//            client.showUserHome();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
    Food tobeordered;
    @FXML
    private Label name;
    @FXML
    private Label genre;
    @FXML
    private Label companyName;
    @FXML
    private Label year;
    @FXML
    private Label runningTime;
    @FXML
    private Label budget;
    @FXML
    private Label revenue;

    @FXML
    private Button addToCart;

    Stage baseStage;
    int count;

    public void setFields(Food food, Stage stage) {
        count = 0;
        RDS = new Restaurant_Database_System();
        this.baseStage = stage;
        name.setText(food.getName());
        tobeordered = new Food();
        tobeordered = food;
        companyName.setText(food.getCategory());
        String ID = RDS.getRestaurantNameById(food.getRestaurant_Id());
        genre.setText(ID);
        year.setText(Double.toString(food.getPrice()));

    }

    public void addToCart(ActionEvent actionEvent) throws Exception {
        count++;

        addToCart.setText("ADDED TO CART (" + String.valueOf(count) + ")");
        ClientMain.orderList.add(tobeordered);
        orderedFood newfood = new orderedFood(tobeordered, count, ClientMain.usrname);
        int indexToReplace = -1;
        for (int i = 0; i < ClientMain.foodOFOrder.size(); i++) {
            if (newfood.getFood() == ClientMain.foodOFOrder.get(i).getFood() && newfood.getCnt() > ClientMain.foodOFOrder.get(i).getCnt() && newfood.getUserName().equalsIgnoreCase(ClientMain.foodOFOrder.get(i).getUserName())) {
                indexToReplace = i;
                break;
            }
        }
        if (indexToReplace != -1) {
            ClientMain.foodOFOrder.set(indexToReplace, newfood);
        } else {
            ClientMain.foodOFOrder.add(newfood);
        }
        System.out.println(tobeordered.getName() + "added to cart");
        System.out.println("ORDER LIST");
//        for (Food food : ClientMain.orderList) {
//            System.out.println(food.getName());
//        }
        for (orderedFood f : ClientMain.foodOFOrder) {
            System.out.println(f.getFood().getName());
            System.out.println(f.getUserName());
            System.out.println(f.getCnt());
        }
    }

}
