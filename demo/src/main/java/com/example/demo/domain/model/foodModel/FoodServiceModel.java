package com.example.demo.domain.model.foodModel;

import com.example.demo.domain.entity.FoodCategory;

import javax.persistence.Lob;
import java.math.BigDecimal;

public class FoodServiceModel {

    private int id;

    private String foodCategory;

    private String name;

    private BigDecimal price;

    private String description;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
