package com.example.demo.controller;

import com.example.demo.dto.foodDTO.FoodResponseDTO;
import com.example.demo.dto.foodDTO.FoodServiceDTO;
import com.example.demo.service.FoodServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController extends BaseController{

    private final FoodServiceImpl foodServiceImpl;

    public FoodController(FoodServiceImpl foodServiceImpl) {
        this.foodServiceImpl = foodServiceImpl;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<FoodResponseDTO> createFood(@RequestBody FoodServiceDTO food) {
        return new ResponseEntity<>(foodServiceImpl.createFood(food), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<FoodResponseDTO> deleteFood(@RequestBody FoodServiceDTO foodServiceDTO) {
        return new ResponseEntity<>(foodServiceImpl.deleteFood(foodServiceDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<FoodResponseDTO> editFood(@RequestBody FoodServiceDTO foodServiceDTO) {
        return new ResponseEntity<>(foodServiceImpl.editFood(foodServiceDTO), HttpStatus.CREATED);
    }
}
