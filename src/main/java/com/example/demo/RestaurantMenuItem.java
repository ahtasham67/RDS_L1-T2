package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RestaurantMenuItem {
    private final SimpleStringProperty restaurantName;
    private final SimpleIntegerProperty foodCount;

    public RestaurantMenuItem(String restaurantName, int foodCount) {
        this.restaurantName = new SimpleStringProperty(restaurantName);
        this.foodCount = new SimpleIntegerProperty(foodCount);
    }

    public String getRestaurantName() {
        return restaurantName.get();
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName.set(restaurantName);
    }

    public int getFoodCount() {
        return foodCount.get();
    }

    public void setFoodCount(int foodCount) {
        this.foodCount.set(foodCount);
    }
}
