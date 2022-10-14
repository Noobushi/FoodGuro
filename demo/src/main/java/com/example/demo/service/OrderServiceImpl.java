package com.example.demo.service;

import com.example.demo.dto.foodDTO.FoodServiceDTO;
import com.example.demo.dto.orderFoodDTO.OrderFoodResponseDTO;
import com.example.demo.entity.Food;
import com.example.demo.entity.OrderFood;
import com.example.demo.entity.User;
import com.example.demo.dto.orderFoodDTO.OrderFoodServiceDTO;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        OrderFood newOrderFood = new OrderFood();
        newOrderFood.setFoods(input.getFoods().stream().map(foodServiceImpl::findByName).collect(Collectors.toList()));
        User foundUser = userService.findByName(input.getUsername());
        newOrderFood.setUser(foundUser);
        orderRepository.save(newOrderFood);
        OrderFoodResponseDTO order = new OrderFoodResponseDTO();
        order.setUsername(newOrderFood.getUser().getUsername());
        order.setFoods(newOrderFood.getFoods().stream().map(Food::getName).collect(Collectors.toList()));
        return order;
    }

}
