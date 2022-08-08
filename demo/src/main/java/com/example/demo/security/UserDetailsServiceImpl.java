package com.example.demo.security;

import com.example.demo.domain.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            throw new RuntimeException(String.format("User %s not found in the database", username));
        }
        return new UserPrincipal(user);
    }
}