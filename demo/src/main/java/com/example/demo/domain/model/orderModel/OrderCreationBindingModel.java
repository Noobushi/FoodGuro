package com.example.demo.domain.model.orderModel;

import java.util.List;

public class OrderCreationBindingModel {

    private String User;

    List<String> foods;

    public OrderCreationBindingModel() {
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }
}
