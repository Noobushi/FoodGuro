package com.example.demo.controller;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.model.foodModel.FoodCreationResponseAndEditModel;
import com.example.demo.domain.model.foodModel.FoodServiceModel;
import com.example.demo.domain.model.userModel.UserEditResponseModel;
import com.example.demo.domain.model.userModel.UserRegisterAndDeleteBindingModel;
import com.example.demo.domain.model.userModel.UserRegisterAndDeleteResponseModel;
import com.example.demo.domain.model.userModel.UserServiceModel;
import com.example.demo.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<UserRegisterAndDeleteResponseModel> registerUser (@RequestBody UserRegisterAndDeleteBindingModel userRegisterAndDeleteBindingModel){
//        UserServiceModel user = new UserServiceModel();
//        user.setFirstName(userRegisterAndDeleteBindingModel.getFirstName());
//        user.setLastName(userRegisterAndDeleteBindingModel.getLastName());
//        user.setUsername(userRegisterAndDeleteBindingModel.getUsername());
//        user.setCity(userRegisterAndDeleteBindingModel.getCity());
//        user.setPassword(userRegisterAndDeleteBindingModel.getPassword());

//        UserServiceModel user = modelMapper.map(userRegisterAndDeleteBindingModel, UserServiceModel.class);

        String username = userServiceImpl.registerUser(modelMapper.map(userRegisterAndDeleteBindingModel, UserServiceModel.class));

        UserRegisterAndDeleteResponseModel registeredUser = new UserRegisterAndDeleteResponseModel();
        registeredUser.setUsername(username);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED );
    }

    @PostMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody UserRegisterAndDeleteResponseModel userRegisterAndDeleteResponseModel){
        userServiceImpl.deleteUser(userServiceImpl.findByName(userRegisterAndDeleteResponseModel.getUsername()).getId());
        return new ResponseEntity( HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<UserServiceModel> editUser (@RequestBody UserEditResponseModel userEditResponseModel){

        UserServiceModel editedUser = userServiceImpl.editUser(modelMapper.map(userEditResponseModel, UserServiceModel.class));

        return new ResponseEntity<>(editedUser, HttpStatus.CREATED);
    }
}
