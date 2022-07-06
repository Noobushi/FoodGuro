package com.example.demo.controller;

import com.example.demo.domain.model.orderModel.OrderCreationBindingModel;
import com.example.demo.domain.model.orderModel.OrderServiceModel;
import com.example.demo.service.OrderServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    private final ModelMapper modelMapper;

    public OrderController(OrderServiceImpl orderServiceImpl, ModelMapper modelMapper) {
        this.orderServiceImpl = orderServiceImpl;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody OrderCreationBindingModel orderCreationBindingModel) {

        orderServiceImpl.createOrder(modelMapper.map(orderCreationBindingModel, OrderServiceModel.class));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
