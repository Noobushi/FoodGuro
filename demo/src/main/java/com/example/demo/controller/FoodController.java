package com.example.demo.controller;

import com.example.demo.dto.foodDTO.FoodResponseDTO;
import com.example.demo.dto.foodDTO.FoodServiceDTO;
import com.example.demo.service.FoodServiceImpl;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
public class FoodController extends BaseController {

    private final FoodServiceImpl foodServiceImpl;

    public FoodController(FoodServiceImpl foodServiceImpl) {
        this.foodServiceImpl = foodServiceImpl;
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<FoodResponseDTO> createFood(@RequestBody FoodServiceDTO food) {
        return new ResponseEntity<>(foodServiceImpl.createFood(food), HttpStatus.CREATED);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<FoodResponseDTO> deleteFood(@RequestBody FoodServiceDTO foodServiceDTO) {
        return new ResponseEntity<>(foodServiceImpl.deleteFood(foodServiceDTO), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<FoodResponseDTO> editFood(@RequestBody FoodServiceDTO foodServiceDTO) {
        return new ResponseEntity<>(foodServiceImpl.editFood(foodServiceDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getImage(@RequestParam String foodName) {
        Gson gson = new Gson();
        return gson.toJson(foodServiceImpl.getImage(foodName));
    }


}
