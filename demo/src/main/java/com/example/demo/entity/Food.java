package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Food extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;
    private String name;

    private BigDecimal price;

    @Lob
    private String description;

    @OneToMany(targetEntity = ImageDataBase.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    private List<ImageDataBase> image;

    public Food() {
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

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
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
