package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class FoodCategory extends BaseEntity{

    private String name;

    @OneToMany(targetEntity = Food.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<Food> foods;

    public FoodCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

}
