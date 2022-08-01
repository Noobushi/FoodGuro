package com.example.demo.service;

import com.example.demo.domain.entity.Food;
import com.example.demo.domain.entity.OrderFood;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.model.orderModel.OrderServiceModel;
import com.example.demo.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

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
    public void createOrder(OrderServiceModel orderServiceModel) {
        List<Food> foods = orderServiceModel.getFoods().stream().map(foodServiceImpl::findByName).collect(Collectors.toList());
        User user = userService.findByName(orderServiceModel.getUsername());
        OrderFood orderFood = new OrderFood();
        orderFood.setFoods(foods);
        orderFood.setUser(user);
        user.getOrder().add(orderFood);
        orderRepository.save(orderFood);
    }

}
