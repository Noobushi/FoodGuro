package com.example.demo.dto.orderFoodDTO;
import java.util.List;

public class OrderFoodServiceDTO {

    private int id;

    private String username;

    List<String> foods;

    public OrderFoodServiceDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }
}
