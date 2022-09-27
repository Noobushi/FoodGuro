package com.example.demo.service;

import com.example.demo.dto.userDTO.UserResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRoles;
import com.example.demo.dto.userDTO.UserServiceDTO;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO registerUser(UserServiceDTO input) {

        User registerUser = modelMapper.map(input, User.class);
        registerUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        registerUser.setUserRole(UserRoles.ROLE_USER);
        userRepository.save(registerUser);
        UserResponseDTO responseUser = modelMapper.map(registerUser, UserResponseDTO.class);
        return responseUser;
    }

    @Transactional
    public UserResponseDTO deleteUser(UserServiceDTO user) {
        User foundUser = userRepository.findUserByUsername(user.getUsername());
        if (Objects.isNull(foundUser)){
            throw new NullPointerException("No such user found!");
        }
        userRepository.delete(foundUser);
        UserResponseDTO deleteUser = modelMapper.map(user, UserResponseDTO.class);
        return deleteUser;
    }

    @Transactional
    public UserServiceDTO editUser(UserServiceDTO userServiceDTO) {
        User foundUser = userRepository.findById(userServiceDTO.getId()).orElseThrow(() -> new UsernameNotFoundException("No user found"));

        foundUser.setFirstName(userServiceDTO.getFirstName());
        foundUser.setLastName(userServiceDTO.getLastName());
        foundUser.setCity(userServiceDTO.getCity());
        foundUser.setUsername(userServiceDTO.getUsername());
        foundUser.setPassword(userServiceDTO.getPassword());
        foundUser.setUserRole(UserRoles.valueOf(userServiceDTO.getUserRole()));

        return modelMapper.map(foundUser, UserServiceDTO.class);

    }

    public User findByName(String userName) {
        if (Objects.isNull(userName)){
            throw new NullPointerException("No such user found!");
        }
        return this.userRepository.findUserByUsername(userName);
    }

    public List<UserServiceDTO> findAll() {
        return this.userRepository.findAll().stream().map(user -> modelMapper.map(user, UserServiceDTO.class)).collect(Collectors.toList());
    }


}
