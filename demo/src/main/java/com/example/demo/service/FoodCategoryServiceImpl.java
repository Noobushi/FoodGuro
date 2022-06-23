package com.example.demo.service;

import com.example.demo.domain.entity.FoodCategory;
import com.example.demo.domain.model.foodCategoryModel.FoodCategoryServiceModel;
import com.example.demo.repository.FoodCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class    FoodCategoryServiceImpl {

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
}
