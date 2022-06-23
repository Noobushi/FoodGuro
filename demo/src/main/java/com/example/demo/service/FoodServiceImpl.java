package com.example.demo.service;

import com.example.demo.domain.entity.Food;
import com.example.demo.domain.entity.FoodCategory;
import com.example.demo.domain.model.foodModel.FoodServiceModel;
import com.example.demo.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FoodServiceImpl {
    private final FoodCategoryServiceImpl foodCategoryService;

    private final FoodRepository foodRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public FoodServiceImpl(FoodCategoryServiceImpl foodCategoryService, FoodRepository foodRepository, ModelMapper modelMapper) {
        this.foodCategoryService = foodCategoryService;
        this.foodRepository = foodRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public String createFood(FoodServiceModel foodServiceModel) {
        Food food = modelMapper.map(foodServiceModel, Food.class);
        FoodCategory category = foodCategoryService.findByName(foodServiceModel.getFoodCategory());

        foodRepository.save(food);
        category.getFoods().add(food);
        return food.getName();
    }
}
