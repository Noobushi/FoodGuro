package com.example.demo.domain.model.foodModel;

import java.math.BigDecimal;

public class FoodCreationResponseAndEditModel {


    private int id;
    private String name;

    private String foodCategory;

    private BigDecimal price;

    public FoodCreationResponseAndEditModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
