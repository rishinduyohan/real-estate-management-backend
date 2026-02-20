package com.pvt.ecom.service.impl;

import com.pvt.ecom.model.entity.User;
import com.pvt.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public String verifyUser(User user) {
        System.out.println("Trying to login user: " + user.getEmail());
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
                    );

            if(authentication.isAuthenticated())
                return jwtService.genarateToken(user.getEmail());
        } catch (Exception e) {
            System.out.println("Login Failed: " + e.getMessage());
            return "fail: " + e.getMessage();
        }
        return "fail";
    }

    public User updateUserProfile(User updatedUser) {
        return userRepository.save(updatedUser);
    }
}
