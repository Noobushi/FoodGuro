package com.example.demo.service;

import com.example.demo.domain.entity.FoodCategory;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.entity.UserRoles;
import com.example.demo.domain.model.userModel.UserServiceModel;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void deleteUser(Integer id) {
        this.userRepository.deleteById(id);
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

}
