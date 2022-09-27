package com.example.demo.service;

import com.example.demo.dto.orderFoodDTO.OrderFoodResponseDTO;
import com.example.demo.entity.Food;
import com.example.demo.entity.OrderFood;
import com.example.demo.entity.User;
import com.example.demo.dto.orderFoodDTO.OrderFoodServiceDTO;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends BaseService{

    private final UserServiceImpl userService;
    private final OrderRepository orderRepository;
    private final FoodServiceImpl foodServiceImpl;
    @Autowired
    public OrderServiceImpl(UserServiceImpl userService, OrderRepository orderRepository, FoodServiceImpl foodServiceImpl) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.foodServiceImpl = foodServiceImpl;
    }

    @Transactional
    public OrderFoodResponseDTO createOrder(OrderFoodServiceDTO input) {
        List<Food> foods = input.getFoods().stream().map(foodServiceImpl::findByName).collect(Collectors.toList());
        OrderFood newOrderFood = modelMapper.map(input,OrderFood.class);
        User foundUser = userService.findByName(input.getUsername());
        foundUser.getOrder().add(newOrderFood);
        orderRepository.save(newOrderFood);
        OrderFoodResponseDTO order = modelMapper.map(newOrderFood, OrderFoodResponseDTO.class);
        return order;
    }

}
