package com.example.demo.dto.orderFoodDTO;

import java.util.List;

public class OrderListResponseDTO {

    public String username;

    List<String> foods;

    public OrderListResponseDTO() {
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
