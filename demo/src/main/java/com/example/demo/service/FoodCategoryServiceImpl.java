package com.example.demo.service;

import com.example.demo.dto.foodCategoryDTO.FoodCategoryResponseDTO;
import com.example.demo.entity.FoodCategory;
import com.example.demo.dto.foodCategoryDTO.FoodCategoryServiceDTO;
import com.example.demo.dto.foodDTO.FoodServiceDTO;
import com.example.demo.repository.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodCategoryServiceImpl extends BaseService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Autowired
    public FoodCategoryServiceImpl(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    @Transactional
    public FoodCategoryResponseDTO createFoodCategory(FoodCategoryServiceDTO input) {
        FoodCategory newCategory = modelMapper.map(input, FoodCategory.class);
        foodCategoryRepository.save(newCategory);
        FoodCategoryResponseDTO returnCategory = modelMapper.map(newCategory, FoodCategoryResponseDTO.class);
        return returnCategory;
    }

    public FoodCategory findByName(String foodCategoryName) {
        FoodCategory foundFoodCategory = foodCategoryRepository.findByName(foodCategoryName);
        checkIfNull(foundFoodCategory, foodCategoryName);
        return foundFoodCategory;
    }

    public List<FoodCategoryServiceDTO> findAll() {
        List<FoodCategoryServiceDTO> categories = new ArrayList<>();
        this.foodCategoryRepository.findAll().forEach(foodCategory -> {
            List<FoodServiceDTO> currentFoodCategory = foodCategory.getFoods().stream().map(food -> {
                FoodServiceDTO foodServiceDTO = modelMapper.map(food, FoodServiceDTO.class);
                return foodServiceDTO;
            }).collect(Collectors.toList());

            FoodCategoryServiceDTO foodCategoryServiceDTO = modelMapper.map(foodCategory, FoodCategoryServiceDTO.class);
            foodCategoryServiceDTO.setFoods(currentFoodCategory);
            categories.add(foodCategoryServiceDTO);
        });
        return categories;
    }

    @Transactional
    public FoodCategoryResponseDTO deleteFoodCategory(FoodCategoryServiceDTO input) {
        FoodCategory foundFoodCategory = foodCategoryRepository.findByName(input.getName());
        checkIfNull(foundFoodCategory, input.getName());
        foodCategoryRepository.delete(foundFoodCategory);
        FoodCategoryResponseDTO deletedFoodCategory = modelMapper.map(foundFoodCategory, FoodCategoryResponseDTO.class);
        return deletedFoodCategory;
    }

    @Transactional
    public List<FoodServiceDTO> getFoodsInCategory(String categoryName) {
        FoodCategory foodCategory = foodCategoryRepository.findByName(categoryName);
        checkIfNull(foodCategory, categoryName);
        return foodCategory.getFoods().stream().map(food -> modelMapper.map(food, FoodServiceDTO.class)
        ).collect(Collectors.toList());
    }

}
