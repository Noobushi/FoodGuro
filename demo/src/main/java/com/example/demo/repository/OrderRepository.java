package com.example.demo.repository;

import com.example.demo.entity.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderFood,Integer> {
}
