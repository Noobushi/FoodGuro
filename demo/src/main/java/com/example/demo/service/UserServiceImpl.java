package com.example.demo.service;

import com.example.demo.domain.entity.Food;
import com.example.demo.domain.entity.FoodCategory;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.entity.UserRoles;
import com.example.demo.domain.model.foodCategoryModel.FoodCategoryServiceModel;
import com.example.demo.domain.model.foodModel.FoodServiceModel;
import com.example.demo.domain.model.userModel.UserServiceModel;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String registerUser(UserServiceModel userServiceModel) {
//        User user = new User();
//        user.setFirstName(userServiceModel.getFirstName());
//        user.setLastName(userServiceModel.getLastName());
//        user.setUsername(userServiceModel.getUsername());
//        user.setCity(userServiceModel.getCity());
//        user.setPassword(userServiceModel.getPassword());

        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRoles.ROLE_USER);
        userRepository.save(user);

        return user.getUsername();
    }

    @Transactional
    public String deleteUser(UserServiceModel userServiceModel) {
        User user = userRepository.findUserByUsername(userServiceModel.getUsername());
        String username = user.getUsername();
        userRepository.delete(user);
        return username;
    }

    @Transactional
    public UserServiceModel editUser(UserServiceModel userServiceModel) {
        User user = userRepository.findById(userServiceModel.getId()).get();

        user.setFirstName(userServiceModel.getFirstName());
        user.setLastName(userServiceModel.getLastName());
        user.setCity(userServiceModel.getCity());
        user.setUsername(userServiceModel.getUsername());
        user.setPassword(userServiceModel.getPassword());
        user.setUserRole(UserRoles.valueOf(userServiceModel.getUserRole()));

        UserServiceModel edited = modelMapper.map(user, UserServiceModel.class);
        return edited;
    }

    public User findByName(String userName) {
        return this.userRepository.findUserByUsername(userName);
    }

    public List<User> findAll() {
     return this.userRepository.findAll();
    }


}
