package com.example.demo.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Food extends BaseEntity {

    private String name;

    private BigDecimal price;

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
}
