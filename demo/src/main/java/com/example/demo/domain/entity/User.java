package com.example.demo.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends BaseEntity {


    @OneToMany(targetEntity = OrderFood.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderFood> orderFood;
    private String firstName;

    private String lastName;

    private String city;
    
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private Integer password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public List<OrderFood> getOrder() {
        return orderFood;
    }

    public void setOrder(List<OrderFood> orderFood) {
        this.orderFood = orderFood;
    }
}

