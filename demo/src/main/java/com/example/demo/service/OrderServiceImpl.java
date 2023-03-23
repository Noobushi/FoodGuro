package com.example.demo.service;

import com.example.demo.dto.foodDTO.FoodResponseDTO;
import com.example.demo.dto.orderFoodDTO.OrderListResponseDTO;
import com.example.demo.entity.Food;
import com.example.demo.entity.OrderList;
import com.example.demo.entity.User;
import com.example.demo.dto.orderFoodDTO.OrderListServiceDTO;
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
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public OrderListResponseDTO createOrder(OrderListServiceDTO input, String username) {
        User foundUser = userRepository.findUserByUsername(username);
        OrderList existingOrderList = foundUser.getOrderList();

        if(existingOrderList != null){
            foundUser.setOrderList(null);
            orderRepository.delete(existingOrderList);
        }

        OrderList newOrderList = new OrderList();
        newOrderList.setFoods(input.getFoods().stream().map(food -> {
            Food f = new Food();
            f.setFoodCategory(food.getFoodCategory());
            f.setName(food.getName());
            f.setPrice(food.getPrice());
            f.setDescription(food.getDescription());
            f.setImage(food.getImage());
            return f;
        }).collect(Collectors.toList()));

        newOrderList.setUser(foundUser);
        orderRepository.save(newOrderList);

        foundUser.setOrderList(newOrderList);
        userRepository.save(foundUser);

        OrderListResponseDTO order = new OrderListResponseDTO();
        order.setFoods(newOrderList.getFoods().stream().map(food -> {
            Food f = new Food();
            f.setFoodCategory(food.getFoodCategory());
            f.setName(food.getName());
            f.setPrice(food.getPrice());
            f.setDescription(food.getDescription());
            f.setImage(food.getImage());
            return f;
        }).collect(Collectors.toList()));

        return order;
    }

    @Transactional
    public List<FoodResponseDTO> getFoodsInOrder(Integer orderId){
        OrderList orderList = orderRepository.getById(orderId);
        return orderList.getFoods().stream().map(food -> modelMapper.map(food, FoodResponseDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public void deleteOrder(Integer orderId) {
        OrderList orderList = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        User user = orderList.getUser();
        user.setOrderList(null);
        orderRepository.deleteById(orderList.getId());
    }
}

