
package com.example.demo;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.layout.HBox;
        import javafx.stage.Stage;

        import java.io.IOException;

public class HboxForCARTcontroller {

    private orderedFood orderedFood;

    //    public Restaurant getRestaurant() {
//        return restaurant;
//    }
//
    public void setRestaurant(orderedFood food) {
        this.orderedFood = food;
    }

    @FXML
    private Label column1;
    @FXML
    private Label column11;
    @FXML
    private Label column111;

    @FXML
    private Label siNo;
    @FXML
    private HBox hBox;


    public void serveOrder(ActionEvent event) {
//        Parent root;
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("moreInfo.fxml"));
//            root = fxmlLoader.load();
//            Stage stage = new Stage();
//            (() fxmlLoader.getController()).setFields(orderedFood, stage);
//            if (!showTransfer) {
//                // ((MoreInfo) fxmlLoader.getController()).noTransferButton();
//            }
//            stage.setTitle("More Info: ");
//            stage.setScene(new Scene(root));
//            stage.setResizable(false);
//            stage.show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void setFields(int i) {
        if (i % 2 == 0) {
            hBox.setStyle("-fx-background-color:  #2E8BC0");
            column1.setStyle("-fx-text-fill: #ffffff");
            column11.setStyle("-fx-text-fill: #ffffff");
            column111.setStyle("-fx-text-fill: #ffffff");

        }
        siNo.setText(Integer.toString(i));
        column1.setText(orderedFood.getFood().getName());
        column11.setText(orderedFood.getFood().getPrice()+"");
        column111.setText(orderedFood.getCnt()+"");
        //String categories = String.join(", ", restaurant.getCategories());
        //column2.setText(categories);
    }
}
