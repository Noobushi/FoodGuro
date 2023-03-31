package com.example.demo.repository;

import com.example.demo.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
  List<Food> findFoodByName(String foodName);
  Food findByName(String foodName);
   @Modifying
   @Query("delete from Food f where f.id=:id")
   void deleteFood(@Param("id") Integer id);
}
