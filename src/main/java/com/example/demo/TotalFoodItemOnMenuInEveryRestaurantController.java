package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TotalFoodItemOnMenuInEveryRestaurantController implements Initializable {
    ClientMain client;
    @FXML
    TableView<RestaurantMenuItem> table;
    @FXML
    TableColumn<RestaurantMenuItem, String> titleColumn;
    @FXML
    TableColumn<RestaurantMenuItem, Integer> titleColumn1;

    ObservableList<RestaurantMenuItem> restaurantMenuItems = FXCollections.observableArrayList();

    void setClient(ClientMain client) {
        this.client = client;
    }

    public void setTable(ObservableList<RestaurantMenuItem> restaurantMenuItems) {
        this.restaurantMenuItems.setAll(restaurantMenuItems);
        table.setItems(this.restaurantMenuItems);
        table.setFixedCellSize(54);
        table.setMaxHeight(restaurantMenuItems.size() * table.getFixedCellSize() + 70);
    }

    @FXML
    void backToHome() {
        try {
            client.showFoodSearch();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantName"));
        titleColumn1.setCellValueFactory(new PropertyValueFactory<>("foodCount"));
    }
}
