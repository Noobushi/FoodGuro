package com.example.demo.domain.model.foodModel;

import com.example.demo.domain.entity.FoodCategory;

import java.math.BigDecimal;

public class FoodServiceModel {

    private String foodCategory;

    private String name;

    private BigDecimal price;

    public FoodServiceModel() {
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
