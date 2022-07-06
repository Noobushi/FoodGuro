package com.example.demo.repository;

import com.example.demo.domain.entity.OrderFood;
import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderFood,Integer> {
}
