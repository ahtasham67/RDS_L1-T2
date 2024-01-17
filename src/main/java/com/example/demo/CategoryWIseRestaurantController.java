package com.example.demo;

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


public class CategoryWIseRestaurantController implements Initializable {
    ClientMain client;
    @FXML
    TableView<String> table;
    @FXML
    TableColumn<String, String> titleColumn;

    ObservableList<String> categoryWiseList;

    void setClient(ClientMain client) {
        this.client = client;
    }

    public void setTable(ObservableList<String> categoryWise) {

        table.setItems(categoryWise);
        table.setFixedCellSize(54);
        table.setMaxHeight(categoryWise.size() * table.getFixedCellSize() + 70);
    }

    @FXML
    void backToHome() {
        try {
            client.showRestaurantSearch();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
    }


}