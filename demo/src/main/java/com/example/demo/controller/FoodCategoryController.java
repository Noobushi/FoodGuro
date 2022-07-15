package com.example.demo.controller;

import com.example.demo.domain.model.foodCategoryModel.FoodCategoryCreationBindingModel;
import com.example.demo.domain.model.foodCategoryModel.FoodCategoryCreationResponseModel;
import com.example.demo.domain.model.foodCategoryModel.FoodCategoryDeleteResponseAndBindingModel;
import com.example.demo.domain.model.foodCategoryModel.FoodCategoryServiceModel;
import com.example.demo.service.FoodCategoryServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foodCategory")
public class FoodCategoryController {
    private final FoodCategoryServiceImpl foodCategoryServiceImpl;

    private final ModelMapper modelMapper;

    public FoodCategoryController(FoodCategoryServiceImpl foodCategoryServiceImpl, ModelMapper modelMapper) {
        this.foodCategoryServiceImpl = foodCategoryServiceImpl;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<FoodCategoryCreationResponseModel> createFoodCategory (@RequestBody FoodCategoryCreationBindingModel foodCategoryCreationBindingModel){

        String name = foodCategoryServiceImpl.createFoodCategory(modelMapper.map(foodCategoryCreationBindingModel, FoodCategoryServiceModel.class));

        FoodCategoryCreationResponseModel createFoodCatalog = new FoodCategoryCreationResponseModel();
        createFoodCatalog.setName(name);
        return new ResponseEntity<>(createFoodCatalog, HttpStatus.CREATED );
    }

    @PostMapping("/delete")
    public ResponseEntity<FoodCategoryDeleteResponseAndBindingModel> deleteFoodCategory (@RequestBody FoodCategoryDeleteResponseAndBindingModel foodCategoryDeleteResponseAndBindingModel){

        String categoryName = foodCategoryServiceImpl.deleteFoodCategory(modelMapper.map(foodCategoryDeleteResponseAndBindingModel, FoodCategoryServiceModel.class));

        FoodCategoryDeleteResponseAndBindingModel deletedFoodCategory = new FoodCategoryDeleteResponseAndBindingModel();
       deletedFoodCategory.setName(categoryName);
        return new ResponseEntity<>(deletedFoodCategory, HttpStatus.OK );
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodCategoryServiceModel>> getAllFoods(){
        return new ResponseEntity<>(foodCategoryServiceImpl.findAll(),HttpStatus.OK);
    }
}
