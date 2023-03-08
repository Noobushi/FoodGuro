package com.example.demo.controller;

import com.example.demo.dto.foodDTO.FoodResponseDTO;
import com.example.demo.dto.foodDTO.FoodServiceDTO;
import com.example.demo.dto.orderFoodDTO.OrderListResponseDTO;
import com.example.demo.dto.orderFoodDTO.OrderListServiceDTO;
import com.example.demo.service.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{

    private final OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }
    @PostMapping("/create")
    public ResponseEntity<OrderListResponseDTO> createOrder(@RequestBody OrderListServiceDTO order) {
        return new ResponseEntity<>(orderServiceImpl.createOrder(order),HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodResponseDTO>> getAllFoods(@RequestParam Integer orderId) {
        return new ResponseEntity<>(orderServiceImpl.getFoodsInOrder(orderId), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<OrderListResponseDTO> deleteFood(@RequestBody OrderListServiceDTO order) {
        return new ResponseEntity<>(orderServiceImpl.deleteOrder(order), HttpStatus.OK);
    }
}
