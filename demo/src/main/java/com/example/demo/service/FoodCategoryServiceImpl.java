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
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FoodCategoryServiceImpl extends BaseService{

    private final FoodCategoryRepository foodCategoryRepository;

    @Autowired
    public FoodCategoryServiceImpl(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    @Transactional
    public FoodCategoryResponseDTO createFoodCategory(FoodCategoryServiceDTO input) {
        FoodCategory newFoodCategory = modelMapper.map(input, FoodCategory.class);
        foodCategoryRepository.save(newFoodCategory);
        FoodCategoryResponseDTO returnFoodCategory = modelMapper.map(newFoodCategory, FoodCategoryResponseDTO.class);
        return returnFoodCategory;
    }

    public FoodCategory findByName(String name) {
        if (Objects.isNull(name)){
            throw new NullPointerException("No such category found!");
        }
        return this.foodCategoryRepository.findByName(name);
    }

    public List<FoodCategoryServiceDTO> findAll() {
        List<FoodCategoryServiceDTO> categories = new ArrayList<>();
        this.foodCategoryRepository.findAll().forEach(foodCategory -> {
            List<FoodServiceDTO> currentFoodCategory = foodCategory.getFoods().stream().map(food -> {
                FoodServiceDTO foodServiceDTO = modelMapper.map(food, FoodServiceDTO.class);
                foodServiceDTO.setFoodCategory(food.getFoodCategory().getName());
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
        if (Objects.isNull(foundFoodCategory)){
            throw new NullPointerException("No such category found!");
        }
        foodCategoryRepository.delete(foundFoodCategory);
        FoodCategoryResponseDTO deletedFoodCategory = modelMapper.map(foundFoodCategory, FoodCategoryResponseDTO.class);
        return deletedFoodCategory;
    }

    @Transactional
    public List<FoodServiceDTO> getFoodsInCategory(FoodCategoryServiceDTO foodCategoryServiceDTO) {
        FoodCategory foodCategory = foodCategoryRepository.findByName(foodCategoryServiceDTO.getName());
        if (Objects.isNull(foodCategory)) {
            throw new NullPointerException("No such category found!");
        }
        return foodCategory.getFoods().stream().map(food -> modelMapper.map(food, FoodServiceDTO.class)
        ).collect(Collectors.toList());
    }
}
