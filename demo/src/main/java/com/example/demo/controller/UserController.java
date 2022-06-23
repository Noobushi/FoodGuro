package com.example.demo.controller;

import com.example.demo.domain.model.userModel.UserRegisterBindingModel;
import com.example.demo.domain.model.userModel.UserRegisterResponseModel;
import com.example.demo.domain.model.userModel.UserServiceModel;
import com.example.demo.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, ModelMapper modelMapper) {
        this.userServiceImpl = userServiceImpl;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseModel> registerUser (@RequestBody UserRegisterBindingModel userRegisterBindingModel){
//        UserServiceModel user = new UserServiceModel();
//        user.setFirstName(userRegisterBindingModel.getFirstName());
//        user.setLastName(userRegisterBindingModel.getLastName());
//        user.setUsername(userRegisterBindingModel.getUsername());
//        user.setCity(userRegisterBindingModel.getCity());
//        user.setPassword(userRegisterBindingModel.getPassword());

//        UserServiceModel user = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        String username = userServiceImpl.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        UserRegisterResponseModel registeredUser = new UserRegisterResponseModel();
        registeredUser.setUsername(username);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED );
    }
}
