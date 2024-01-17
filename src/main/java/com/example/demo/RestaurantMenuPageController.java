package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RestaurantMenuPageController implements Initializable {
    ClientMain client;
    @FXML
    TableView<Food> table;
    @FXML
    TableColumn<Food, String> titleColumn;
    @FXML
    TableColumn<Food, String> categoryColumn;
    @FXML
    TableColumn<Food, Double> priceColumn;

    //    @FXML
//    TableColumn<Movie, String> genreColumn;
//    @FXML
//    TableColumn<Movie, Integer> runningTimeColumn;
//    @FXML
//    TableColumn<Movie, Long> budgetColumn;
//    @FXML
//    TableColumn<Movie, Long> revenueColumn;
    void setClient(ClientMain client) {
        this.client = client;
    }

    public void setTable(ObservableList<Food> foodList) {
        table.setItems(foodList);
        table.setFixedCellSize(30);
        table.setMaxHeight(foodList.size() * table.getFixedCellSize() + 30);
    }

    @FXML
    void backToHome() {
        try {
            client.showCompanyHome();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

    }

}