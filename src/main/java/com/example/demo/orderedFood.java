package com.example.demo;

import java.io.Serializable;

public class orderedFood implements Serializable {
    Food food;
    int cnt;
    String userName;

    orderedFood(){}
    orderedFood(Food food, int cnt, String userName){
        this.food=food;
        this.cnt=cnt;
        this.userName=userName;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getCnt() {
        return cnt;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public Food getFood() {
        return food;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
