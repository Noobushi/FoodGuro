package com.example.demo.domain.model.foodModel;

import java.math.BigDecimal;

public class FoodCreationBindingModel {

    private String foodCategory;

    private String name;

    private BigDecimal price;

    public FoodCreationBindingModel() {
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
