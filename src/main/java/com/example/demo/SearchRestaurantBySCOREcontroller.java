package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchRestaurantBySCOREcontroller implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldName1;
    @FXML
    private Label errorMessageName;
    @FXML
    private ScrollPane scrollPaneName;
    @FXML
    private VBox vBox;
    @FXML
    private HBox titleBar;
    //modify for food later
    private ArrayList<Restaurant> restaurantList;
    Restaurant_Database_System RDS = new Restaurant_Database_System();

    public void setRestaurantList(ArrayList<Restaurant> restaurants) {
        this.restaurantList = restaurants;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPaneName.setVisible(false);
        titleBar.setVisible(false);
    }

    ClientMain client;

    void setClient(ClientMain client) {
        this.client = client;
    }

    public void searchBYscore(ActionEvent event) {
        vBox.getChildren().clear();
        scrollPaneName.setVisible(false);
        titleBar.setVisible(false);
        errorMessageName.setVisible(false);
        String start = textFieldName.getText();
        String end = textFieldName1.getText();
        if (start.equals("") || end.equals("")) {
            errorMessageName.setVisible(true);
            errorMessageName.setText("Proper Input Not Given");
            textFieldName.clear();
            textFieldName1.clear();
            return;
        }

        // ArrayList<Movie> moviesToShow = new ArrayList<>();
        ArrayList<Restaurant> restaurantsToShow = new ArrayList<>();

        double num1, num2;
        try {
            num1 = Double.parseDouble(start);
            num2 = Double.parseDouble(end);
        } catch (Exception e) {
            errorMessageName.setVisible(true);
            errorMessageName.setText("Please Enter a Integer");
            return;
        }
//        for (int i = 0; i < restaurantList.size(); i++)
//        {
//            int time = restaurantList.get(i).getRunning_time();
//            if(time >= num1 && time <= num2)
//            {
//                moviesToShow.add(movies.get(i));
//            }
//        }
        restaurantsToShow = (ArrayList<Restaurant>) RDS.searchRestaurantbyScore(num1,num2);

        if (restaurantsToShow.size() == 0) {
            errorMessageName.setVisible(true);
            errorMessageName.setText("No Restaurant found");
            return;
        }

        scrollPaneName.setVisible(true);
        titleBar.setVisible(true);
        int j = 1;
        for (int i = restaurantsToShow.size() - 1; i >= 0; i--) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hbox.fxml"));
            Node root;
            try {
                root = (Node) fxmlLoader.load();
                ((HboxController) fxmlLoader.getController()).setRestaurant(restaurantsToShow.get(i));
                ((HboxController) fxmlLoader.getController()).setFields(j);
                vBox.getChildren().add(root);
                j++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        textFieldName1.clear();
        textFieldName.clear();
    }

    @FXML
    void backToHome() {
        try {
            client.showRestaurantSearch();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

