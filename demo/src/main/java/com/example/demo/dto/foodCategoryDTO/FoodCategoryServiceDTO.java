package com.example.demo.dto.foodCategoryDTO;

import com.example.demo.dto.foodDTO.FoodServiceDTO;

import java.util.List;

public class FoodCategoryServiceDTO {

    private int id;
    private String name;

    private List<FoodServiceDTO> foods;

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


}
