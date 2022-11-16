package com.example.demo.dto.foodDTO;

import com.example.demo.entity.ImageDataBase;

import java.math.BigDecimal;
import java.util.List;

public class FoodServiceDTO {

    private int id;

    private String foodCategory;

    private String name;

    private BigDecimal price;

    private String description;

    private List<ImageDataBase> image;

    public FoodServiceDTO() {
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

    public List<ImageDataBase> getImage() {
        return image;
    }

    public void setImage(List<ImageDataBase> image) {
        this.image = image;
    }
}
