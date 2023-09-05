package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderList extends BaseEntity {

    private String username;

    @OneToMany(targetEntity = Food.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Food> foods;

    public OrderList() {
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}