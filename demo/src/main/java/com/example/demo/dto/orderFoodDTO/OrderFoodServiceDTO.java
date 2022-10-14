package com.example.demo.dto.orderFoodDTO;
import com.example.demo.entity.Food;

import java.util.List;

public class OrderFoodServiceDTO {

    private String username;

    List<String> foods;

    public OrderFoodServiceDTO() {
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
