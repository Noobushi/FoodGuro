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
        food.setFoodCategory(category);

        Food persisted = foodRepository.save(food);
        category.getFoods().add(persisted);
        return food.getName();
    }

    @Transactional
    public String deleteFood(FoodServiceModel foodServiceModel) {
        Food food = foodRepository.findByName(foodServiceModel.getName());
        String foodName = food.getName();
        foodRepository.delete(food);
        return foodName;
    }

    @Transactional
    public FoodServiceModel editFood(FoodServiceModel foodServiceModel) {
        Food food = foodRepository.findById(foodServiceModel.getId()).get();

        FoodCategory newCategory = foodCategoryService.findByName(foodServiceModel.getFoodCategory());
        newCategory.getFoods().add(food);

        FoodCategory oldCategory = food.getFoodCategory();
        oldCategory.getFoods().remove(food);

        food.setName(foodServiceModel.getName());
        food.setPrice(foodServiceModel.getPrice());
        food.setDescription(foodServiceModel.getDescription());


        FoodServiceModel edited = modelMapper.map(food, FoodServiceModel.class);
        edited.setFoodCategory(foodServiceModel.getFoodCategory());

        return edited;
    }

    public Food findByName(String name){
        return this.foodRepository.findByName(name);
    }
}
