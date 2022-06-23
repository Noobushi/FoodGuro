package com.example.demo.repository;

import com.example.demo.domain.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {
    FoodCategory findByName(String name);
}
