package com.example.demo.service;

import com.example.demo.dto.foodDTO.FoodResponseDTO;
import com.example.demo.dto.orderFoodDTO.OrderListResponseDTO;
import com.example.demo.entity.Food;
import com.example.demo.entity.OrderList;
import com.example.demo.entity.User;
import com.example.demo.dto.orderFoodDTO.OrderListServiceDTO;
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
    public OrderListResponseDTO createOrder(OrderListServiceDTO input) {
        OrderList newOrderList = new OrderList();
        newOrderList.setFoods(input.getFoods().stream().map(foodServiceImpl::findByName).collect(Collectors.toList()));
        User foundUser = userService.findByName(input.getUsername());
        newOrderList.setUser(foundUser);
        newOrderList.setUsername(foundUser.getUsername());
        orderRepository.save(newOrderList);
//        OrderFoodResponseDTO order = modelMapper.map(newOrderFood, OrderFoodResponseDTO.class);

        OrderListResponseDTO order = new OrderListResponseDTO();
        order.setUsername(newOrderList.getUser().getUsername());
        order.setFoods(newOrderList.getFoods().stream().map(Food::getName).collect(Collectors.toList()));

        return order;
    }

    @Transactional
    public List<FoodResponseDTO> getFoodsInOrder(Integer orderId){
        OrderList orderList = orderRepository.getById(orderId);
        return orderList.getFoods().stream().map(food -> modelMapper.map(food, FoodResponseDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public OrderListResponseDTO deleteOrder(OrderListServiceDTO order) {
        OrderList foundOrderList = orderRepository.findOrderByUsername(order.getUsername());
        checkIfNull(foundOrderList, order.getUsername());
        orderRepository.delete(foundOrderList);

        OrderListResponseDTO deletedOrder = new OrderListResponseDTO();
        deletedOrder.setUsername(foundOrderList.getUsername());
        deletedOrder.setFoods(foundOrderList.getFoods().stream().map(Food::getName).collect(Collectors.toList()));

        return deletedOrder;
    }

}
