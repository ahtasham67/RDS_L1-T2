package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MoreInfo {
    ClientMain client;
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
    private Button transferButton;

    Stage baseStage;

    public void noTransferButton()
    {
        transferButton.setVisible(false);
    }
    public void setFields(Restaurant rest, Stage stage)
    {
        this.baseStage = stage;
        name.setText(rest.getName());
        String categoryString = String.join(", ", rest.getCategories());
        companyName.setText(categoryString);
        String ID=String.valueOf(rest.getId());
        genre.setText(ID);
        year.setText(Double.toString(rest.getScore()));
        runningTime.setText(rest.getZipCode());
       // budget.setText(Long.toString(movie.getBudget()));
        revenue.setText(rest.getPrice());
    }

}
