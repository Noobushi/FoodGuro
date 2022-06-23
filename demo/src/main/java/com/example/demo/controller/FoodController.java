package com.example.demo.controller;

import com.example.demo.domain.model.foodModel.FoodCreationBindingModel;
import com.example.demo.domain.model.foodModel.FoodCreationResponseModel;
import com.example.demo.domain.model.foodModel.FoodServiceModel;
import com.example.demo.service.FoodServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
public class FoodController {

   private final FoodServiceImpl foodServiceImpl;

   private final ModelMapper modelMapper;

    public FoodController(FoodServiceImpl foodServiceImpl, ModelMapper modelMapper) {
        this.foodServiceImpl = foodServiceImpl;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<FoodCreationResponseModel> createFood (@RequestBody FoodCreationBindingModel foodCreationBindingModel){

        String name = foodServiceImpl.createFood(modelMapper.map(foodCreationBindingModel, FoodServiceModel.class));

        FoodCreationResponseModel createdFood = new FoodCreationResponseModel();
        createdFood.setName(name);
        return new ResponseEntity<>(createdFood, HttpStatus.CREATED );
    }
}
