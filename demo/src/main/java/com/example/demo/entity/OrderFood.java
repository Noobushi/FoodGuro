package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderFood extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private User user;

    @ManyToMany(targetEntity = Food.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_food_foods",
            joinColumns = @JoinColumn(
                    name = "order_food_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "food_id",
                    referencedColumnName = "id"
            )
    )
    List<Food> foods;

    public OrderFood() {
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
}
