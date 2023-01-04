package com.example.demo.controller;

import com.example.demo.dto.foodCategoryDTO.FoodCategoryResponseDTO;
import com.example.demo.dto.foodCategoryDTO.FoodCategoryServiceDTO;
import com.example.demo.dto.foodDTO.FoodServiceDTO;
import com.example.demo.service.FoodCategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/foodCategory")
public class FoodCategoryController extends BaseController{
    private final FoodCategoryServiceImpl foodCategoryServiceImpl;

    public FoodCategoryController(FoodCategoryServiceImpl foodCategoryServiceImpl) {
        this.foodCategoryServiceImpl = foodCategoryServiceImpl;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<FoodCategoryResponseDTO> createFoodCategory(@RequestBody FoodCategoryServiceDTO foodCategory) {
        return new ResponseEntity<>(foodCategoryServiceImpl.createFoodCategory(foodCategory), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<FoodCategoryResponseDTO> deleteFoodCategory(@RequestBody FoodCategoryServiceDTO foodCategory) {
        return new ResponseEntity<>(foodCategoryServiceImpl.deleteFoodCategory(foodCategory), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodCategoryServiceDTO>> getAllFoods() {
        return new ResponseEntity<>(foodCategoryServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping("/allFoods")
    public ResponseEntity<List<FoodServiceDTO>> getAllFoodsInCategory( @RequestParam String category) {
        return new ResponseEntity<>(foodCategoryServiceImpl.getFoodsInCategory(category), HttpStatus.OK);
    }

}
