package com.example.demo.controller;

import com.example.demo.dto.userDTO.UserResponseDTO;
import com.example.demo.dto.userDTO.UserServiceDTO;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserServiceDTO user) {
        return new ResponseEntity<>(userServiceImpl.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<UserResponseDTO> deleteUser(@RequestBody UserServiceDTO user) {
        return new ResponseEntity<>(userServiceImpl.deleteUser(user),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<UserServiceDTO> editUser(@RequestBody UserServiceDTO user) {
        return new ResponseEntity<>(userServiceImpl.editUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<UserServiceDTO> getAllUsers() {
        return userServiceImpl.findAll();
    }

}
