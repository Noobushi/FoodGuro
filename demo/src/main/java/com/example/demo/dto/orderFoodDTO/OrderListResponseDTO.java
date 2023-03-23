package com.example.demo.dto.orderFoodDTO;

import com.example.demo.entity.Food;

import java.util.List;

public class OrderListResponseDTO {
    List<Food> foods;

    public OrderListResponseDTO() {
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
