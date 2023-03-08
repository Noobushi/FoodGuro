package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderList extends BaseEntity {

    @OneToOne(mappedBy = "orderList")
    private User user;

    @OneToMany(targetEntity = Food.class, fetch = FetchType.EAGER)
    List<Food> foods;

    private String username;

    public OrderList() {
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}