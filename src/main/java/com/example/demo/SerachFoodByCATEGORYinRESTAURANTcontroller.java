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

public class SerachFoodByCATEGORYinRESTAURANTcontroller implements Initializable {

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
    private ArrayList<Food> foodList;
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

    public void FOODsearchCategoryInRestaurant(ActionEvent event) {
        vBox.getChildren().clear();
        scrollPaneName.setVisible(false);
        titleBar.setVisible(false);
        errorMessageName.setVisible(false);
        String name = textFieldName.getText();
        String restName = textFieldName1.getText();
        if (name.equals("") || restName.equals("")) {
            errorMessageName.setVisible(true);
            errorMessageName.setText("Proper Input Not Given");
            return;
        }

        // ArrayList<Movie> moviesToShow = new ArrayList<>();
        ArrayList<Food> foodToShow = new ArrayList<>();

        foodToShow = (ArrayList<Food>) RDS.searchBycategoryInrest(name, restName);

        if (foodToShow.size() == 0) {
            errorMessageName.setVisible(true);
            errorMessageName.setText("No Food Item found");
            return;
        }

        scrollPaneName.setVisible(true);
        titleBar.setVisible(true);
        int j = 1;
        for (int i = foodToShow.size() - 1; i >= 0; i--) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hboxFOOD.fxml"));
            Node root;
            try {
                root = (Node) fxmlLoader.load();
                ((HboxControllerFOOD) fxmlLoader.getController()).setRestaurant(foodToShow.get(i));
                ((HboxControllerFOOD) fxmlLoader.getController()).setFields(j);
                vBox.getChildren().add(root);
                j++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void backToHome() {
        try {
            client.showFoodSearch();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
