package ServerAll;

import java.io.Serializable;

public class Food implements Serializable{
    private int Restaurant_Id;
    private String Category;
    private String Name;
    private double Price;

    // contructors
    public Food() {
    }

    public Food(int restaurant_Id, String category, String name, double price) {
        this.Restaurant_Id = restaurant_Id;
        this.Category = category;
        this.Name = name;
        this.Price = price;
    }

    // setter methods
    public void setRestaurant_Id(int restaurant_Id) {
        this.Restaurant_Id = restaurant_Id;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    // getter methods
    public int getRestaurant_Id() {
        return Restaurant_Id;
    }

    public String getCategory() {
        return Category;
    }

    public String getName() {
        return Name;
    }

    public double getPrice() {
        return Price;
    }

    public void showFoodDetails() {
        System.out.println("Restaurant_Id: " + Restaurant_Id);
        System.out.println("Category: " + Category);
        System.out.println("Name: " + Name);
        System.out.println("Price :" + Price);
    }

}
