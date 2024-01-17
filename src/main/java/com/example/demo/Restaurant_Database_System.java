package com.example.demo;

import java.util.*;
import java.io.*;
import java.security.PublicKey;

public class Restaurant_Database_System {
    private List<Restaurant> restaurantList;
    private List<Food> foodItems;

    public Restaurant_Database_System() {
        restaurantList = new ArrayList<>();
        //restaurantList=ClientMain.allRestaurants;
        for(Restaurant rest: ClientMain.allRestaurants){
            restaurantList.add(rest);
        }
        foodItems = new ArrayList<>();
        //foodItems=ClientMain.foodList;
        for(Food food: ClientMain.foodList){
            foodItems.add(food);
        }
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public List<Food> getFoodList() {
        return foodItems;
    }

    public void addRestaurant(Restaurant restaurant) {
        boolean alreadyExistRestaurant = false;
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getName().equalsIgnoreCase(restaurant.getName())
                    && restaurantList.get(i).getId() == restaurant.getId()) {
                alreadyExistRestaurant = true;
            }
        }
        if (!alreadyExistRestaurant)
            restaurantList.add(restaurant);
        else
            return;
    }

    public boolean doesRestuarantExist(String resStr) {
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getName().equalsIgnoreCase(resStr)) {
                return true;
            }
        }
        return false;
    }

    public boolean foodAlreadyExist(Food food) {
        for (int i = 0; i < foodItems.size(); i++) {
            if ((foodItems.get(i).getRestaurant_Id() == food.getRestaurant_Id())
                    && (foodItems.get(i).getName().equalsIgnoreCase(food.getName()))
                    && (foodItems.get(i).getCategory().equalsIgnoreCase(food.getCategory()))
                    && (foodItems.get(i).getPrice() == food.getPrice()))
                return true;
        }
        return false;
    }

    public void addFoodItem(Food food) {
        if (!foodAlreadyExist(food))
            foodItems.add(food);
        else
            return;
    }

    public int getRestaurantIdByName(String name) {
        int temp = -1;
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getName().equalsIgnoreCase(name)) {
                temp = restaurantList.get(i).getId();
                break;
            }
        }
        return temp;
    }

    public String getRestaurantNameById(int id) {

        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getId()==id) {
                 return restaurantList.get(i).getName();
            }
        }
      return "";
    }
    public List<Restaurant> searchRestaurantByName(String str) {
        List<Restaurant> searchRestaurantbyName = new ArrayList<>();
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getName().toLowerCase().contains(str.toLowerCase())) {
            //if (restaurantList.get(i).getName().equalsIgnoreCase(str)) {
                searchRestaurantbyName.add(restaurantList.get(i));
            }
        }
        return searchRestaurantbyName;
    }

    public List<Restaurant> searchRestaurantbyScore(double start, double end) {
        // boolean exist = false;
        List<Restaurant> searchRestaurantbyScoreList = new ArrayList<>();
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getScore() >= start && restaurantList.get(i).getScore() <= end) {
                // restaurantList.get(i).ShowDetails();
                searchRestaurantbyScoreList.add(restaurantList.get(i));
                // exist = true;
            }
        }
        /*
         * if (exist) {
         * return searchRestaurantbyScoreList;
         * } else {
         * return null;
         * }
         */
        return searchRestaurantbyScoreList;
    }

    public List<Restaurant> searchRestaurantbyCategory(String cat) {
        List<Restaurant> searchRestaurantbyCategoryList = new ArrayList<>();
        for (int i = 0; i < restaurantList.size(); i++) {
            for (int j = 0; j < restaurantList.get(i).getCategories().size(); j++) {
                 if (restaurantList.get(i).getCategories().get(j).toLowerCase().contains(cat.toLowerCase())) {
             //   if (restaurantList.get(i).getCategories().get(j).equalsIgnoreCase(cat)) {
                    // System.out.println(restaurantList.get(i).getName());
                    // restaurantList.get(i).ShowDetails();
                    searchRestaurantbyCategoryList.add(restaurantList.get(i));
                }
            }
        }
        return searchRestaurantbyCategoryList;
    }

    public List<Restaurant> searchRestaurantbyPrice(String pr) {
        List<Restaurant> searchRestaurantbyPriceList = new ArrayList<>();
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getPrice().equals(pr)) {
                // System.out.println(restaurantList.get(i).getName());
                // restaurantList.get(i).ShowDetails();
                searchRestaurantbyPriceList.add(restaurantList.get(i));
            }
        }
        return searchRestaurantbyPriceList;
    }

    public List<Restaurant> searchRestaurantbyZipCode(String zip) {
        List<Restaurant> searchRestaurantbyzip = new ArrayList<>();
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getZipCode().equals(zip)) {
                // System.out.println(restaurantList.get(i).getName());
                // restaurantList.get(i).ShowDetails();
                searchRestaurantbyzip.add(restaurantList.get(i));
            }
        }
        return searchRestaurantbyzip;
    }


    /***************** FOOD SEARCH PORTION *******************/
    public List<Food> searchFoodByName(String foodName) {
        List<Food> foundFoodwithNames = new ArrayList<>();
        for (int i = 0; i < foodItems.size(); i++) {
            if (foodItems.get(i).getName().toLowerCase().contains(foodName.toLowerCase())) {
                // if (foodItems.get(i).getName().equalsIgnoreCase(foodName)) {
                foundFoodwithNames.add(foodItems.get(i));
            }
        }
        return foundFoodwithNames;
    }

    public List<Food> searchFoodByNameOrestaurant(String foodName, String foodNameRestaurant) {
        List<Food> foundFoodwithNameOrestaurant = new ArrayList<>();
        for (int i = 0; i < foodItems.size(); i++) {
            if (foodItems.get(i).getName().toLowerCase().contains(foodName.toLowerCase())
                    && foodItems.get(i).getRestaurant_Id() == getRestaurantIdByName(foodNameRestaurant)) {
                foundFoodwithNameOrestaurant.add(foodItems.get(i));
            }
        }
        return foundFoodwithNameOrestaurant;
    }

    public List<Food> searchFoodbyCategory(String foodCategory) {
        List<Food> foodByCategory = new ArrayList<>();
        System.out.println("Food of " + foodCategory + " category: ");
        for (int i = 0; i < foodItems.size(); i++) {
            if (foodItems.get(i).getCategory().toLowerCase().contains(foodCategory.toLowerCase())) {
                foodByCategory.add(foodItems.get(i));
            }
        }
        return foodByCategory;
    }

    public List<Food> searchBycategoryInrest(String foodCategoryInRestuarant, String categoryInRestaurant) {
        List<Food> foodInCategoryInRestaurant = new ArrayList<>();
        for (int i = 0; i < foodItems.size(); i++) {
            if (foodItems.get(i).getCategory().toLowerCase().contains(foodCategoryInRestuarant.toLowerCase())
                    && foodItems.get(i).getRestaurant_Id() == getRestaurantIdByName(categoryInRestaurant)) {
                foodInCategoryInRestaurant.add(foodItems.get(i));
            }
        }
        return foodInCategoryInRestaurant;
    }

    public List<Food> searchFoodbyPriceRange(double startRange, double endRange) {
        List<Food> foodInRange = new ArrayList<>();
        for (int i = 0; i < foodItems.size(); i++) {
            if (foodItems.get(i).getPrice() >= startRange && foodItems.get(i).getPrice() <= endRange) {
                foodInRange.add(foodItems.get(i));
            }
        }
        return foodInRange;
    }

    public List<Food> foodInrangeInRestaurant(double startRangeforRestaurant, double endRangeforRestaurant,
            String RestaurantStr) {
        List<Food> foodInRangeInRestaurant = new ArrayList<>();

        for (int i = 0; i < foodItems.size(); i++) {
            if ((foodItems.get(i).getPrice() >= startRangeforRestaurant
                    && foodItems.get(i).getPrice() <= endRangeforRestaurant)
                    && (foodItems.get(i).getRestaurant_Id() == getRestaurantIdByName(RestaurantStr))) {

                foodInRangeInRestaurant.add(foodItems.get(i));
            }
        }
        return foodInRangeInRestaurant;
    }

    public List<Food> searchCostliestFood(String restaurantForTheCostliestFood) {
        double maxPrice = -1;
        // int maxIndex = -1;
        List<Food> costliestItems = new ArrayList<>();
        int restaurantId = getRestaurantIdByName(restaurantForTheCostliestFood);
        for (int i = 0; i < foodItems.size(); i++) {
            if ((restaurantId == foodItems.get(i).getRestaurant_Id())
                    && maxPrice < foodItems.get(i).getPrice()) {
                maxPrice = foodItems.get(i).getPrice();
                // maxIndex = i;
            }
        }
        for (int i = 0; i < foodItems.size(); i++) {
            if (foodItems.get(i).getPrice() == maxPrice) {
                costliestItems.add(foodItems.get(i));
            }
        }
        return costliestItems;
    }

    public int getFoodCount(Restaurant res) {
        int cnt = 0;
        for (Food food : foodItems) {
            if (food.getRestaurant_Id() == res.getId()) {
                cnt++;
            }
        }
        return cnt;
    }

    public boolean alreadyExistID(int restId) {
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getId() == restId) {
                return true;
            }
        }
        return false;
    }

    public void addResFromUser(int restId, String newName, double newScore, String newPrice, String newZipCode,
            List<String> newCategories) {
        Restaurant newRestaurant = new Restaurant(restId, newName, newScore, newPrice, newZipCode, newCategories);
        restaurantList.add(newRestaurant);
    }

    public void addfoodUser(int resId, String category, String itemName, double price) {
        Food newFoodItem = new Food(resId, category, itemName, price);
        if (!foodAlreadyExist(newFoodItem))
            foodItems.add(newFoodItem);
        else
            return;
    }

    //on test

    public List<String> getCategoryWiseRestaurantList() {

        List<String> categoryList = new ArrayList<>();
        for (int i = 0; i < restaurantList.size(); i++) {
            for (int j = 0; j < restaurantList.get(i).getCategories().size(); j++) {
                if (!categoryList.contains(restaurantList.get(i).getCategories().get(j))) {
                    categoryList.add(restaurantList.get(i).getCategories().get(j));
                }
            }
        }
        List<String> result = new ArrayList<>();
        for (String category : categoryList) {
            StringBuilder restaurantsForCategory = new StringBuilder();
            for (Restaurant restaurant : restaurantList) {
                for (String restaurantCategory : restaurant.getCategories()) {
                    if (restaurantCategory.equals(category)) {
                        if (restaurantsForCategory.length() > 0) {
                            restaurantsForCategory.append(", ");
                        }
                        restaurantsForCategory.append(restaurant.getName());
                        break;
                    }
                }
            }
            if (restaurantsForCategory.length() > 0) {
                result.add(category + ": " + restaurantsForCategory.toString());
            }
        }
        return result;
    }

}
