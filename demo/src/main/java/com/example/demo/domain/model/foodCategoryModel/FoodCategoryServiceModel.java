package com.example.demo.domain.model.foodCategoryModel;

import com.example.demo.domain.model.foodModel.FoodServiceModel;

import java.util.List;

public class FoodCategoryServiceModel {

    private int id;
    private String name;

    private List<FoodServiceModel> foods;

    public FoodCategoryServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodServiceModel> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodServiceModel> foods) {
        this.foods = foods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
