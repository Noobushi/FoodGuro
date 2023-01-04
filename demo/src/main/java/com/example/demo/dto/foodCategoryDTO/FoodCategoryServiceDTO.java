package com.example.demo.dto.foodCategoryDTO;

import com.example.demo.dto.foodDTO.FoodServiceDTO;
import com.example.demo.entity.ImageDataBase;

import java.util.List;

public class FoodCategoryServiceDTO {

    private int id;
    private String name;

    private List<FoodServiceDTO> foods;

    private List<ImageDataBase> image;

    public FoodCategoryServiceDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodServiceDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodServiceDTO> foods) {
        this.foods = foods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ImageDataBase> getImage() {
        return image;
    }

    public void setImage(List<ImageDataBase> image) {
        this.image = image;
    }
}
