package com.example.demo.service;

import com.example.demo.domain.entity.Food;
import com.example.demo.domain.entity.FoodCategory;
import com.example.demo.domain.model.foodCategoryModel.FoodCategoryServiceModel;
import com.example.demo.domain.model.foodModel.FoodServiceModel;
import com.example.demo.repository.FoodCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodCategoryServiceImpl {

    private final FoodCategoryRepository foodCategoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public FoodCategoryServiceImpl(FoodCategoryRepository foodCategoryRepository, ModelMapper modelMapper) {
        this.foodCategoryRepository = foodCategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public String createFoodCategory(FoodCategoryServiceModel foodCategoryServiceModel) {
        FoodCategory foodCategory = modelMapper.map(foodCategoryServiceModel, FoodCategory.class);
        foodCategoryRepository.save(foodCategory);
        return foodCategory.getName();
    }

    public FoodCategory findByName(String name) {
        return this.foodCategoryRepository.findByName(name);
    }

    public List<FoodCategoryServiceModel> findAll() {
        List<FoodCategoryServiceModel> categories = new ArrayList<>();
        this.foodCategoryRepository.findAll().forEach(foodCategory -> {
            List<FoodServiceModel> currentFoodCategory = foodCategory.getFoods().stream().map(food -> {
                FoodServiceModel foodServiceModel = modelMapper.map(food, FoodServiceModel.class);
                foodServiceModel.setFoodCategory(food.getFoodCategory().getName());
                return foodServiceModel;
            }).collect(Collectors.toList());

            FoodCategoryServiceModel foodCategoryServiceModel = modelMapper.map(foodCategory, FoodCategoryServiceModel.class);
            foodCategoryServiceModel.setFoods(currentFoodCategory);
            categories.add(foodCategoryServiceModel);
        });
        return categories;

    }

    @Transactional
    public String deleteFoodCategory(FoodCategoryServiceModel foodCategoryServiceModel) {
        FoodCategory foodCategory = foodCategoryRepository.findByName(foodCategoryServiceModel.getName());
        String foodCategoryName = foodCategory.getName();
        foodCategoryRepository.delete(foodCategory);
        return foodCategoryName;
    }


}
