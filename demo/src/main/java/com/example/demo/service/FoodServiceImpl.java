package com.example.demo.service;

import com.example.demo.dto.foodDTO.FoodResponseDTO;
import com.example.demo.entity.Food;
import com.example.demo.entity.FoodCategory;
import com.example.demo.dto.foodDTO.FoodServiceDTO;
import com.example.demo.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class FoodServiceImpl extends BaseService {
    private final FoodCategoryServiceImpl foodCategoryService;
    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodCategoryServiceImpl foodCategoryService, FoodRepository foodRepository) {
        this.foodCategoryService = foodCategoryService;
        this.foodRepository = foodRepository;
    }

    @Transactional
    public FoodResponseDTO createFood(FoodServiceDTO input) {
        Food newFood = modelMapper.map(input, Food.class);
        FoodCategory category = foodCategoryService.findByName(input.getCategory());
        Food persistedFood = foodRepository.save(newFood);
        category.getFoods().add(persistedFood);
        FoodResponseDTO createdFood = modelMapper.map(newFood, FoodResponseDTO.class);
        return createdFood;
    }

    @Transactional
    public FoodResponseDTO deleteFood(FoodServiceDTO input) {
        Food foundFood = foodRepository.findByName(input.getName());
        checkIfNull(foundFood,input.getName());
        foodRepository.deleteFood(foundFood.getId());
        FoodResponseDTO deletedFood = modelMapper.map(foundFood, FoodResponseDTO.class);
        return deletedFood;
    }

    @Transactional
    public FoodResponseDTO editFood(FoodServiceDTO input) {
        Food food = foodRepository.findById(input.getId()).get();

        FoodCategory newCategory = foodCategoryService.findByName(input.getCategory());
        newCategory.getFoods().add(food);

        FoodCategory oldCategory = foodCategoryService.findByName(food.getCategory());
        oldCategory.getFoods().remove(food);

        food.setName(input.getName());
        food.setPrice(input.getPrice());
        food.setDescription(input.getDescription());
        food.setQuantity(input.getQuantity());
        food.setCategory(newCategory.getName());

        FoodResponseDTO newFood = modelMapper.map(food, FoodResponseDTO.class);
        return newFood;
    }

    public Food findByName(String foodName) {
        Food foundFood = foodRepository.findByName(foodName);
        checkIfNull(foundFood,foodName);
        return foundFood;
    }

    public String getImage(String foodName){
        List<Food> foundFood = foodRepository.findFoodByName(foodName);
        for(Food food : foundFood){
            if(food.getName().equals(foodName)){
                return food.getImagePath();
            }
        }
        throw new NullPointerException(String.format("%s object doesnt exist!",foodName));
    }
}
