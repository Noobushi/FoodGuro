package com.example.demo.domain.model.orderModel;

import java.util.List;

public class OrderCreationBindingModel {

    public String username;

    List<String> foods;

    public OrderCreationBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }
}
