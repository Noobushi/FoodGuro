package com.example.demo.service;

import com.example.demo.dto.foodDTO.FoodResponseDTO;
import com.example.demo.dto.orderFoodDTO.OrderListResponseDTO;
import com.example.demo.entity.Food;
import com.example.demo.entity.OrderList;
import com.example.demo.entity.User;
import com.example.demo.dto.orderFoodDTO.OrderListServiceDTO;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends BaseService{
    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final FoodRepository foodRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, FoodRepository foodRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
    }

    @Transactional
    public OrderListResponseDTO createOrder(OrderListServiceDTO input, String username) {
        User foundUser = userRepository.findUserByUsername(username);

        OrderList existingOrderList = foundUser.getOrderList();
        if (existingOrderList != null) {
            existingOrderList.getFoods().clear();
        } else {
            existingOrderList = new OrderList();
            foundUser.setOrderList(existingOrderList);
        }

        existingOrderList.setFoods(input.getFoods().stream().map(food -> {
            Food retrievedFood = foodRepository.findByName(food.getName());
            retrievedFood.setQuantity(food.getQuantity());
            return retrievedFood;
        }).collect(Collectors.toList()));
        existingOrderList.setUsername(foundUser.getUsername());

        orderRepository.save(existingOrderList);
        userRepository.save(foundUser);

        OrderListResponseDTO order = new OrderListResponseDTO();
        order.setFoods(existingOrderList.getFoods());
        return order;
    }

    @Transactional
    public List<FoodResponseDTO> getFoodsInOrder(Integer orderId){
        OrderList orderList = orderRepository.getById(orderId);
        return orderList.getFoods().stream().map(food -> modelMapper.map(food, FoodResponseDTO.class)).collect(Collectors.toList());
    }

}

