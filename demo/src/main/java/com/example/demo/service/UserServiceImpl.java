package com.example.demo.service;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.model.userModel.UserServiceModel;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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

        userRepository.save(user);

        return user.getUsername();
    }

}
