package com.example.demo;

import javafx.event.ActionEvent;

import java.io.IOException;

public class SearchFoodController {
ClientMain client;
    void setClient(ClientMain client){
        this.client = client;
    }


    public void searchFoodByName(ActionEvent actionEvent) throws Exception {
        client.showFoodSearchBYNAME();
    }

    public void searchFoodinRestaurantByName(ActionEvent actionEvent)throws Exception {
        client.showFoodSearchBYNAMEandRESTAURANT();
    }

    public void searchFoodByCategory(ActionEvent actionEvent) throws Exception{
        client.showFoodSearchBYCATEGORY();
    }

    public void searchFoodinRestaurantByCategory(ActionEvent actionEvent) throws Exception {
        client.showFoodSearchBYCATEGORYinRESTAURANT();
    }

    public void searchFoodByPriceRange(ActionEvent actionEvent)throws Exception {
        client.showFoodSearchBYPRICERANGE();
    }

    public void searchFoodInRestaurantByPriceRange(ActionEvent actionEvent) throws Exception{
        client.showFoodSearchBYPRICERANGEinRESTAURANT();
    }

    public void searchCostliestFood(ActionEvent actionEvent) throws Exception {
        client.showCostliestFoodofRestaurant();
    }

    public void backToSearchPage(ActionEvent actionEvent) throws Exception {
        try {
            client.showUserHome();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void TotalFoodItemOnMenu(ActionEvent actionEvent) throws Exception{
        client.totalFoodCountInRestaurantList();
    }
}
