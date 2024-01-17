package com.example.demo;

import java.io.Serializable;
import java.util.*;

public class Restaurant implements Serializable {
    private int Id;
    private String Name;
    private double Score;
    private String Price;
    private String ZipCode;
    private List<String> Categories;

    public Restaurant() {
        Categories = new ArrayList<>();
    }

    public Restaurant(int id, String name, double score, String price, String zipCode, List<String> categories) {
        Categories = new ArrayList<>();
        this.Id = id;
        this.Name = name;
        this.Score = score;
        this.Price = price;
        this.ZipCode = zipCode;
        this.Categories = categories;
    }

    // setters here...
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setScore(double score) {
        this.Score = score;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public void setZipCode(String zipCode) {
        this.ZipCode = zipCode;
    }

    public void setCategories(List<String> categories) {
        this.Categories = categories;
    }

    // getters here
    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public double getScore() {
        return Score;
    }

    public String getPrice() {
        return Price;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public List<String> getCategories() {
        return Categories;
    }

    public void ShowDetails() {
        System.out.println("Id: " + Id);
        System.out.println("Name: " + Name);
        System.out.println("Score: " + Score);
        System.out.println("Price: " + Price);
        System.out.println("ZipCode: " + ZipCode);
        System.out.println("Categories: ");
        for (int i = 0; i < Categories.size(); i++) {
            System.out.println(Categories.get(i));
        }
    }
}
