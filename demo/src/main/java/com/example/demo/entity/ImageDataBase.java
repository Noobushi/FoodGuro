package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class ImageDataBase extends BaseEntity {
    private String name;

    public ImageDataBase() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
