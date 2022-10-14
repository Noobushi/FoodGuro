package com.example.demo.controller;

import com.example.demo.dto.orderFoodDTO.OrderFoodResponseDTO;
import com.example.demo.dto.orderFoodDTO.OrderFoodServiceDTO;
import com.example.demo.service.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{

    private final OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }
    @PostMapping("/create")
    public ResponseEntity<OrderFoodResponseDTO> createOrder(@RequestBody OrderFoodServiceDTO orderFood) {
        return new ResponseEntity<>(orderServiceImpl.createOrder(orderFood),HttpStatus.CREATED);
    }

}
