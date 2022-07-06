package com.example.demo.domain.model.orderModel;
import java.util.List;

public class OrderServiceModel {

    private int id;

    private String user;

    List<String> foods;

    public OrderServiceModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }
}
